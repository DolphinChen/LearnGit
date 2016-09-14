package com.cdf.arbitraryhome.selfdefineview.waveanim;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.cdf.arbitraryhome.util.CdfUtil;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 实现声波动效
 * Created by cdf on 2016/9/13.
 */
public class WaveAnimView extends View {

    private static final int X_NUMS = 100;
    private Paint mPaint = new Paint();

    // 离散点的横坐标
    private int[] xValues = new int[X_NUMS];
    private int mBaseY;
    private int mLineWidth;
    private int mLineSpace;
    private float[] yValues = new float[X_NUMS];

    private ArrayList<SineCurve> mComposeLines = new ArrayList<>();


    public WaveAnimView(Context context) {
        super(context);
        init();
    }

    public WaveAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(5);

//        // 先用一条正弦曲线来模拟
//        SineCurve curve = new SineCurve(X_NUMS);
//        curve.setAmplitude(50);
//        mComposeLines.add(new SineCurve(X_NUMS));
//
//        // 启动动画
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,3);
//        valueAnimator.setDuration(3000);
//        valueAnimator.setInterpolator(new LinearInterpolator());
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float value = (float) animation.getAnimatedValue();
//                CdfUtil.log("onAnimationUpdate: " + value);
//
//                value-=((int)value);
//                updateYAxisValues(value);
//                invalidate();
//            }
//
//        });
//        valueAnimator.start();



    }

    private void updateYAxisValues(float fraction) {
        Arrays.fill(yValues, 0);
        for (SineCurve curve : mComposeLines) {
            curve.setAmplitude(50);
            curve.setOffset(X_NUMS * fraction);
            curve.fillValues(yValues);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (changed) {
            updateXAxisValues(left, right);
//            updateYAxisValues(top, bottom);
            mBaseY = (bottom - top) >> 1;
        }
    }

    private void updateYAxisValues(int top, int bottom) {
        int testY = (bottom - top) / 4;
        for (int i = 0; i < X_NUMS; i++) {
            yValues[i] = testY;
        }
    }

    private void updateXAxisValues(int left, int right) {
        mLineWidth = (right - left) / X_NUMS / 2;
        mLineSpace = (right - left - mLineWidth * X_NUMS) / (X_NUMS + 1);

        xValues[0] = mLineSpace + (mLineWidth >> 1);
        for (int i = 1; i < X_NUMS; i++) {
            xValues[i] = xValues[i - 1] + mLineSpace + mLineWidth;
        }

        mPaint.setStrokeWidth(mLineWidth);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.argb(100, 0, 255, 0));

        CdfUtil.log("onDraw!");
        for (int i = 0; i < X_NUMS; i++) {
            canvas.drawLine(xValues[i], mBaseY - yValues[i], xValues[i], mBaseY + yValues[i], mPaint);
        }
    }
}
