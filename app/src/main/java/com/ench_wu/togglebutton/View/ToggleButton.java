package com.ench_wu.togglebutton.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * author:ench_wu
 * Created on 2015/9/23.
 */
public class ToggleButton extends View {
    private ToggleState toggleState = ToggleState.open;
    private Bitmap slideBg;
    private Bitmap switchBg;
    private int currentX;

    private boolean isSilde = false;

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


        if (isSilde) {//如果isSilde==true的话手指还未抬起

            int left = currentX - slideBg.getWidth() / 2;
            if (left < 0) left = 0;
            if (left > switchBg.getWidth() - slideBg.getWidth()) {
                left = switchBg.getWidth() - slideBg.getWidth();
            }
            canvas.drawBitmap(slideBg, left, 0, null);

        } else {
            if (toggleState == ToggleState.close) {
                canvas.drawBitmap(slideBg, 0, 0, null);
            } else {
                int left = switchBg.getWidth() - slideBg.getWidth();
                canvas.drawBitmap(slideBg, left, 0, null);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentX = (int) event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isSilde = true;
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                isSilde = false;

                int centerX = switchBg.getWidth() / 2;
                if (currentX > centerX) {
                    //判断原先状态是否已经是ToggleState.open,如果不是,那将toggleState = ToggleState.open
                    if (toggleState != ToggleState.open) {
                        toggleState = ToggleState.open;
                        //判断lisrner是否被调用
                        if (listner != null) {
                            listner.onToggleStateChange(toggleState);
                        }
                    }
                } else {
                    //判断原先状态是否已经是ToggleState.close
                    if (toggleState != ToggleState.close) {
                        toggleState = ToggleState.close;
                        if (listner != null) {
                            listner.onToggleStateChange(toggleState);
                        }
                    }
                }

                break;

        }
        invalidate();
        return true;

    }

    /**
     * 暴露接口
     */
    private OnToggleStateChangeListner listner;

    public void setOnToggleStateChangeListener(OnToggleStateChangeListner listner) {
        this.listner = listner;
    }

    public interface OnToggleStateChangeListner {
        void onToggleStateChange(ToggleState state);
    }
}
