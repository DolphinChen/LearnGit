package com.cdf.arbitraryhome.util;

/**
 * Created by cdf on 2016/9/13.
 */
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class CdfUtil {

    public static String TAG = "cdf-rt";

    public static boolean DEBUG = true;
    private static Paint mPaint;

    static {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public static void log(String msg) {
        Log.v(TAG, msg);
    }

    public static void log(String format, Object... args) {
        log(String.format(format, args));
    }

    public static void loge(Exception e) {
        log(android.util.Log.getStackTraceString(e));
    }

    public static void logStackTrace(String desc) {
        RuntimeException here = new RuntimeException(desc);
        here.fillInStackTrace();
        loge(here);
    }

    public static void logList(String name, List<Object> result) {
        StringBuilder sb = new StringBuilder(name);
        if (result == null) {
            sb.append(":null");
            log(sb.toString());
            return;
        }
        int size = result.size();
        sb.append("(" + size + ")");
        if (size == 0) {
            sb.append("[]");
            log(sb.toString());
        } else {
            sb.append("[");
            for (Object o : result) {
                sb.append(o.toString() + ",");
            }
            sb.append("]");
            log(sb.toString());
        }
    }

    public static void logArray(String name, Object[] result) {
        StringBuilder sb = new StringBuilder(name);
        if (result == null) {
            sb.append(":null");
            log(sb.toString());
            return;
        }
        int size = result.length;
        sb.append("(" + size + ")");
        if (size == 0) {
            sb.append("[]");
            log(sb.toString());
        } else {
            sb.append("[");
            for (Object o : result) {
                sb.append(o.toString() + ",");
            }
            sb.append("]");
            log(sb.toString());
        }
    }
    ///////////////////////////////////// 绘制相关 //////////////////////////////
    private static Rect sRect = new Rect();

    public static void fillCanvas(Canvas canvas, int color) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color);
        canvas.getClipBounds(sRect);
        canvas.drawRect(sRect, mPaint);
    }

    public static void outlineCanvas(Canvas canvas, int color) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(color);
        mPaint.setStrokeWidth(5);
        sRect.set(0,0,canvas.getWidth(), canvas.getHeight());
        canvas.drawRect(sRect, mPaint);
    }

    public static Paint getStrokePaint(int color) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(color);
        mPaint.setStrokeWidth(5);
        return mPaint;
    }

    public static Paint getFillPaint(int color) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color);
        return mPaint;
    }

    public static void log(int level, String msg) {
        switch (level) {
            case Log.ERROR:
                Log.e(TAG, msg);
                break;
            case Log.WARN:
                Log.w(TAG, msg);
                break;
        }
    }
}

