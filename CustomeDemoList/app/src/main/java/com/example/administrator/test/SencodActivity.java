package com.example.administrator.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/1/17 0017.
 */

public class SencodActivity extends AppCompatActivity {
    TochListener mListener=new TochListener();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        findViewById(R.id.contair).setOnTouchListener(mListener);
        findViewById(R.id.text2).setOnTouchListener(mListener);
        findViewById(R.id.text1).setOnTouchListener(mListener);
        findViewById(R.id.text1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("view click","click");
            }
        });
    }



   static class TochListener implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    printl(v,"ACTION_DOWN");
                    break;
                case MotionEvent.ACTION_MOVE:
                    printl(v,"ACTION_MOVE");
                    break;
                case MotionEvent.ACTION_UP:
                    printl(v,"ACTION_UP");
                    break;
            }
            return false;
        }

       private void printl(View v, String action){
           switch (v.getId()){
               case R.id.contair:
                   Log.d(action,"contair ");
                   break;
               case R.id.text2:
                   Log.d(action,"text2 ");
                   break;
               case R.id.text1:
                   Log.d(action,"text1");
                   break;
           }
       }

   }
}
