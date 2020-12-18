package com.example.finalhomework;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalhomework.bean.UserInfo;
import com.example.finalhomework.database.UserDBHelper;
import com.example.finalhomework.util.DateUtil;

public class SQLiteWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private UserDBHelper mHelper; // 声明一个用户数据库帮助器的对象
    private EditText et_name;
    private EditText et_phoneNumber;
    private EditText et_password;


    // private boolean bMarried = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_write);

        et_name = findViewById(R.id.et_name);
        et_phoneNumber = findViewById(R.id.et_phoneNumber);
        et_password = findViewById(R.id.et_password);

        //  et_weight = findViewById(R.id.et_weight);
        findViewById(R.id.btn_save).setOnClickListener(this);

        // Spinner sp_married = findViewById(R.id.sp_married);
        //   sp_married.setOnItemSelectedListener(new TypeSelectedListener());
    }

    /*class TypeSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            bMarried = (arg2 == 0) ? false : true;
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }*/

    @Override
    protected void onStart() {
        super.onStart();
        // 获得数据库帮助器的实例
        mHelper = UserDBHelper.getInstance(this, 2);
        // 打开数据库帮助器的写连接
        mHelper.openWriteLink();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 关闭数据库连接
        mHelper.closeLink();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save) {
            String name = et_name.getText().toString();
            String phoneNumber = et_phoneNumber.getText().toString();
            String password = et_password.getText().toString();
            //  String weight = et_weight.getText().toString();
            if (TextUtils.isEmpty(name)) {
                showToast("请先填写姓名");
                return;
            } else if (TextUtils.isEmpty(phoneNumber)) {
                showToast("请先填写手机号");
                return;
            } else if (TextUtils.isEmpty(password)) {
                showToast("请先填写密码");
                return;
            }

            // 以下声明一个用户信息对象，并填写它的各字段值
            UserInfo info = new UserInfo();
            info.name = name;
            info.phone = phoneNumber;
            info.pwd = password;
            info.update_time = DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss");

            // 执行数据库帮助器的插入操作
            mHelper.insert(info);

            //显示“注册成功”  并跳转到登陆页面
            showToast("注册成功");

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

            //Log.d()
        }
    }

    private void showToast(String desc) {
        Toast.makeText(this, desc, Toast.LENGTH_SHORT).show();
    }
}

