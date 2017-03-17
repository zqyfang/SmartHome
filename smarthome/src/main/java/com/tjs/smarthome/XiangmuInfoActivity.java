package com.tjs.smarthome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tjs.smarthome.base.BaseActivity;

public class XiangmuInfoActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar setting_item_toolbar_one;
    private TextView tv;
    private ImageButton ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangmu_info);
        initView();
    }

    @Override
    public void initView() {
        //找到toolbar的ID
        setting_item_toolbar_one= (Toolbar) findViewById(R.id.setting_item_toolbar_one);
        tv= (TextView) setting_item_toolbar_one.findViewById(R.id.setting_toolbar_tv);
        tv.setText("项目信息");

        ib= (ImageButton) setting_item_toolbar_one.findViewById(R.id.setting_title_ib);
        ib.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_title_ib:
                startActivity(new Intent(this,SettingActivity.class));
            break;
        }
    }
}
