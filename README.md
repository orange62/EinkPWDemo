要使用我们新增加的接口, 需要使用我们提供的classes.jar包。需要补充的是, 我们这边使用eclipse替换默认的classes.jar很方便, 但是使用android studio去操作的时候可能会有点挫折. 不确定不同android studio版本会不会有不同配置方式. 我们这边使用android studio 3.6.4。

配置方法是:

### 1. 将classes.jar 放到 libs/eink下(eink是新建的目录), 然后在build.gradle的dependencies 下添加下面语句:

compileOnly files(&#39;libs/eink/classes.jar&#39;)

注意：一定要用compileOnly，运行的时候不需要这个 classes.jar。

### 改变jar 包引用优先级

##### 在项目根目录的 build.gradle下的 allprojects 节点下;

allprojects **{**
repositories **{**
google()
 jcenter()

**}**

gradle.projectsEvaluated **{**
tasks.withType(JavaCompile) **{**
options.compilerArgs.add(&#39;-Xbootclasspath/p:app/libs/eink/classes.jar&#39;)
**}
 }
 }**

上面的配置可能在有些版本的AS 上面编译不通过，找不到 我们新增加的classes.jar包。这个时候可以用下面的定义看看：

def androidJar = file(&#39;libs/eink/classes.jar&#39;)

gradle.projectsEvaluated **{**
  tasks.withType(JavaCompile) **{**
    options.compilerArgs.add(&quot;-Xbootclasspath/p:$androidJar&quot;)
**}
 }**

就是通过def 来定义编译的jar包。

#### 2.2 修改module.iml

在Android studio 的module中修改jar包在module.iml(如果module名是app, 那么此文件就是 app.iml)文件里.将orderEntry里的jdk移到最后面. 注意, 这个iml文件是动态变化的, 所以如果在项目中发现使用不了我们的classes.jar, 就去改一下这里.

##### 手动修改iml顺序

\&lt;orderEntry type=&quot;jdk&quot; jdkName=&quot;Android API 25 Platform&quot; jdkType=&quot;Android SDK&quot; /\&gt;

注意：不同的 android版本上面的API版本可能配置不一样。如果是8.1SDK，可能需要改为 API 27.

#### 自动修改iml顺序

app下build.gradle末尾添加下面的代码，会在build时自动修改顺序

    preBuild {
        doLast {
            def imlFile = file(project.name + ".iml")
            println 'Change ' + project.name + '.iml order'
            try {
                def parsedXml = (new XmlParser()).parse(imlFile)
                def jdkNode = parsedXml.component[1].orderEntry.find { it.'@type' == 'jdk' }
                parsedXml.component[1].remove(jdkNode)
                def sdkString = "Android API " + android.compileSdkVersion.substring("android-".length()) + " Platform"
                new Node(parsedXml.component[1], 'orderEntry', ['type': 'jdk', 'jdkName': sdkString, 'jdkType': 'Android SDK'])
                XmlUtil.serialize(parsedXml, new FileOutputStream(imlFile))
            } catch (FileNotFoundException ignore) {
                // nop, iml not found
            }
        }
    }
