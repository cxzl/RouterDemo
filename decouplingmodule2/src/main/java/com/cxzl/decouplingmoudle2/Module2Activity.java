package com.cxzl.decouplingmoudle2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cxzl.basemodule.ICallbackProvider;

import static com.cxzl.basemodule.Constants.CallbackPath;

@Route(path = "/module2/activity")
public class Module2Activity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module2);

        findViewById(R.id.tv_route_callback).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String callbackPath = getIntent().getStringExtra(CallbackPath);
            if(callbackPath == null){
                Toast.makeText(Module2Activity.this,"没有回调路径",Toast.LENGTH_LONG).show();
            }else{
                finish();
                ICallbackProvider iCallbackProvider = (ICallbackProvider) ARouter.getInstance()
                        .build(callbackPath)
                        .navigation();
                iCallbackProvider.onCallback("Module2Activity回调的数据");
            }
        }
    };
}
