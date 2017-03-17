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

public class AnFangFragment extends BaseFragment {
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
        //js脚本可用
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return false;
            }
        });
//        wv.loadUrl("http://www.baidu.com");
    }
}
