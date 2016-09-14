package com.cdf.arbitraryhome.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cdf.arbitraryhome.R;
import com.cdf.arbitraryhome.selfdefineview.waveanim.WaveAnimView;

/**
 * Created by cdf on 2016/9/13.
 */
public class WaveAnimActivity extends Activity implements View.OnClickListener{

    private WaveAnimView mWaveView;
    private Button mBtnStartBreathAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_anim);

        mWaveView = (WaveAnimView) findViewById(R.id.wave_view);
        mBtnStartBreathAnim = (Button) findViewById(R.id.btn_start_breath_anim);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wave_view:
                break;
        }
    }
}
