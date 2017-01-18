package com.example.administrator.test;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/1/18 0018.
 */

public class DemoSelectActivity extends AppCompatActivity {

    private ListView mListView;
    private Adapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_select);
        mListView= (ListView) findViewById(R.id.list);
        mAdapter=new Adapter();
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                switch (mAdapter.getItem(position)){
                    case "RealmDemo":
                        intent.setClass(DemoSelectActivity.this,MainActivity.class);
                        break;
                    case "TouchEventDemo":
                        intent.setClass(DemoSelectActivity.this,SencodActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }


   static class Adapter extends BaseAdapter{

        private String[] items;

       public Adapter() {
           this.items = new String[]{"RealmDemo","TouchEventDemo"};
       }

       @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public String getItem(int position) {
            return items==null?null:items[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView= LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,null);
            }
            TextView title = (TextView) convertView.findViewById(android.R.id.text1);
            title.setText(items[position]);
            return convertView;
        }
    }
}
