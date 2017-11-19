package com.example.pangqianqian.phone_book;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.AdapterView;
import java.util.ArrayList;
import java.util.List;
import android.net.Uri;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;


public class Phone_book extends Activity implements AdapterView.OnItemClickListener {

    private ListView lv;
    private MyAdapter adapter;
    private GetPhoneNumberFromMobile getPhoneNumberFromMobile;
    private List<PhoneInfo> list = new ArrayList<PhoneInfo>();



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_book);
        lv = (ListView) findViewById(R.id.listView1);
        getPhoneNumberFromMobile = new GetPhoneNumberFromMobile();
        list = getPhoneNumberFromMobile.getPhoneNumberFromMobile(this);
        adapter = new MyAdapter(list, this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String number = list.get(position).getNumber();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.CALL");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }

}


