package com.itri.colorringprogress;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.timqi.sectorprogressview.ColorfulRingProgressView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ColorfulRingProgressView crpv;
    TextView tvPercent;

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run () {
                            Random random = new Random();
                            int i = random.nextInt(100);
                            crpv.setPercent(i);
                            tvPercent.setText(i + "");
                        }
                    });
                    try {
                        Thread.sleep(500);
                    }catch (InterruptedException e){

                    }

                }
            }
        }).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crpv = findViewById(R.id.crpv);
        tvPercent = findViewById(R.id.tvPercent);

        crpv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator anim = ObjectAnimator.ofFloat(v, "percent",
                        0, ((ColorfulRingProgressView) v).getPercent());
                anim.setInterpolator(new LinearInterpolator());
                anim.setDuration(100);
                anim.start();
            }
        });
//        showTip();


    }
}
