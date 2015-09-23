package com.ench_wu.togglebutton.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * author:ench_wu
 * Created on 2015/9/23.
 */
public class ToggleButton extends View {
    private ToggleState toggleState = ToggleState.open;
    private Bitmap slideBg;
    private Bitmap switchBg;

    public ToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ToggleButton(Context context) {
        super(context);
    }

    public enum ToggleState {
        open, close
    }

    public void setSlideBackgroundResourse(int slideBackground) {
        slideBg = BitmapFactory.decodeResource(getResources(), slideBackground);
    }

    public void setSwitchBackgroundResourse(int switchBackground) {
        switchBg = BitmapFactory.decodeResource(getResources(), switchBackground);
    }

    public void setToggleState(ToggleState state) {
        toggleState = state;
    }

    /**
     * 必须先测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(switchBg.getWidth(), switchBg.getHeight());
    }

    /**
     * 绘制自己显示在屏幕时的样子
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(switchBg, 0, 0, null);

        if (toggleState==ToggleState.close){
            canvas.drawBitmap(slideBg, 0, 0, null);
        }else {
        int left = switchBg.getWidth() - slideBg.getWidth();
        canvas.drawBitmap(slideBg, left, 0, null);
        }


    }
}
