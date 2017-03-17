package com.tjs.smarthome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.tjs.smarthome.base.BaseActivity;

public class SettingActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView setting_lv;
    private Toolbar setting_toolbar;
    private ImageButton setting_title_ib;
    private String[] seting_lv_text={"项目信息","主控器信息","设备信息","位号信息","app专用逻辑","app专用场景"
    ,"虚拟设备","云服务器设置","设备更换","系统设置"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    @Override
    public void initView() {
        setting_lv= (ListView) findViewById(R.id.setting_lv);
        setting_toolbar= (Toolbar) findViewById(R.id.setting_toolbar);
        setting_title_ib= (ImageButton) setting_toolbar.findViewById(R.id.setting_title_ib);
        //把toolbar设置到顶部
//        setSupportActionBar(setting_toolbar);
        //设置按钮点击事件
        setting_title_ib.setOnClickListener(this);
        SettingAdapter sad=new SettingAdapter(this);
        //设置适配器
        setting_lv.setAdapter(sad);
        //设置listview条目点击事件
        setting_lv.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_title_ib:
                //跳回到主页
                Intent i=new Intent(SettingActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                Intent i=new Intent(this,XiangmuInfoActivity.class);
                startActivity(i);
                break;
            case 1:
                Intent ii=new Intent(this,ZhuKongQiActivity.class);
                startActivity(ii);
                break;
            case 2:
                Intent iii=new Intent(this,SheBeiInfoActivity.class);
                startActivity(iii);
                break;
            case 3:
                Intent iv=new Intent(this,WeiHaoInfoActivity.class);
                startActivity(iv);
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                Intent six=new Intent(this,InverntActivity.class);
                startActivity(six);
                break;
            case 7:
                Intent seven=new Intent(this,ServiceActivity.class);
                startActivity(seven);
                break;
            case 8:
                Intent eight=new Intent(this,SheBei_ChangeActivity.class);
                startActivity(eight);
                break;
            case 9:
                Intent nine=new Intent(this,SystemActivity.class);
                startActivity(nine);
                break;
        }
    }


    private class SettingAdapter extends BaseAdapter {
        private LayoutInflater mInflater = null;
        private SettingAdapter(Context context)
        {
            //根据context上下文加载布局
            this.mInflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return seting_lv_text.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView=mInflater.inflate(R.layout.setting_item,null);
            }
            TextView  tv= (TextView) convertView.findViewById(R.id.tv);
            tv.setText(seting_lv_text[position]);
            return convertView;
        }
    }
}
