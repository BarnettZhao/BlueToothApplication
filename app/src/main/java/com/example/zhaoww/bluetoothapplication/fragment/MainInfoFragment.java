package com.example.zhaoww.bluetoothapplication.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zhaoww.bluetoothapplication.R;
import com.example.zhaoww.bluetoothapplication.activity.LocationMapActivity;
import com.example.zhaoww.bluetoothapplication.utils.PreferencesUtils;

/**
 * Created by zhaoww on 2016/5/2.
 */
public class MainInfoFragment extends Fragment implements View.OnClickListener{
    private ImageView mLocation;
    private static final int REQUEST_ACT_LOCATION = 1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.maininfo_fragment,null);
        mLocation = (ImageView) view.findViewById(R.id.image_location);
        mLocation.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_location:
                startActivityForResult(new Intent(getActivity(), LocationMapActivity.class), REQUEST_ACT_LOCATION);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (getActivity().RESULT_OK == resultCode) {
            switch (requestCode) {
                case REQUEST_ACT_LOCATION:
                    String address = data.getStringExtra("address");
//                    mTextLocation.setText(address);
//                    PreferencesUtils.getInstance().writeSharedString(getActivity(), ADDRESS, address);
                    Toast.makeText(getActivity().getApplicationContext(), address, Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    }
}
