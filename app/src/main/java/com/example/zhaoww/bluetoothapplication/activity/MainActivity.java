package com.example.zhaoww.bluetoothapplication.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.zhaoww.bluetoothapplication.R;
import com.example.zhaoww.bluetoothapplication.fragment.MainInfoFragment;
import com.example.zhaoww.bluetoothapplication.fragment.SettingFragment;
import com.example.zhaoww.bluetoothapplication.utils.ActivityController;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private FragmentManager mManager;
    private FragmentTransaction mTransaction;

    private MainInfoFragment mMainInfo;
    private SettingFragment mSetting;

    private RadioGroup mRadioGroup;
    private RadioButton mRadioButtonInfo,mRadioButtonSetting;
    private Boolean isExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragments();
    }

    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.radiogroup_main);
        mRadioButtonInfo = (RadioButton) findViewById(R.id.radiobutton_info);
        mRadioButtonSetting = (RadioButton) findViewById(R.id.radiobutton_setting);
        mRadioButtonInfo.setOnClickListener(this);
        mRadioButtonSetting.setOnClickListener(this);
    }

    private void initFragments() {
        mMainInfo = new MainInfoFragment();
        mSetting =  new SettingFragment();
        mManager=getFragmentManager();
        mTransaction=mManager.beginTransaction();
        mTransaction.replace(R.id.frame_main,mMainInfo);
        mTransaction.commit();
        mRadioGroup.check(R.id.radiobutton_info);
    }

    @Override
    public void onClick(View v) {
        mTransaction=mManager.beginTransaction();
        switch (v.getId()) {
            case R.id.radiobutton_info:
                mTransaction.replace(R.id.frame_main,mMainInfo);
                break;
            case R.id.radiobutton_setting:
                mTransaction.replace(R.id.frame_main,mSetting);
                break;
            default:
                break;
        }
        mTransaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
            exit();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if(!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            ActivityController.finishAll();
        }
    }
}
