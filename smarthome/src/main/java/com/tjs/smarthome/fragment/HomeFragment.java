package com.tjs.smarthome.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;


import com.socks.library.KLog;
import com.tjs.smarthome.MainActivity;
import com.tjs.smarthome.R;
import com.tjs.smarthome.base.BaseFragment;
import com.tjs.smarthome.view.MyWebview;

/**
 * Created by tjsmc on 17/2/15.
 */

public class HomeFragment extends BaseFragment {
    View view;
    private WebView wv;
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
        wv= (WebView) view.findViewById(R.id.wv);
        WebSettings ws=wv.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
//        wv.loadUrl("http://www.163.com");
        wv.loadUrl("file:///android_asset/index.html");
    }
}
