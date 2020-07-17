package com.me.slone.wan.network.progress;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.hjq.base.BaseDialog;
import com.me.slone.dialog.WaitDialog;


public class ProgressDialogHandler extends Handler {

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;

    private BaseDialog mLoadingDialog;

    public ProgressDialogHandler(Context context, ProgressCancelListener mProgressCancelListener,
                                 boolean cancelable) {
        super(Looper.getMainLooper());
        this.context = context;
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }

    private void initProgressDialog() {

        if (mLoadingDialog == null) {

            mLoadingDialog = new WaitDialog.Builder(context)
                    .setCancelable(cancelable)
                    .setCancelListener(dialog -> {
                        mProgressCancelListener.onCancelProgress();
                    })
                    .create();
        }
        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    private void dismissProgressDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }

}
