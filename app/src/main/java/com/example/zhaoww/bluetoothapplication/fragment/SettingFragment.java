package com.example.zhaoww.bluetoothapplication.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhaoww.bluetoothapplication.R;
import com.example.zhaoww.bluetoothapplication.activity.LoginActivity;
import com.example.zhaoww.bluetoothapplication.activity.SetIcon;
import com.example.zhaoww.bluetoothapplication.utils.ActivityController;
import com.example.zhaoww.bluetoothapplication.utils.GetDiskBitmapUtil;
import com.example.zhaoww.bluetoothapplication.utils.PreferencesUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zhaoww on 2016/5/2.
 * shezhi
 */
public class SettingFragment extends Fragment implements View.OnClickListener{
    private CircleImageView mCircleViewIcon;
    private TextView mTextSex, mTextData,mTextName;
    private LinearLayout mLayoutLocation, mLayoutBirthday, mLayoutSex, mLayoutName, mLayoutIcon;
    private Button mButtonQuit;
    private String []mSexs={"男","女"};
    private String sex ,data;
    private Calendar mCalendar;
    public static String NAME = "loginName", SEX = "sex", DATA = "data", ADDRESS = "address";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.setting_fragment,null);
        initWidgets(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        String pathMe =getActivity().getApplicationContext().getExternalFilesDir("icon").getAbsolutePath()+"/ailafei/icon/myicon.jpg";
//		String pathIt =getApplicationContext().getExternalFilesDir("icon").getAbsolutePath()+"/ailafei/icon/it.jpg";
//        Log.e("path" , pathMe);
//        if (pathMe!=null){
//            mCircleViewIcon.setImageBitmap(GetDiskBitmapUtil.getDiskBitmap(pathMe));
//            mCircleViewIcon.setImageBitmap(GetDiskBitmapUtil.getDiskBitmap(pathMe));
//        }else {
            mCircleViewIcon.setImageResource(R.mipmap.blooth_logo);
//        }

        mTextData.setText(PreferencesUtils.getInstance().readSharedString(getActivity(),DATA, "1月1日"));

        mTextSex.setText(PreferencesUtils.getInstance().readSharedString(getActivity(),SEX, "男"));

        mTextName.setText(PreferencesUtils.getInstance().readSharedString(getActivity(), NAME, "二锅头"));

    }

    private void initWidgets(View view) {
        mCircleViewIcon= (CircleImageView) view.findViewById(R.id.circleImageView_icon);
        mCircleViewIcon.setOnClickListener(this);
        mButtonQuit= (Button) view.findViewById(R.id.person_info_login_out);
        mButtonQuit.setOnClickListener(this);
        mLayoutLocation = (LinearLayout) view.findViewById(R.id.person_info_location_ll);
        mLayoutLocation.setOnClickListener(this);
        mLayoutBirthday = (LinearLayout) view.findViewById(R.id.layout_mybirthday);
        mLayoutBirthday.setOnClickListener(this);
        mLayoutSex = (LinearLayout) view.findViewById(R.id.layout_mysex);
        mLayoutSex.setOnClickListener(this);
        mLayoutName = (LinearLayout) view.findViewById(R.id.layout_myname);
        mLayoutName.setOnClickListener(this);
        mLayoutIcon = (LinearLayout) view.findViewById(R.id.layout_myicon);
        mLayoutIcon.setOnClickListener(this);
        mTextSex = (TextView) view.findViewById(R.id.person_info_gender);
        mTextData = (TextView) view.findViewById(R.id.person_info_birthday);
        mTextName = (TextView) view.findViewById(R.id.person_info_nickname);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_mybirthday:
                showCalendarDialog("noYear",mTextData,DATA);
                break;
            case R.id.layout_mysex:
                showSexDialog(mTextSex,SEX);
                break;
            case R.id.layout_myicon:
                startActivity(new Intent(getActivity(),SetIcon.class));
                break;
            case R.id.person_info_login_out:
                ActivityController.finishAll();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                Toast.makeText(getActivity().getApplicationContext(),"已退出应用",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void showSexDialog(final TextView textView, final String sharSex){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setSingleChoiceItems(mSexs, 2, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                sex=mSexs[which];
                Toast.makeText(getActivity().getApplicationContext(),"你的选择是"+ sex,Toast.LENGTH_LONG).show();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText(sex);
                PreferencesUtils.getInstance().writeSharedString(getActivity(),sharSex,sex);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        //builder.show();
        Dialog dialog=builder.show();
        dialog.show();
    }

    private void showCalendarDialog(final String typeYear, final TextView textView,final String sharData) {
        mCalendar= Calendar.getInstance();
        DatePickerDialog dialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mCalendar.set(year,monthOfYear,dayOfMonth);
                SimpleDateFormat format=null;
                if ("Year".equals(typeYear)){
                    format=new SimpleDateFormat("yyyy年MM月dd日");
                }else if ("noYear".equals(typeYear)){
                    format=new SimpleDateFormat("MM月dd日");
                }
                data=format.format(mCalendar.getTime());
                textView.setText(data);
                PreferencesUtils.getInstance().writeSharedString(getActivity(), sharData, data);
            }
        },mCalendar.get(Calendar.YEAR),mCalendar.get(Calendar.MONTH),mCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
}
