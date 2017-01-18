package com.example.administrator.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/1/17 0017.
 */

public class CustomGroup extends FrameLayout {
    public CustomGroup(Context context) {
        super(context);
    }

    public CustomGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("CustomGroup", "dispatchTouchEvent ");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("CustomGroup", "onInterceptTouchEvent ");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("CustomGroup", "onTouchEvent ");
        return super.onTouchEvent(event);
    }
}
