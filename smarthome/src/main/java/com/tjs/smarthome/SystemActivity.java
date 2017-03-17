package com.tjs.smarthome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tjs.smarthome.base.BaseActivity;

public class SystemActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton setting_title_ib;
    private TextView setting_toolbar_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);
        initView();
    }

    @Override
    public void initView() {
        setting_title_ib= (ImageButton) findViewById(R.id.setting_title_ib);
        setting_toolbar_tv= (TextView) findViewById(R.id.setting_toolbar_tv);
        setting_toolbar_tv.setText("系统设置");
        setting_title_ib.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_title_ib:
                //按钮点击事件
                Intent i=new Intent(this,SettingActivity.class);
                startActivity(i);
            break;
        }
    }
}
