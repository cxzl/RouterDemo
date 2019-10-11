package com.cxzl.basemodule;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;

@Interceptor(priority = 8, name = "login interceptor")
public class LoginInterceptor implements IInterceptor {
    String userInfo;
    @Override
    public void process(final Postcard postcard, final InterceptorCallback callback) {
        if(postcard.getExtras().getBoolean(Constants.NeedLogin,false)){
            LoginManager.getInstance().addLoginListener(new ILoginListener() {
                @Override
                public void loginSuccess(String userInfo) {
                    LoginInterceptor.this.userInfo = userInfo;
                    Log.i("userInfo success",userInfo);
                    callback.onContinue(postcard);
                }

                @Override
                public void loginFail() {
                    Log.i("userInfo fail",userInfo+"");
                    callback.onInterrupt(null);
                }
            });
            if(LoginManager.getInstance().isLogin()){
                userInfo = LoginManager.getInstance().getUserInfo();
                Log.i("userInfo",userInfo);
                callback.onContinue(postcard);
            }else{
                ARouter.getInstance().build("/base/login").navigation();
            }
        }else{
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {

    }
}
