package com.example.administrator.hellocurrencies;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class register extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将一个正在创建的活动添加到活动管理器中
        ActivityCollector.addActivity(this);
        setContentView(R.layout.activity_register);

        //点击注册，跳转至主页
        Button btn_register=(Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                EditText editText1 = (EditText) findViewById(R.id.re_name);
                EditText editText2 = (EditText) findViewById(R.id.re_pwd);
                //判断用户注册是否输入了密码和用户名
                if (editText1.getText().length() == 0 || editText2.getText().length() == 0) {
                    Toast.makeText(register.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    //判断是否已经注册了相同用户名
                    boolean flag = false;
                    List<User> users = DataSupport.findAll(User.class);
                    for (User user : users) {
                        if (editText1.getText().toString().equals(user.getUsername())) {
                            flag = true;
                        }
                    }
                    if (flag) {
                        Toast.makeText(register.this, "该用户名已经注册", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //新建LitePal数据库
                        Connector.getDatabase();
                        User user = new User();
                        String newname = editText1.getText().toString();
                        String newpwd = editText2.getText().toString();
                        //存储用户名密码
                        user.setUsername(newname);
                        user.setPassword(newpwd);
                        user.save();
                        Toast.makeText(register.this, "注册成功", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(register.this, MainActivity.class);
                        //传递用户名到下一活动，用于显示
                        intent.putExtra("userName",newname);
                        startActivity(intent);
                    }

                }
            }

        });
    }

    //表明一个要销毁的活动从活动管理器里移除
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }


}
