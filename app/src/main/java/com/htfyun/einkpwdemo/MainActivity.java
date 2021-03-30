package com.htfyun.einkpwdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.EinkPWInterface;
import android.view.PWDrawObjectHandler;
import android.view.View;

public class MainActivity extends FullScreenBaseActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private View pwView;
    private View toolbarPenWidth;
    private View toolbarPenWidth2;
    private View toolbarUndo;
    private View toolbarRedo;
    private View toolbarRectangle;
    private EinkPWInterface einkPWInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbarPenWidth = findViewById(R.id.toolbarPenWidth);
        toolbarPenWidth2 = findViewById(R.id.toolbarPenWidth2);
        toolbarRectangle = findViewById(R.id.toolbarRectangle);
        toolbarUndo = findViewById(R.id.toolbarUndo);
        toolbarRedo = findViewById(R.id.toolbarRedo);

        toolbarPenWidth.setOnClickListener(this);
        toolbarPenWidth2.setOnClickListener(this);
        toolbarRectangle.setOnClickListener(this);
        toolbarUndo.setOnClickListener(this);
        toolbarRedo.setOnClickListener(this);

        pwView = findViewById(R.id.pwView);
        einkPWInterface = pwView.getPWInterFace();

    }

    @Override
    public void onClick(View v) {
        if (v == toolbarPenWidth) {
            if (einkPWInterface.getDrawObjectType() != PWDrawObjectHandler.DRAW_OBJ_RANDOM) {
                Log.i(TAG, "OBJECT not RANDOW");
                einkPWInterface.setDrawObjectType(PWDrawObjectHandler.DRAW_OBJ_RANDOM);
            }
            einkPWInterface.setPenSettingWidth(1);
        } else if (v == toolbarPenWidth2) {
            if (einkPWInterface.getDrawObjectType() != PWDrawObjectHandler.DRAW_OBJ_RANDOM) {
                Log.i(TAG, "OBJECT not RANDOW");
                einkPWInterface.setDrawObjectType(PWDrawObjectHandler.DRAW_OBJ_RANDOM);
            }
            einkPWInterface.setPenSettingWidth(5);
        } else if (v == toolbarRectangle) {
            Log.i(TAG, "Draw Rectangle");
            einkPWInterface.setDrawObjectType(PWDrawObjectHandler.DRAW_OBJ_RECTANGLE);
        } else if (v == toolbarUndo) {
            Log.i(TAG, "click Undo");
            einkPWInterface.unDo();
        } else if (v == toolbarRedo) {
            Log.i(TAG, "click reDo");
            einkPWInterface.reDo();
        }
    }

}
