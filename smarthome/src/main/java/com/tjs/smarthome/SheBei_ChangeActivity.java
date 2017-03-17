package com.tjs.smarthome;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tjs.smarthome.base.BaseActivity;
import com.tjs.smarthome.zxing.android.CaptureActivity;

public class SheBei_ChangeActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton setting_title_ib,im_change_pre,im_change_new;//标题按钮，两个扫描按钮
    private TextView setting_toolbar_tv,tv_pre;//标题，老设备码
    private EditText et_new;//新设备码
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private static final int REQUEST_CODE_PRE= 0x0000;
    private static final int REQUEST_CODE_NEW= 0x0001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_bei__change);
        initView();
    }

    @Override
    public void initView() {
        setting_title_ib= (ImageButton) findViewById(R.id.setting_title_ib);
        setting_toolbar_tv= (TextView) findViewById(R.id.setting_toolbar_tv);
        im_change_pre= (ImageButton) findViewById(R.id.im_change_pre);
        im_change_new= (ImageButton) findViewById(R.id.im_change_new);
        et_new= (EditText) findViewById(R.id.et_new);
        tv_pre= (TextView) findViewById(R.id.tv_pre);
        setting_toolbar_tv.setText("设备更换");
        setting_title_ib.setOnClickListener(this);
        im_change_pre.setOnClickListener(this);
        im_change_new.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_title_ib:
                //按钮点击事件
                Intent i=new Intent(this,SettingActivity.class);
                startActivity(i);
            break;
            case R.id.im_change_pre:
                saomiao(REQUEST_CODE_PRE);
                break;
            case R.id.im_change_new:
                saomiao(REQUEST_CODE_NEW);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //二维码信息回传
        if (requestCode == REQUEST_CODE_PRE && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
//              Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);
                Toast.makeText(this, ""+content, Toast.LENGTH_SHORT).show();
                tv_pre.setText( content);
            }
        }else if (requestCode == REQUEST_CODE_NEW && resultCode == RESULT_OK){
            if (data != null) {
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
//              Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);
                et_new.setText(content);
            }
        }
    }
    public void saomiao(int code){
        //跳转到扫描界面
        Intent ii=new Intent(this, CaptureActivity.class);
        startActivityForResult(ii, code);
    }
}
