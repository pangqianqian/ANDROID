package com.example.pangqianqian.phone_book;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Call extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        Button button = (Button) findViewById(R.id.btn_click);
        final EditText editText = (EditText) findViewById(R.id.et_number);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim() == null || editText.getText().toString().trim().equals("")) {
                    Toast.makeText(Call.this, "对不起，电话不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (editText.getText().toString().trim() != null && !(editText.getText().toString().trim().equals(""))) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
                            + editText.getText().toString().trim()));
                    if (ActivityCompat.checkSelfPermission(Call.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(intent);
                }
            }
        });
    }
}


