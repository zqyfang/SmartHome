package com.tjs.smarthome;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.tjs.smarthome.base.BaseActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SheBeiInfoActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar setting_item_toolbar_one;
    private ImageButton ib;
    private TextView tv;
    private ExpandableListView ex_listview;
    private List<String> datas=new ArrayList<>();
    //相关数据
    private Map<String, List<String>> dataset = new HashMap<>();
    private String[] parentList = new String[]{"一层灯光模块", "二层灯光模块", "三层灯光模块"};
    private String[] guding_title=new String[]{"类型","设备码","描述"};
    private List<String> childrenList1 = new ArrayList<>();
    private List<String> childrenList2 = new ArrayList<>();
    private List<String> childrenList3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_bei_info);
        initView();
        initData();
        ex_listview.setAdapter(new MyExpandableListViewAdapter());
    }

    /**
     * 初始化数据
     */
    private void initData() {
        childrenList1.add("路继电器模块");
        childrenList1.add( "0SAKJSADBK");
        childrenList1.add("控制一层灯光");
        childrenList2.add("8路继电器模块");
        childrenList2.add( "0SAKJSADBK");
        childrenList2.add( "控制二层灯光");
        childrenList3.add("8路继电器模块");
        childrenList3.add("0SAKJSADBK");
        childrenList3.add("控制三层灯光");
        dataset.put(parentList[0], childrenList1);
        dataset.put(parentList[1], childrenList2);
        dataset.put(parentList[2], childrenList3);
    }


    @Override
    public void initView() {
        setting_item_toolbar_one= (Toolbar) findViewById(R.id.setting_item_toolbar_one);
        tv= (TextView) setting_item_toolbar_one.findViewById(R.id.setting_toolbar_tv);
        tv.setText("设备信息");
        ib= (ImageButton) setting_item_toolbar_one.findViewById(R.id.setting_title_ib);
        ib.setOnClickListener(this);
        //找到可折叠的ExpandableListView的id
        ex_listview= (ExpandableListView) findViewById(R.id.ex_listview);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_title_ib:
                //回转设置界面
                Intent i=new Intent(this,SettingActivity.class);
                startActivity(i);
            break;
        }
    }

    private class MyExpandableListViewAdapter extends BaseExpandableListAdapter{
        @Override
        public int getGroupCount() {
            return dataset.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return dataset.get(parentList[groupPosition]).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return dataset.get(parentList[groupPosition]);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return dataset.get(parentList[groupPosition]).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater=getLayoutInflater();
                convertView = inflater.inflate(R.layout.parent_item, null);
            }
            convertView.setTag(R.layout.parent_item, groupPosition);
            convertView.setTag(R.layout.child_item, -1);
            TextView text = (TextView) convertView.findViewById(R.id.parent_title);
            text.setText(parentList[groupPosition]);
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater=getLayoutInflater();
                convertView = inflater.inflate(R.layout.child_item, null);
            }
            convertView.setTag(R.layout.parent_item, groupPosition);
            convertView.setTag(R.layout.child_item, childPosition);
            TextView text_title= (TextView) convertView.findViewById(R.id.child_title);
            TextView text_content= (TextView) convertView.findViewById(R.id.child_content);
            //设置子标题
            text_title.setText(guding_title[childPosition]);
            //设置子内容
            text_content.setText(dataset.get(parentList[groupPosition]).get(childPosition));
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }
}
