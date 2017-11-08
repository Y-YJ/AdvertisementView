package com.example.qingchen.advertisementview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qingchen
 * @date 17-11-8
 */

public class AdvertisementView extends View {
    private static final String TAG = "AdvertisementView";


    private List<String> list = new ArrayList<>();
    private Paint paint = new Paint();
    private float progress = 0,tempProgress=0;
    private int numTime = 0;
    private int height,top;
    private int textLeft=0,textSize=36,durationTime=2000,delayTime=1000;

    public AdvertisementView(Context context) {
        super(context);
    }

    public AdvertisementView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AdvertisementView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode=MeasureSpec.getMode(heightMeasureSpec);
        switch (mode){
            case MeasureSpec.AT_MOST:
                height=200;
                break;
            //如果是固定的大小，那就不要去改变它
            case MeasureSpec.EXACTLY:
                height=MeasureSpec.getSize(heightMeasureSpec);
                break;
            //如果没有指定大小，就设置为默认大小
            case MeasureSpec.UNSPECIFIED:
                height=200;
                break;
            default:
                break;
        }

        if ((height+textSize)%2==1){
            tempProgress=(height+textSize+3)/2;
        }else {
            tempProgress=(height+textSize+2)/2;
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (list!=null&&list.size()!=0) {
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setTextSize(textSize);
            if (progress!=tempProgress) {
                top=numTime;
                canvas.drawText(list.get(numTime), textLeft, (height +textSize)/ 2 - progress, paint);
                canvas.drawText(numTime == (list.size() - 1) ? list.get(0) : list.get(numTime + 1), textLeft, height + textSize - progress, paint);
            }else {
                canvas.drawText(list.get(top), textLeft, (height +textSize)/ 2 - progress, paint);
                canvas.drawText(top == (list.size() - 1) ? list.get(0) : list.get(top + 1), textLeft, height + textSize - progress, paint);
            }
        }
    }

    public void setTextLeft(int textLeft) {
        this.textLeft = textLeft;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setDurationTime(int durationTime) {
        this.durationTime = durationTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public float getProgress() {
        return this.progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }


    public void start() {
        if (list==null||list.size()<2) {
            System.out.println("检查参数");
        }else {
            ObjectAnimator animator = ObjectAnimator.ofFloat(this, "progress", 0, tempProgress );
            animator.setDuration(durationTime);
            animator.setInterpolator(new LinearInterpolator());
            animator.addListener(new MyAnimatorListener());
            animator.setStartDelay(delayTime);
            animator.start();
        }
    }

    private class MyAnimatorListener implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            numTime++;
            numTime=numTime%list.size();
            start();
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }

}
