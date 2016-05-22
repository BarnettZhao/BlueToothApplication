package com.example.zhaoww.bluetoothapplication.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.zhaoww.bluetoothapplication.utils.ActivityController;

/**
 * Created by zhaoww on 2016/5/2.
 */
public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityController.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }
}
