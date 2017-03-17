package com.tjs.smarthome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.socks.library.KLog;
import com.tjs.smarthome.bean.Login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends Activity implements View.OnClickListener {
    private ImageButton login_ib_close,show_user_ib;
    private ListView ppw_lv;
    private PopupWindow popupWindow;
    private LinearLayout user_text;//登录输入框
    private RelativeLayout activity_login;
    private UserAdapter ua;
    private List<String> listItem;
    private Button change_password_btn,login;
    private EditText password_et,username_et;//用户名和密码输入框
    private String testIp="http://192.168.0.106:8102/api/App";//测试接口地址
    private String username,password;
    private Login login_bean;
    boolean flag;//用户列表是否显示的判断标志
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initControls();
    }

    /**
     * 初始化listview控制
     */
    private void initControls() {
        //动态获取输入框的宽度，以便设置弹出框的宽度
        activity_login.measure(0,0);

        LayoutInflater inflater=LayoutInflater.from(this);
        View v=inflater.inflate(R.layout.pop_window,null);
        ppw_lv= (ListView) v.findViewById(R.id.ppw_lv);
        ua=new UserAdapter(LoginActivity.this,getData());
        ppw_lv.setAdapter(ua);
        //自适配长、框设置
        popupWindow = new PopupWindow(v, activity_login.getMeasuredWidth(),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.text_background));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        popupWindow.update();
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        //设置条目点击事件
        ppw_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //得到以前的数据，并点击回显
               String info=listItem.get(position).toString();
                username_et.setText(info);
                popupWindow.dismiss();
            }
        });

    }

    private void initView() {
        login_ib_close= (ImageButton) findViewById(R.id.login_ib_close);
        show_user_ib= (ImageButton) findViewById(R.id.show_user_ib);
        user_text= (LinearLayout) findViewById(R.id.user_text);
        activity_login= (RelativeLayout) findViewById(R.id.activity_login);
        //初始化登录按钮
        login= (Button) findViewById(R.id.login);
        // 初始化用户名密码输入框
        password_et= (EditText) findViewById(R.id.password_et);
        username_et= (EditText) findViewById(R.id.username_et);
        //设置点击事件
        login_ib_close.setOnClickListener(this);
        show_user_ib.setOnClickListener(this);
        //点击按钮进入设置界面
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_ib_close:
                //关闭登录框
                finish();
            break;
            case R.id.show_user_ib:
                //显示popuwindos显示在那个控件下方,参数前两个为偏移量，最后一个为对齐方式
                if (flag) {
                    Toast.makeText(this, "无登录记录！", Toast.LENGTH_SHORT).show();
                }else{
                    popupWindow.showAsDropDown(user_text, 0, 0,Gravity.RIGHT);
                }
                break;
            case R.id.login:
                getUerAndPass();
                break;
            case R.id.change_password_btn://修改密码按钮
                
        }
    }


    /**
     * 登录
     */
    private void loginSend() {
        new Thread(){
            @Override
            public void run() {
                //完整url拼接
                String url=testIp+"/Login?"+"aid=TJS_App_3.0"+"&name="+username+"&pwd="+password;
                OkGo.get(url).tag(this).cacheKey("Login").cacheMode(CacheMode.DEFAULT)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                Gson gson=new Gson();
                                login_bean=gson.fromJson(s, Login.class);
                                int id=login_bean.getStatusCode();
                                checkLogin(id);
                            }
                        });
            }
        }.start();
    }

    /**
     * 检查登录是否成功
     */
    private void checkLogin(int id) {
        if (id==200) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,SettingActivity.class));
                    finish();

                }
            });
        }else{
            Toast.makeText(LoginActivity.this, "出现错误！", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 得到用户名和密码
     */
    private void getUerAndPass() {
        //得到输入框的用户名和密码
        username=username_et.getText().toString().trim();
        password=password_et.getText().toString().trim();
        if (username.equals("")&&password.equals("")) {
            startActivity(new Intent(this,SettingActivity.class));
            //Toast.makeText(this, "帐号或密码不能为空!", Toast.LENGTH_SHORT).show();
        }else{
            loginSend();
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //屏蔽系统返回键会关闭当前登录activity
        if(keyCode == KeyEvent.KEYCODE_BACK){
            showDialog(2);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 用户名列表对应的数据适配器
     */
    private class UserAdapter extends BaseAdapter {
        private LayoutInflater inflater;//得到一个LayoutInfalter对象用来导入布局
        List<String> list;
        /**构造函数*/
        public UserAdapter(Context context, List<String> list) {
            this.inflater = LayoutInflater.from(context);
            this.list=list;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.user_item,null);
                holder = new ViewHolder();
                /**得到各个控件的对象*/
                holder.user_name_tv = (TextView) convertView.findViewById(R.id.user_name_tv);
                holder.del_username_ib = (ImageButton) convertView.findViewById(R.id.del_username_ib);
                convertView.setTag(holder);//绑定ViewHolder对象
            }
            else{
                holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
            }
            /**设置TextView显示的内容，即我们存放在动态数组中的数据*/
            holder.user_name_tv.setText(getItem(position).toString());
            holder.del_username_ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //删除不需要的用户名
//                    list.remove(getItem(position));此种方式和下面的方式都可以
                    list.remove(list.get(position));
                    ua.notifyDataSetChanged();
                    //当用户名记录全部删除之后，隐藏下拉框
                    if (list.size()==0) {
                        flag=true;//这只是一个标志，为的是当用户列表为空的时候，再次点击下拉，列表不显示
                        popupWindow.dismiss();
                    }

                }
            });


            return convertView;
        }
    }
    /**数据存储*/
    private List<String> getData(){
        listItem = new ArrayList<>();
        /**为动态数组添加数据*/
        for(int i=0;i<3;i++)
        {
            listItem.add("条目"+i);
        }
        return listItem;
    }
    /**存放控件*/
    public final class ViewHolder{
        public TextView user_name_tv;
        public ImageButton   del_username_ib;
    }
}
