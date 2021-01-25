package com.siren.liu.click.onclick;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

/**
 * Created by LiuG on 2019-06-06.
 */
public abstract class MultipleClickListener implements View.OnClickListener {
    private static final int SINGLE_CLICK_MESSAGE = 0xFF;
    private static final int DOUBLE_CLICK_DELAY_TIME = 200;
    private long lastClickTime = 0;

    private Handler mainHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            View view = (View) msg.obj;
            onSingleClick(view);
        }
    };

    @Override
    public final void onClick(final View view) {
        final long currentTime = System.currentTimeMillis();
        final long timeInterval = Math.abs(currentTime - lastClickTime);
        if (timeInterval < doubleMark()) {
            lastClickTime = 0;
            onDoubleClick(view);
            //单双击事件同时存在时，如果判断为双击则不再执行单击事件的逻辑
            mainHandler.removeMessages(SINGLE_CLICK_MESSAGE);
            return;
        }

        lastClickTime = currentTime;
        Message msg = Message.obtain();
        msg.what = SINGLE_CLICK_MESSAGE;
        msg.obj = view;
        //延迟执行单击事件，因为后续可能会触发双击事件
        mainHandler.sendMessageDelayed(msg, doubleMark() + 1);
    }

    protected int doubleMark() {
        return DOUBLE_CLICK_DELAY_TIME;
    }

    protected abstract void onSingleClick(View view);

    protected abstract void onDoubleClick(View view);
}
