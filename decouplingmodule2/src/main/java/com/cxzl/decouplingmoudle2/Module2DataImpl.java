package com.cxzl.decouplingmoudle2;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cxzl.basemodule.IDataProvider;

@Route(path = "/module2/data")
public class Module2DataImpl implements IDataProvider{

    @Override
    public String getData() {
        return "你获取到"+getClass().getSimpleName()+"的数据";
    }

    @Override
    public void init(Context context) {

    }
}
