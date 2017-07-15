package com.jiangdg.keepappalive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/** 登录界面
 *
 * Created by jianddongguo on 2017/7/7.
 * http://blog.csdn.net/andrexpert
 */
public class LoginActivity extends AppCompatActivity {
    private EditText mEdtUsrName;
    private EditText mEdtUsrPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEdtUsrName = (EditText)findViewById(R.id.etv_usrname);
        mEdtUsrPasswd = (EditText)findViewById(R.id.etv_usrpswd);
    }

    public void onLoginClick(View v){
//        if("jiang".equals(mEdtUsrName.getText().toString())
//                && "123".equals(mEdtUsrPasswd.getText().toString())){
//            Intent intent = new Intent(LoginActivity.this,SportsActivity.class);
//            startActivity(intent);
//        }
        Intent intent = new Intent(LoginActivity.this,SportsActivity.class);
        startActivity(intent);
    }
}
