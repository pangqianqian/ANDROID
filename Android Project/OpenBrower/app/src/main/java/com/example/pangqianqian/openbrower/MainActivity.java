package com.example.pangqianqian.openbrower;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.btn_click);
        final EditText editText=(EditText)findViewById(R.id.et_uri);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(editText.getText().toString()));
                startActivity(intent);
            }
        });
    }
}
