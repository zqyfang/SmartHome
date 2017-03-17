package com.tjs.smarthome.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.tjs.smarthome.MainActivity;
import com.tjs.smarthome.R;
import com.tjs.smarthome.base.BaseFragment;
import com.tjs.smarthome.view.MyWebview;

/**
 * Created by tjsmc on 17/2/15.
 */

public class QuYuFragment extends BaseFragment {
    private MyWebview wv;
    View view;
    @Override
    public View initView() {

        return null;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.viewpager_content,null);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        wv= (MyWebview) view.findViewById(R.id.wv);
        wv.loadUrl("file:///android_asset/a.html");
    }



}
