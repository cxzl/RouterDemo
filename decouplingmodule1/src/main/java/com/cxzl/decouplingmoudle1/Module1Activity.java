package com.cxzl.decouplingmoudle1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cxzl.basemodule.Constants;
import com.cxzl.basemodule.ICallbackProvider;
import com.cxzl.basemodule.IDataProvider;

import static com.cxzl.basemodule.Constants.CallbackPath;
import static com.cxzl.basemodule.Constants.NeedLogin;

@Route(path = "/module1/activity")
public class Module1Activity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module1);
        findViewById(R.id.tv_route).setOnClickListener(onClickListener);
        findViewById(R.id.tv_route_with_interceptor).setOnClickListener(onClickListener);
        findViewById(R.id.tv_route_data).setOnClickListener(onClickListener);
        findViewById(R.id.tv_route_with_callback).setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.tv_route){
                ARouter.getInstance().build("/module2/activity").navigation();
            }else if(view.getId() == R.id.tv_route_with_interceptor){
                ARouter.getInstance().build("/module2/activity").withBoolean(NeedLogin, true)
                        .navigation(Module1Activity.this, new NavCallback() {
                            @Override
                            public void onArrival(Postcard postcard) {

                            }

                            @Override
                            public void onInterrupt(Postcard postcard) {
                                super.onInterrupt(postcard);
                            }
                        });
            }else if(view.getId() == R.id.tv_route_data){
                IDataProvider iDataProvider = (IDataProvider) ARouter.getInstance()
                        .build("/module2/data")
                        .navigation();
                Toast.makeText(Module1Activity.this,iDataProvider.getData(),Toast.LENGTH_LONG).show();
            }else if(view.getId() == R.id.tv_route_with_callback){
                ARouter.getInstance().build("/module2/activity")
                        .withString(CallbackPath,"/module1/callback")
                        .navigation();
            }
        }
    };

    @Route(path = "/module1/callback")
    public static class Module1CallBackImpl implements ICallbackProvider{

        @Override
        public void onCallback(String data) {
            Log.i("Module1CallBackImpl:",data);
        }

        @Override
        public void init(Context context) {

        }
    }
}
