package com.example.administrator.hellocurrencies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

import static android.R.attr.password;

public class login extends AppCompatActivity {
    private EditText editText1;
    private EditText editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //将正在创建的活动添加到活动管理器里
        ActivityCollector.addActivity(this);
        setContentView(R.layout.activity_login);

        //点击登录，跳转至主页
        Button btn_login=(Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //得到用户名和密码的编辑框
                editText1 = (EditText)findViewById(R.id.name);
                editText2 = (EditText)findViewById(R.id.pwd);

                //判断是否成功匹配的标志
                boolean flag = false;
                //LitePal里遍历查询所有数据的方法
                List<User> users = DataSupport.findAll(User.class);
                for (User user : users){
                    //判断用户输入的用户名和密码是否与数据库中相同
                    if(user.getUsername().equals(editText1.getText().toString())&&
                            user.getPassword().equals(editText2.getText().toString())) {
                        flag = true;
                    }
                }
                if(flag) {
                    //创建Intent对象，传入源Activity和目的Activity的类对象
                    Intent intent = new Intent(login.this, SplashActivity.class);
                    //传递用户名到下一活动，用于显示
                    String USERNAME = editText1.getText().toString();
                    intent.putExtra("userName",USERNAME);

                    startActivity(intent);
                }
                else{
                    //登录信息错误，通过Toast显示提示信息
                    Toast.makeText(login.this,"用户登录信息错误" , Toast.LENGTH_SHORT).show();
                }
            }
        });

        //点击立即注册，跳转至注册注册页面
        Button btn_toregister=(Button) findViewById(R.id.btn_toregister);
        btn_toregister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(login.this,register.class);
                startActivity(intent);
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
