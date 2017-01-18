package com.example.administrator.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/1/17 0017.
 */

public class TextView1 extends TextView {
    public TextView1(Context context) {
        super(context);
    }

    public TextView1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("TextView1", "dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    private int downX;
    private int downY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("TextView1", "onTouchEvent");
        int currentX = (int) event.getX();
        int currentY = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                downY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                ((ViewGroup) getParent()).scrollBy(-(currentX-downX), -(currentY-downY));
                break;
        }
        return super.onTouchEvent(event);
    }
}
