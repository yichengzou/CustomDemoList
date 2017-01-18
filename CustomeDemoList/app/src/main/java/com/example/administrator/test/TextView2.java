package com.example.administrator.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/1/17 0017.
 */

public class TextView2 extends TextView {
    public TextView2(Context context) {
        super(context);
    }

    public TextView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("TextView2","dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("TextView2","onTouchEvent");
        return super.onTouchEvent(event);
    }
}
