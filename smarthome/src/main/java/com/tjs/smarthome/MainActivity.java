package com.tjs.smarthome;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tjs.smarthome.base.BaseActivity;
import com.tjs.smarthome.base.BaseFragment;
import com.tjs.smarthome.factory.FragmentFactory;
import com.tjs.smarthome.view.MyWebview;


public class MainActivity extends BaseActivity implements View.OnClickListener ,RadioGroup.OnCheckedChangeListener{
    private Toolbar maintl;
    private RadioGroup main_rg;
    private FragmentManager mFragmentManager;
    private ImageButton main_title_ib;//title的设置按钮
    private long exitTime=0;
    private ViewPager main_vp;//内容部分的ViewPager
    MyAdapter mAdapter;
    private TextView main_toolbar_tv;//toolbar文字

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager=getSupportFragmentManager();
        //之所以初始化，就是为了传递布尔值，以方便动态控制是否拦截分发事件
        new MyWebview(this,false);//true的话，webview拦截事件。
        initView();
        initControoler();
    }



    /**
     * 初始化view
     */
    public void initView() {
        maintl= (Toolbar) findViewById(R.id.main_toolbar);
        main_title_ib= (ImageButton) maintl.findViewById(R.id.main_title_ib);
        main_rg= (RadioGroup) findViewById(R.id.main_rg);
        //设置titile为空，并设置toobal到ActionBar，实现字体居中且可控制
//        maintl.setTitle("");
//        setSupportActionBar(maintl);
        main_vp= (ViewPager) findViewById(R.id.main_vp);
        main_title_ib.setOnClickListener(this);
        main_rg.setOnCheckedChangeListener(this);
        main_toolbar_tv= (TextView)findViewById(R.id.main_toolbar_tv);
        // 设置viewpager的预加载数目
        main_vp.setOffscreenPageLimit(4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_title_ib:
                //登录验证，暂时关闭
                checkUserAndPwd();
//                startActivity(new Intent(MainActivity.this,SettingActivity.class));
            break;
        }
    }

    /**
     * 验证登录
     */
    private void checkUserAndPwd() {
        Intent i=new Intent(this,LoginActivity.class);
        startActivity(i);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_rg_home:
                main_vp.setCurrentItem(0);
                //设置toolbar文字
                int color = Resources.getSystem().getColor(android.R.color.white);
                main_toolbar_tv.setTextColor(color);
                main_toolbar_tv.setText(getResources().getString(R.string.title_home));
                break;
            case R.id.main_rg_quyu:
                main_vp.setCurrentItem(1);
                main_toolbar_tv.setText(getResources().getString(R.string. bottom_quyu));
                break;
            case R.id.main_rg_shebei:
                main_vp.setCurrentItem(2);
                main_toolbar_tv.setText(getResources().getString(R.string.bottom_shebei));
                break;
            case R.id.main_rg_anfang:
                main_vp.setCurrentItem(3);
                main_toolbar_tv.setText(getResources().getString(R.string.bottom_anfang));
                break;
            //
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * 初始化viewpager控制
     */
    private void initControoler() {
        mAdapter = new MyAdapter(getSupportFragmentManager());
        main_vp.setAdapter(mAdapter);
        main_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        main_rg.check(R.id.main_rg_home);
                        break;
                    case 1:
                        main_rg.check(R.id.main_rg_quyu);
                        break;
                    case 2:
                        main_rg.check(R.id.main_rg_shebei);
                        break;
                    case 3:
                        main_rg.check(R.id.main_rg_anfang);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 适配器
     */

    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            BaseFragment fragment = FragmentFactory.getChartFragment(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(position);
        }
    }


}
