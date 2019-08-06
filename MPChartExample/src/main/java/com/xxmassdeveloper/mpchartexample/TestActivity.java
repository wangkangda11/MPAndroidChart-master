package com.xxmassdeveloper.mpchartexample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;

import com.xxmassdeveloper.mpchartexample.view.DMJSView;
import com.xxmassdeveloper.mpchartexample.view.Forecast2WsView;
import com.xxmassdeveloper.mpchartexample.view.HistogramView;
import com.xxmassdeveloper.mpchartexample.view.StatscsView;
import com.xxmassdeveloper.mpchartexample.view.viewbeen.Wsbyinfo;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private int[] airIndex = new int[]{1, 2, 3, 2, 2};
    private StatscsView statscsView;
    HistogramView histogram;
    DMJSView wsby_dmjsview;
    private ArrayList<Wsbyinfo> tmpL = null;

    Forecast2WsView f2wsw;
    List<HistogramView.Histogram> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

//        statscsView = (StatscsView) this.findViewById(R.id.statscsView1);
//        histogram = (HistogramView) this.findViewById(R.id.histogram);
////        statscsView.uodateAirIndex(airIndex);
//        histogram.setData(getdata(list));
//
////        8.6
//        wsby_dmjsview = (DMJSView) findViewById(R.id.wsby_dmjsview);
//        initData();
//        wsby_dmjsview.setTmpL(tmpL);

        f2wsw = findViewById(R.id.f2wsw);
        nativeData();
//        setData(tmpL);
        f2wsw.setTmpL(tmpL);
    }

    private List<HistogramView.Histogram> getdata(List<HistogramView.Histogram> list) {
        if (list != null) list.clear();
        HistogramView.Histogram histogram = new HistogramView.Histogram();
        histogram.setValueName("SKWEN");
        histogram.setValue(200);
        histogram.setValue1(260);
        histogram.setSpaceWidth(40);
        histogram.setValueWidth(90);
        histogram.setColor(Color.parseColor("#FF4363"));
        list.add(histogram);

        HistogramView.Histogram histogram1 = new HistogramView.Histogram();
        histogram1.setValueName("Vicent");
        histogram1.setValue(500);
        histogram1.setValue1(560);
        histogram1.setSpaceWidth(40);
        histogram1.setValueWidth(90);
        histogram1.setColor(Color.parseColor("#FF4363"));
        list.add(histogram1);
        HistogramView.Histogram histogram2 = new HistogramView.Histogram();
        histogram2.setValueName("Vicent");
        histogram2.setValue(600);
        histogram2.setValue1(560);
        histogram2.setSpaceWidth(40);
        histogram2.setValueWidth(90);
        histogram2.setColor(Color.parseColor("#FF4363"));
        list.add(histogram2);
//        HistogramView.Histogram histogram3 = new HistogramView.Histogram();
//        histogram3.setValueName("Vicent");
//        histogram3.setValue(50);
//        histogram3.setSpaceWidth(40);
//        histogram3.setValueWidth(90);
//        list.add(histogram3);
//        HistogramView.Histogram histogram4 = new HistogramView.Histogram();
//        histogram4.setValueName("Vicent");
//        histogram4.setValue(20);
//        histogram4.setSpaceWidth(40);
//        histogram4.setValueWidth(90);
//        list.add(histogram4);

        return list;
    }

    private void initData() {
        tmpL = new ArrayList<>();

        Wsbyinfo sx = new Wsbyinfo("陕西");
        sx.setFMaximumOutput(4490);
        sx.setFMinimumOutput(3516);
        sx.setFActualOutput(16522);
        tmpL.add(sx);
        Wsbyinfo gs = new Wsbyinfo("甘肃");
        gs.setFMaximumOutput(351);
        gs.setFMinimumOutput(3929);
        gs.setFActualOutput(11565);
        tmpL.add(gs);
        Wsbyinfo qh = new Wsbyinfo("青海");
        qh.setFMaximumOutput(6273);
        qh.setFMinimumOutput(625);
        qh.setFActualOutput(7118);
        tmpL.add(qh);
        Wsbyinfo nx = new Wsbyinfo("宁夏");
        nx.setFMaximumOutput(3081);
        nx.setFMinimumOutput(3828);
        nx.setFActualOutput(17433);
        tmpL.add(nx);
        Wsbyinfo xj = new Wsbyinfo("新疆");
        xj.setFMaximumOutput(4800);
        xj.setFMinimumOutput(1411);
        xj.setFActualOutput(27284);
        tmpL.add(xj);

        Wsbyinfo xjl = new Wsbyinfo("新疆");
        xj.setFMaximumOutput(4800);
        xj.setFMinimumOutput(1411);
        xj.setFActualOutput(27284);
        tmpL.add(xj);
    }


    private void setData(ArrayList<Wsbyinfo> tmpL) {
        ArrayList<Wsbyinfo> wsbyList = new ArrayList<>();
        Wsbyinfo xb = new Wsbyinfo("西北");
        double fMaximumOutput = 0;
        double fMinimumOutput = 0;
        double fActualOutput = 0;
        for (Wsbyinfo wsbyinfo : tmpL) {
            fMaximumOutput += wsbyinfo.getFMaximumOutput();
            fMinimumOutput += wsbyinfo.getFMinimumOutput();
            fActualOutput += wsbyinfo.getFActualOutput();
        }
        xb.setFMaximumOutput(fMaximumOutput);
        xb.setFMinimumOutput(fMinimumOutput);
        xb.setFActualOutput(fActualOutput);
        wsbyList.add(xb);
        wsbyList.addAll(tmpL);
        f2wsw.setTmpL(wsbyList);
//        f2wsw.requestLayout();
    }

    private void nativeData() {
        if (tmpL == null) tmpL = new ArrayList<>();
        if (tmpL.size() != 0) tmpL.clear();
        Wsbyinfo sx = new Wsbyinfo("陕西");
        sx.setFMaximumOutput(4490);
        sx.setFMinimumOutput(3516);
        sx.setFActualOutput(16522);
        tmpL.add(sx);
        Wsbyinfo gs = new Wsbyinfo("甘肃");
        gs.setFMaximumOutput(351);
        gs.setFMinimumOutput(3929);
        gs.setFActualOutput(11565);
        tmpL.add(gs);
        Wsbyinfo qh = new Wsbyinfo("青海");
        qh.setFMaximumOutput(6273);
        qh.setFMinimumOutput(625);
        qh.setFActualOutput(7118);
        tmpL.add(qh);
        Wsbyinfo nx = new Wsbyinfo("宁夏");
        nx.setFMaximumOutput(3081);
        nx.setFMinimumOutput(3828);
        nx.setFActualOutput(17433);
        tmpL.add(nx);
        Wsbyinfo xj = new Wsbyinfo("新疆");
        xj.setFMaximumOutput(4800);
        xj.setFMinimumOutput(1411);
        xj.setFActualOutput(27284);
        tmpL.add(xj);
    }
}
