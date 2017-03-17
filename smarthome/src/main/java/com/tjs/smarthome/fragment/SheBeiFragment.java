package com.tjs.smarthome.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.tjs.smarthome.R;
import com.tjs.smarthome.base.BaseFragment;

/**
 * Created by tjsmc on 17/2/15.
 */

public class SheBeiFragment extends BaseFragment {
    private WebView wv;
    View view;

    @Override
    public View initView() {
        //更改toolbar的文字

        return null;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.viewpager_content,null);// 此处不能把null变为container，不然会内存溢出
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
//        wv.loadUrl("http://www.sina.com");
    }
}
