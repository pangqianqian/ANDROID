package com.example.pangqianqian.phone_book;

import android.Manifest;
import android.app.ActivityGroup;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.TabHost;


public class MainActivity extends ActivityGroup {

    private static final int MY_PERMISSION_REQUEST_CODE = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        request();
        //设置tabhost
        //initTabs();
    }

    private void initTabs() {
        TabHost mTabHost = (TabHost) findViewById(R.id.tabhost);
        mTabHost.setup(this.getLocalActivityManager());
        // 添加日志列表的tab,注意下面的setContent中的代码.是这个需求实现的关键
        mTabHost.addTab(mTabHost.newTabSpec("通讯录").setIndicator("通讯录").setContent(new Intent(this, Phone_book.class)));
        mTabHost.addTab(mTabHost.newTabSpec("拨号").setIndicator("拨号").setContent(new Intent(this, Call.class)));
    }

    /**
     * 需要2个权限(都是危险权限):
     * 1. 读取通讯录权限;
     * 2.拨打电话的权限
     */
    public void request() {
        /**
         * 第 1 步: 检查是否有相应的权限
         */
        boolean isAllGranted = checkPermissionAllGranted(
                new String[]{
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.CALL_PHONE
                }
        );
        // 如果这2个权限全都拥有, 则直接执行备份代码
        if (isAllGranted) {
            initTabs();
            return;
        }

        /**
         * 第 2 步: 请求权限
         */
        // 一次请求多个权限, 如果其他有权限是已经授予的将会自动忽略掉
        ActivityCompat.requestPermissions(
                this,
                new String[]{
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.CALL_PHONE
                },
                MY_PERMISSION_REQUEST_CODE
        );
    }

    /**
     * 检查是否拥有指定的所有权限
     */
    private boolean checkPermissionAllGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                // 只要有一个权限没有被授予, 则直接返回 false
                return false;
            }
        }
        return true;
    }

    /**
     * 第 3 步: 申请权限结果返回处理
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSION_REQUEST_CODE) {
            boolean isAllGranted = true;
            // 判断是否所有的权限都已经授予了
            for (int grant : grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false;
                    break;
                }
            }
            if (isAllGranted) {
                // 如果所有的权限都授予了, 则执行备份代码
                initTabs();

            } else {
                // 弹出对话框告诉用户需要权限的原因, 并引导用户去应用权限管理中手动打开权限按钮
                return;
            }
        }
    }
}
