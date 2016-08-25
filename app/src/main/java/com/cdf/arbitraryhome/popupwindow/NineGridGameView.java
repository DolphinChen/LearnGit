package com.cdf.arbitraryhome.popupwindow;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.cdf.arbitraryhome.R;

import java.util.Random;

/**
 * Created by cdf on 2016/5/15.
 */
public class NineGridGameView extends View implements View.OnTouchListener, Runnable {

    private Context mContext;
    private PopupWindow mPopWin;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private Random mRand = new Random(60);

    public NineGridGameView(Context context) {
        super(context);
        init(context);
    }

    public NineGridGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NineGridGameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.mipmap.ic_launcher);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mPopWin = new PopupWindow(imageView);
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mHandler.removeCallbacks(this);
                mPopWin.showAtLocation(this, Gravity.NO_GRAVITY, x, y);
                mPopWin.update(60 + mRand.nextInt(60), 60 + mRand.nextInt(60));
                break;
            case MotionEvent.ACTION_MOVE:
                mHandler.removeCallbacks(this);
                mPopWin.update(x, y, 60, 60);
                break;
            case MotionEvent.ACTION_UP:
                mHandler.postDelayed(this, 1000);
                break;
        }
        return true;
    }

    @Override
    public void run() {
        mPopWin.dismiss();
    }
}
