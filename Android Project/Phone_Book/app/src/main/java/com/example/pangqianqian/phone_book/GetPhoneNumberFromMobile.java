package com.example.pangqianqian.phone_book;

import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
import android.provider.ContactsContract.CommonDataKinds.Phone;

/**
 * Created by pangqianqian on 2017/11/19.
 */

public class GetPhoneNumberFromMobile {

    private List<PhoneInfo> list;

    public List<PhoneInfo> getPhoneNumberFromMobile(Context context) {

        list = new ArrayList<PhoneInfo>();
        Cursor cursor = context.getContentResolver().query(Phone.CONTENT_URI,
                null, null, null, null);
        //moveToNext方法返回的是一个boolean类型的数据
        while (cursor.moveToNext()) {
            //读取通讯录的姓名
            String name = cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME));
            //读取通讯录的号码
            String number = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
            PhoneInfo phoneInfo = new PhoneInfo(name, number);
            list.add(phoneInfo);
        }
        return list;
    }
}
