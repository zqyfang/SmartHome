package com.tjs.smarthome.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import com.github.lzyzsd.jsbridge.BridgeWebView;

/**
 * 暂时没有使用，因为要使用jsbridge。
 * Created by tjsmc on 17/3/3.
 */

public class MyWebview extends BridgeWebView {
//     加static的话是全局变量，不加的话，此时使用会一直是false。
    public static boolean mflag;  //基本数据类型，不随形式参数的改变而改变，只是值拷贝罢了，加了static才可行，才能变化值。
    public MyWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyWebview(Context context,boolean flag) {
        super(context);
        this.mflag=flag;
    }

    public MyWebview(Context context) {
        super(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //true响应webview的事件，false父view处理
        Log.e("flag的值是：",mflag+"");
        getParent().requestDisallowInterceptTouchEvent(mflag);
        return super.dispatchTouchEvent(ev);
    }
}
