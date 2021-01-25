package com.siren.liu.click.repeat;

import android.view.View;

/**
 * Created by LiuG on 2019-06-06.
 */
public final class RepeatHelper {

    private volatile long lastClickTime;   //最近一次点击的时间
    private volatile int lastClickViewId;  //最近一次点击的控件ID

    private RepeatHelper() {
    }

    public static RepeatHelper getInstance() {
        return Singleton.INSTANCE;
    }

    private static final class Singleton {
        private static final RepeatHelper INSTANCE = new RepeatHelper();
    }

    public boolean checkRepeat(View view, long delay, long delayViews) {
        final int viewId = view.getId();
        final long currentTime = System.currentTimeMillis();
        final long timeInterval = Math.abs(currentTime - lastClickTime);
        if (viewId == lastClickViewId && timeInterval < delay) {
            return true;
        }
        if (viewId != lastClickViewId && timeInterval < delayViews) {
            return true;
        }
        lastClickTime = currentTime;
        lastClickViewId = viewId;
        return false;
    }

}
