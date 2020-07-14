package com.me.slone.wan.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import com.bottom.item.BaseTabItem;
import com.me.slone.wan.R;

/**
 * Author：diankun
 * Time：20-7-14 下午3:33
 * Description:
 */
public class OnlyIconView extends BaseTabItem {

    private ImageView mIcon;

    private int mDefaultDrawable;
    private int mCheckedDrawable;

    public OnlyIconView(Context context) {
        this(context, null);
    }

    public OnlyIconView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OnlyIconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.bottom_bar_item_only_icon, this, true);

        mIcon = (ImageView) findViewById(R.id.icon);
    }

    public void initialize(@DrawableRes int drawableRes, @DrawableRes int checkedDrawableRes) {
        mDefaultDrawable = drawableRes;
        mCheckedDrawable = checkedDrawableRes;
    }

    @Override
    public void setChecked(boolean checked) {
        mIcon.setImageResource(checked ? mCheckedDrawable : mDefaultDrawable);
    }

    @Override
    public void setMessageNumber(int number) {
        //不需要就不用管
    }

    @Override
    public void setHasMessage(boolean hasMessage) {
        //不需要就不用管
    }

    @Override
    public String getTitle() {
        return "no title";
    }
}

