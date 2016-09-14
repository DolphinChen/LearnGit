package com.cdf.arbitraryhome.selfdefineview.waveanim;

/**
 * Created by cdf on 2016/9/13.
 */
public class SineCurve {
    private final double[] mValues;
    private final int mXNums;
    private float mAmplitude;
    private int mOffset;

    public SineCurve(int xNums) {
        mXNums = xNums;
        mValues = new double[xNums];
        updateValues();
    }

    public void setAmplitude(float amplitude) {
        mAmplitude = amplitude;
    }

    private void updateValues() {
        double step = (double) 2 * Math.PI / (mXNums - 1);
        for (int i = 0; i < mXNums - 1; i++) {
            mValues[i] = Math.sin(step * i);
        }
    }

    public void fillValues(float[] yValues) {
        if (mValues.length == yValues.length) {
            for (int i = 0; i < yValues.length; i++) {
                yValues[i] = (float) (mValues[(i + mOffset) % mValues.length] * mAmplitude);
            }
        }
    }

    public void setOffset(float offset) {
        mOffset = (int) offset;
    }
}
