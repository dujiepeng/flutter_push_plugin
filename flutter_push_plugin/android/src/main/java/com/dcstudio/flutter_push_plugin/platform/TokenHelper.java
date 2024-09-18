package com.dcstudio.flutter_push_plugin.platform;

import android.content.Context;

public abstract class TokenHelper {
    protected Context context;

    public TokenHelper(Context context) {
        this.context = context;
    }

    public abstract void init();
    public abstract void unInit();
}
