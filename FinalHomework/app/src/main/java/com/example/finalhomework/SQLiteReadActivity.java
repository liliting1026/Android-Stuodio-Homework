package com.example.finalhomework;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalhomework.bean.UserInfo;
import com.example.finalhomework.database.UserDBHelper;

import java.util.ArrayList;

public class SQLiteReadActivity extends AppCompatActivity   implements View.OnClickListener {

    private UserDBHelper myHelper; // 声明一个用户数据库帮助器的对象
    private TextView tv_sqlite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_read);

        tv_sqlite = findViewById(R.id.tv_sqlite);
        findViewById(R.id.btn_delete).setOnClickListener(this);
    }

    // 读取数据库中保存的所有用户记录
    private void readSQLite() {
        if (myHelper == null) {
            showToast("数据库连接为空");
            return;
        }
        // 执行数据库帮助器的查询操作
        ArrayList<UserInfo> userArray = myHelper.query("1=1");
        String desc = String.format("数据库查询到%d条记录，详情如下：", userArray.size());
        for (int i = 0; i < userArray.size(); i++) {
            UserInfo info = userArray.get(i);
            desc = String.format("%s\n第%d条记录信息如下：", desc, i + 1);
            desc = String.format("%s\n　姓名为%s", desc, info.name);
            desc = String.format("%s\n　手机号码为%s", desc, info.phone);
            desc = String.format("%s\n　密码为%s", desc, info.pwd);
//          desc = String.format("%s\n　更新时间为%s", desc, info.update_time);
        }
        if (userArray.size() <= 0) {
            desc = "数据库查询到的记录为空";
        }
        tv_sqlite.setText(desc);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 获得数据库帮助器的实例
        myHelper = UserDBHelper.getInstance(this, 2);
        // 打开数据库帮助器的读连接
        myHelper.openReadLink();
        readSQLite();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 关闭数据库连接
        myHelper.closeLink();
    }


    public void onClick(View v) {
        if (v.getId() == R.id.btn_delete) {
            // 关闭数据库连接
            myHelper.closeLink();
            // 打开数据库帮助器的写连接
            myHelper.openWriteLink();
            // 删除所有记录
            myHelper.deleteAll();
            // 关闭数据库连接
            myHelper.closeLink();
            // 打开数据库帮助器的读连接
            myHelper.openReadLink();
            readSQLite();
        }
    }

    private void showToast(String desc) {
        Toast.makeText(this, desc, Toast.LENGTH_SHORT).show();
    }
}
