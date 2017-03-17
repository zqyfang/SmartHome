package com.tjs.smarthome;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tjs.smarthome.base.BaseActivity;

public class ZhuKongQiActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar tl;
    private ImageButton ib_add,setting_title_ib,del_item;
    private LinearLayout zhukongqi_ll_top,zhukongqi_hideorshow_item;
    private TextView setting_toolbar_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_kong_qi);
        initView();
    }

    @Override
    public void initView() {
        tl= (Toolbar) findViewById(R.id.setting_item_toolbar_two);
        //查询toolbar的textview的ID
        setting_toolbar_tv= (TextView) tl.findViewById(R.id.setting_toolbar_tv);
        setting_toolbar_tv.setText("主控器信息");

        ib_add= (ImageButton) tl.findViewById(R.id.ib_add);
        setting_title_ib= (ImageButton) tl.findViewById(R.id.setting_title_ib);
        ib_add.setOnClickListener(this);
        setting_title_ib.setOnClickListener(this);
        //主控器的分条目显示最外层线性布局
        zhukongqi_ll_top= (LinearLayout) findViewById(R.id.zhukongqi_ll_top);
        //获取分条目显示的添加布局
        zhukongqi_hideorshow_item= (LinearLayout) zhukongqi_ll_top.findViewById(R.id.zhukongqi_hideorshow_item);
        //获取删除按钮id
        del_item= (ImageButton) zhukongqi_hideorshow_item.findViewById(R.id.del_item);
        del_item.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_add:
                zhukongqi_hideorshow_item.setVisibility(View.VISIBLE);
            break;
            case R.id.setting_title_ib:
                //返回设置界面
                Intent i=new Intent(this,SettingActivity.class);
                startActivity(i);
                break;
            case R.id.del_item:
                showMyDialog();
                break;
        }
    }

    /**
     * 显示选择对话框
     */
    private void showMyDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("确认删除此条目吗？");

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                zhukongqi_hideorshow_item.setVisibility(View.GONE);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ZhuKongQiActivity.this, "无操作", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }
}
