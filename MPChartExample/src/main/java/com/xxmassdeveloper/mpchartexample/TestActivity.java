package com.xxmassdeveloper.mpchartexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;

import com.xxmassdeveloper.mpchartexample.view.StatscsView;

public class TestActivity extends AppCompatActivity {


    private SeekBar seekBar;

    private StatscsView statscsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        statscsView = (StatscsView) this.findViewById(R.id.statscsView1);

        statscsView.uodateAirIndex(airIndex);
    }

    private int[] airIndex=new int[]{1,2,3,2,2};

    private int[] lastData0 = new int[] { 70000, 10000, 20000, 40000, 50000,
            80000, 40000 };
    private int[] thisData0 = new int[] { 40000, 10000, 10000, 20000, 30000,
            50000, 30000 };

    private int[] lastData1 = new int[] { 70000, 60000, 60000, 40000, 50000,
            80000, 80000 };
    private int[] thisData1 = new int[] { 40000, 30000, 30000, 20000, 30000,
            50000, 30000 };

    private int[] lastData2 = new int[] { 70000, 50000, 70000, 80000, 80000,
            80000, 70000 };
    private int[] thisData2 = new int[] { 40000, 10000, 40000, 40000, 30000,
            40000, 10000 };

    private int[] lastData3 = new int[] { 70000, 80000, 70000, 40000, 50000,
            80000, 40000 };
    private int[] thisData3 = new int[] { 10000, 10000, 10000, 20000, 30000,
            10000, 30000 };
}
