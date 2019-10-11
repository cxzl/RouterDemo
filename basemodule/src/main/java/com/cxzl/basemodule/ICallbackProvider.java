package com.cxzl.basemodule;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface ICallbackProvider extends IProvider {
    void onCallback(String data);
}
