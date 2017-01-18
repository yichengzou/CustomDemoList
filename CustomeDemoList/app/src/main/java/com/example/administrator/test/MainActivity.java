package com.example.administrator.test;


import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etAge;
    private ListView mList;
    private Realm mDbUtil;
    MyAdapter adapter;
    private TextView timeShow;

    private SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Handler mHandler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            if (mHandler == null) return;
            timeShow.setText(String.valueOf(sp.format(new Date(System.currentTimeMillis()))));
            mHandler.sendEmptyMessageDelayed(1, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = (EditText) findViewById(R.id.et_name);
        etAge = (EditText) findViewById(R.id.et_age);
        mList = (ListView) findViewById(R.id.list);
        timeShow = (TextView) findViewById(R.id.show_time);
        mHandler.sendEmptyMessageDelayed(1, 1000);
        mDbUtil = Realm.getDefaultInstance();
        RealmResults<Person> all = mDbUtil.where(Person.class).findAll();
        if (all.size() != 0) {
            Person person = all.get(0);
            etAge.setText(String.valueOf(person.getAge()));
            etName.setText(person.getName());
            etName.setSelection(person.getName().length());
        }
        adapter = new MyAdapter(all);
        mDbUtil.addChangeListener(new RealmChangeListener(){
            @Override
            public void onChange(Object element) {
                adapter.notifyDataSetChanged();
            }
        });
        mList.setAdapter(adapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDbUtil.beginTransaction();
                Person p = adapter.getItem(position);
                p.deleteFromRealm();
                mDbUtil.commitTransaction();
                adapter.notifyDataSetChanged();

            }
        });
    }

    public void save(View view) {
        if (TextUtils.isEmpty(etName.getText().toString())) {
            Toast.makeText(this, "请填写用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(etAge.getText().toString())) {
            Toast.makeText(this, "请填写年龄", Toast.LENGTH_SHORT).show();
            return;
        }
        mDbUtil.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Person p = realm.where(Person.class).equalTo("name", etName.getText().toString()).findFirst();
                if (p == null) {
                    p=realm.createObject(Person.class,adapter.getCount());
                }
                p.setName(etName.getText().toString());
                p.setAge(Integer.parseInt(etAge.getText().toString()));
                realm.copyToRealmOrUpdate(p);
            }
        });

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    public void modif(View view) {

    }


    static class MyAdapter extends BaseAdapter {
        RealmResults<Person> list;

        public MyAdapter(RealmResults<Person> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Person getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.test_list_item, null);
            }
            Person person = list.get(position);
            TextView text = (TextView) convertView.findViewById(android.R.id.text1);
            text.setText(String.format("name=%1s\t age=%2s\t id=%3s", person.getName(), person.getAge(), person.getId()));
            return convertView;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler = null;
    }
}
