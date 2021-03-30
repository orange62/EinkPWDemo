package com.htfyun.einkpwdemo;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FullScreenBaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        final int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  // hide naviagtion,and whow again when touch
//                      | View.SYSTEM_UI_FLAG_IMMERSIVE    // use with SYSTEM_UI_FLAG_HIDE_NAVIGATION.only show when wipe
                // top or bottom of screen.
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY // the same with SYSTEM_UI_FLAG_IMMERSIVE,but hide navigation
                // after a short timeout.
                // implements at frameworks/base/policy/src/com/android/internal/policy/impl/PhoneWindowManager.java
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
//                | View.SYSTEM_UI_FLAG_LOW_PROFILE;
        decorView.setSystemUiVisibility(uiOptions);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            final int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  // hide naviagtion,and whow again when touch
//                      | View.SYSTEM_UI_FLAG_IMMERSIVE    // use with SYSTEM_UI_FLAG_HIDE_NAVIGATION.only show when wipe
                    // top or bottom of screen.
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY // the same with SYSTEM_UI_FLAG_IMMERSIVE,but hide navigation
                    // after a short timeout.
                    // implements at frameworks/base/policy/src/com/android/internal/policy/impl/PhoneWindowManager.java
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
//                | View.SYSTEM_UI_FLAG_LOW_PROFILE;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

}
