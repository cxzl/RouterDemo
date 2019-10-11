package com.cxzl.basemodule;

import java.util.HashSet;
import java.util.Iterator;

public class LoginManager {
    private static LoginManager instance;
    private HashSet<ILoginListener> iLoginListeners = new HashSet<>();
    private boolean isLogin;
    private String userInfo;

    private LoginManager(){}
    public static LoginManager getInstance(){
        if(instance == null){
            instance = new LoginManager();
        }
        return instance;
    }

    public void addLoginListener(ILoginListener iLoginListener){
        iLoginListeners.add(iLoginListener);
    }

    public void removeLoginListener(ILoginListener iLoginListener){
        iLoginListeners.remove(iLoginListener);
    }

    public void removeLoginListener(){
        iLoginListeners.clear();
    }

    public void notifyLoginSuccess(String userInfo){
        this.userInfo = userInfo;
        isLogin = true;
        Iterator<ILoginListener> it =  iLoginListeners.iterator();
        while(it.hasNext()){
            ILoginListener iLoginListener = it.next();
            iLoginListener.loginSuccess(userInfo);
            it.remove();
        }
    }

    public void notifyLoginFail(){
        this.userInfo = null;
        isLogin = false;
        Iterator<ILoginListener> it =  iLoginListeners.iterator();
        while(it.hasNext()){
            ILoginListener iLoginListener = it.next();
            iLoginListener.loginFail();
            it.remove();
        }
    }

    public boolean isLogin(){
        return isLogin;
    }

    public String getUserInfo(){
        return userInfo;
    }
}
