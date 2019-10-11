package com.cxzl.basemodule;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/base/login")
public class LoginActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.tv_login_success).setOnClickListener(onClickListener);
        findViewById(R.id.tv_login_fail).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            if(id == R.id.tv_login_success){
                LoginManager.getInstance().notifyLoginSuccess("{用户：cxzl，手机：12345678910}");
                finish();
            }else if(id == R.id.tv_login_fail){
                LoginManager.getInstance().notifyLoginFail();
                finish();
            }
        }
    };
}
