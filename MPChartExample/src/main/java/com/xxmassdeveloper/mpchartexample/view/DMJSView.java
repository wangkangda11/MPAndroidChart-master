package com.xxmassdeveloper.mpchartexample.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.xxmassdeveloper.mpchartexample.view.viewbeen.Wsbyinfo;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/10/25.
 */

public class DMJSView extends View {
    Context context;
    private ArrayList<Wsbyinfo> tmpL = new ArrayList<>();
    private int w, h;

    public DMJSView(Context context) {
        super(context);
    }

    public DMJSView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int w = wm.getDefaultDisplay().getWidth();
        int h = wm.getDefaultDisplay().getHeight();
        this.context = context;


//        initData();
        invalidate();
    }

    public DMJSView(Context context, ArrayList<Wsbyinfo> tmpL) {
        super(context);
        this.tmpL = tmpL;
    }

    public ArrayList<Wsbyinfo> getTmpL() {
        return tmpL;
    }

    public void setTmpL(ArrayList<Wsbyinfo> tmpL) {
        this.tmpL = tmpL;
        invalidate();
    }


    private DecimalFormat format = new DecimalFormat("0");
    private double maxUP = 560d;
    private double maxDOWN = 490d;
    private double UP_DOWN = maxUP-maxDOWN;
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
//		this.canvas = canvas;
//		canvas.drawColor(Color.BLACK);
        Paint g2 = new Paint();

//		canvas.drawBitmap(外框, 0, 0, g2);
        g2.setTextSize(28);

        g2.setAntiAlias(true);
        ArrayList<Wsbyinfo> tmpLxx=tmpL;
        Path path = new Path();

        float pwidth=this.getWidth();
        float pheight=this.getHeight();
        float barUnite = (float) (pwidth*0.425/(tmpLxx.size()+1));
        float barWidth = (float) (barUnite*0.7);
//        Font linefont=calculateFontSize(g2,barWidth*0.32,"0000","微软雅黑");
//        Font linetitlefont=calculateFontSize(g2,barWidth*0.36,"最大出力","微软雅黑");
//        Font titlefont=calculateFontSize(g2,barWidth*0.36,"陕西","微软雅黑");
        Wsbyinfo areawsbyinfo = null;
        g2.setStrokeWidth(1);
        for(int i = 0;i<tmpLxx.size();i++){
            areawsbyinfo = tmpLxx.get(i);

            path.reset();
            path.moveTo(pwidth*0.15f+i*barUnite*2.5f-barWidth/2,pheight*0.9f);
            path.lineTo(pwidth*0.15f+i*barUnite*2.5f,pheight*0.93f);
            path.lineTo(pwidth*0.15f+i*barUnite*2.5f+barWidth/2,pheight*0.9f);
            path.lineTo(pwidth*0.15f+i*barUnite*2.5f,pheight*0.87f);
            path.close();
            g2.setColor(Color.WHITE);
            g2.setStyle(Paint.Style.FILL);
            canvas.drawPath(path, g2);


            path.reset();
            path.moveTo(pwidth*0.15f+i*barUnite*2.5f-barWidth/2,(float)(pheight*0.9f-pheight*0.6f*areawsbyinfo.getFMinimumOutput()/(areawsbyinfo.getFMinimumOutput()+areawsbyinfo.getFMaximumOutput())));
            path.lineTo(pwidth*0.15f+i*barUnite*2.5f,(float)(pheight*0.93f-pheight*0.6f*areawsbyinfo.getFMinimumOutput()/(areawsbyinfo.getFMinimumOutput()+areawsbyinfo.getFMaximumOutput())));
            path.lineTo(pwidth*0.15f+i*barUnite*2.5f+barWidth/2,(float)(pheight*0.9f-pheight*0.6*areawsbyinfo.getFMinimumOutput()/(areawsbyinfo.getFMinimumOutput()+areawsbyinfo.getFMaximumOutput())));
            path.lineTo(pwidth*0.15f+i*barUnite*2.5f,(float)(pheight*0.87f-pheight*0.6*areawsbyinfo.getFMinimumOutput()/(areawsbyinfo.getFMinimumOutput()+areawsbyinfo.getFMaximumOutput())));
            path.close();
            g2.setColor(Color.argb(255,112,196,196));
            g2.setStyle(Paint.Style.FILL);
            canvas.drawPath(path, g2);
            g2.setColor(Color.WHITE);
            g2.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, g2);

            path.reset();
            path.moveTo(pwidth*0.15f+i*barUnite*2.5f-barWidth/2,pheight*0.9f);
            path.lineTo(pwidth*0.15f+i*barUnite*2.5f,pheight*0.93f);
            path.lineTo(pwidth*0.15f+i*barUnite*2.5f,(float)(pheight*0.93f-pheight*0.6*areawsbyinfo.getFMinimumOutput()/(areawsbyinfo.getFMinimumOutput()+areawsbyinfo.getFMaximumOutput())));
            path.lineTo(pwidth*0.15f+i*barUnite*2.5f-barWidth/2,(float)(pheight*0.9-pheight*0.6*areawsbyinfo.getFMinimumOutput()/(areawsbyinfo.getFMinimumOutput()+areawsbyinfo.getFMaximumOutput())));
            path.close();
            g2.setColor(Color.argb(255,112,196,196));
            g2.setStyle(Paint.Style.FILL);
            canvas.drawPath(path, g2);
            g2.setColor(Color.WHITE);
            g2.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, g2);

            path.reset();
            path.moveTo(pwidth*0.15f+i*barUnite*2.5f,pheight*0.93f);
            path.lineTo(pwidth*0.15f+i*barUnite*2.5f+barWidth/2,pheight*0.9f);
            path.lineTo(pwidth*0.15f+i*barUnite*2.5f+barWidth/2,(float)(pheight*0.9-pheight*0.6*areawsbyinfo.getFMinimumOutput()/(areawsbyinfo.getFMinimumOutput()+areawsbyinfo.getFMaximumOutput())));
            path.lineTo(pwidth*0.15f+i*barUnite*2.5f,(float)(pheight*0.93f-pheight*0.6*areawsbyinfo.getFMinimumOutput()/(areawsbyinfo.getFMinimumOutput()+areawsbyinfo.getFMaximumOutput())));
            path.close();
            g2.setColor(Color.argb(255,112,196,196));
            g2.setStyle(Paint.Style.FILL);
            canvas.drawPath(path, g2);
            g2.setColor(Color.WHITE);
            g2.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, g2);



            path.reset();
            path.moveTo(pwidth*0.15f+i*barUnite*2.5f-barWidth/2,(float)(pheight*0.9-pheight*0.6*areawsbyinfo.getFMinimumOutput()/(areawsbyinfo.getFMinimumOutput()+areawsbyinfo.getFMaximumOutput())));
            path.lineTo(pwidth*0.15f+i*barUnite*2.5f,(float)(pheight*0.93-pheight*0.6*areawsbyinfo.getFMinimumOutput()/(areawsbyinfo.getFMinimumOutput()+areawsbyinfo.getFMaximumOutput())));
            path.lineTo(pwidth*0.15f+i*barUnite*2.5f,pheight*0.33f);
            path.lineTo(pwidth*0.15f+i*barUnite*2.5f-barWidth/2,pheight*0.3f);
            path.close();
            g2.setColor(Color.argb(255,243,164,121));
            g2.setStyle(Paint.Style.FILL);
            canvas.drawPath(path, g2);
            g2.setColor(Color.WHITE);
            g2.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, g2);


            path.reset();
            path.moveTo(pwidth*0.15f+i*barUnite*2.5f,(float)(pheight*0.93-pheight*0.6*areawsbyinfo.getFMinimumOutput()/(areawsbyinfo.getFMinimumOutput()+areawsbyinfo.getFMaximumOutput())));
            path.lineTo(pwidth*0.15f+i*barUnite*2.5f+barWidth/2,(float)(pheight*0.9-pheight*0.6*areawsbyinfo.getFMinimumOutput()/(areawsbyinfo.getFMinimumOutput()+areawsbyinfo.getFMaximumOutput())));
            path.lineTo(pwidth*0.15f+i*barUnite*2.5f+barWidth/2,pheight*0.3f);
            path.lineTo(pwidth*0.15f+i*barUnite*2.5f,pheight*0.33f);
            path.close();
            g2.setColor(Color.argb(255,243,164,121));
            g2.setStyle(Paint.Style.FILL);
            canvas.drawPath(path, g2);
            g2.setColor(Color.WHITE);
            g2.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, g2);


            path.reset();
            path.moveTo(pwidth*0.15f+i*barUnite*2.5f-barWidth/2,pheight*0.3f);
            path.lineTo(pwidth*0.15f+i*barUnite*2.5f,pheight*0.33f);
            path.lineTo(pwidth*0.15f+i*barUnite*2.5f+barWidth/2,pheight*0.3f);
            path.lineTo(pwidth*0.15f+i*barUnite*2.5f,pheight*0.27f);
            path.close();
            g2.setColor(Color.argb(255,243,164,121));
            g2.setStyle(Paint.Style.FILL);
            canvas.drawPath(path, g2);
            g2.setColor(Color.WHITE);
            g2.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, g2);

            g2.setColor(Color.WHITE);
            g2.setStyle(Paint.Style.FILL);
            g2.setTextSize(32);
//	      	g2.setFont(linefont);

            double v = areawsbyinfo.getFMinimumOutput() / (areawsbyinfo.getFMinimumOutput() + areawsbyinfo.getFMaximumOutput());

            canvas.drawLine((float)(pwidth*0.09f+i*barUnite*2.5f-barWidth*0.5), (float)(pheight*0.9)-(float)(pheight*0.6*areawsbyinfo.getFMinimumOutput()/(areawsbyinfo.getFMinimumOutput()+areawsbyinfo.getFMaximumOutput())), (float)(pwidth*0.15+i*barUnite*2.5-barWidth*0.5), (int)(pheight*0.9)-(int)(pheight*0.6*areawsbyinfo.getFMinimumOutput()/(areawsbyinfo.getFMinimumOutput()+areawsbyinfo.getFMaximumOutput())), g2);
            if (v<0.1){

                canvas.drawText(format.format(areawsbyinfo.getFActualOutput()), (int)(pwidth*0.1+i*barUnite*2.5-barWidth*0.7), (int)(pheight*0.88)-(int)(pheight*0.6*areawsbyinfo.getFMinimumOutput()/(areawsbyinfo.getFMinimumOutput()+areawsbyinfo.getFMaximumOutput())), g2);
            }else {

                canvas.drawText(format.format(areawsbyinfo.getFActualOutput()), (int)(pwidth*0.1+i*barUnite*2.5-barWidth*0.7), (int)(pheight*0.948)-(int)(pheight*0.6*areawsbyinfo.getFMinimumOutput()/(areawsbyinfo.getFMinimumOutput()+areawsbyinfo.getFMaximumOutput())), g2);
            }


            canvas.drawLine((int)(pwidth*0.09+i*barUnite*2.5-barWidth*0.5), (int)(pheight*0.3),(int)(pwidth*0.15+i*barUnite*2.5-barWidth*0.5), (int)(pheight*0.3),g2);
            canvas.drawText(format.format(areawsbyinfo.getFActualOutput()+areawsbyinfo.getFMaximumOutput()),(int)(pwidth*0.1+i*barUnite*2.5-barWidth*0.7), (int)(pheight*0.278),g2);//(int)(pheight*0.348),g2

            canvas.drawLine((int)(pwidth*0.09+i*barUnite*2.5-barWidth*0.5), (int)(pheight*0.9),(int)(pwidth*0.15+i*barUnite*2.5-barWidth*0.5), (int)(pheight*0.9),g2);
            canvas.drawText(format.format(areawsbyinfo.getFActualOutput()-areawsbyinfo.getFMinimumOutput()),(int)(pwidth*0.1+i*barUnite*2.5-barWidth*0.7), (int)(pheight*0.948),g2);

            if(i==0){
//	      		g2.setFont(linetitlefont);
                g2.setColor(Color.rgb(255,255,255));
                g2.setTextSize(24);
//                0.097
                canvas.drawText("实际出力",(int)(pwidth*0.033+i*barUnite*2.5-barWidth*0.5), (int)(pheight*0.92)-(int)(pheight*0.6*areawsbyinfo.getFMinimumOutput()/(areawsbyinfo.getFMinimumOutput()+areawsbyinfo.getFMaximumOutput())),g2);
                canvas.drawText("最大出力",(int)(pwidth*0.033+i*barUnite*2.5-barWidth*0.5), (int)(pheight*0.29),g2);
                canvas.drawText("最小出力",(int)(pwidth*0.033+i*barUnite*2.5-barWidth*0.5), (int)(pheight*0.89),g2);
            }

//	      	g2.setFont(titlefont);
//            g2.setTextSize(52);
            g2.setTextSize(40);
            canvas.drawText(areawsbyinfo.getFProvinceName(),(int)(pwidth*0.14+i*barUnite*2.5-barWidth*0.6), (int)(pheight*0.2),g2);
            g2.setTextSize(28);

//    		g2.setFont(linefont);
            g2.setColor(Color.rgb(112,196,196));
            canvas.drawText(format.format(areawsbyinfo.getFMinimumOutput()),(int)(pwidth*0.21+i*barUnite*2.5-barWidth*0.5), (int)(pheight*0.922-pheight*0.3*areawsbyinfo.getFMinimumOutput()/(areawsbyinfo.getFMinimumOutput()+areawsbyinfo.getFMaximumOutput())),g2);
            g2.setColor(Color.rgb(243,164,121));
//0.322
            canvas.drawText(format.format(areawsbyinfo.getFMaximumOutput()),(int)(pwidth*0.21+i*barUnite*2.5-barWidth*0.5), (int)(pheight*0.322+pheight*0.3*areawsbyinfo.getFMaximumOutput()/(areawsbyinfo.getFMinimumOutput()+areawsbyinfo.getFMaximumOutput())),g2);

            g2.setColor(Color.WHITE);
//    		g2.setFont(new Font("微软雅黑", Font.BOLD, (int)(this.getWidth()*0.02)));
            canvas.drawText("单位：MW", (int)(this.getWidth()*0.12), (int)(this.getHeight()*0.06),g2);//(int)(this.getWidth()*0.01), (int)(this.getHeight()*0.06)
            canvas.drawText("  上备", (int)(this.getWidth()*0.75),(int)(this.getHeight()*0.06),g2);
            canvas.drawText("  下备", (int)(this.getWidth()*0.88),(int)(this.getHeight()*0.06),g2);
            g2.setColor(Color.rgb(243,164,121));
//            g2.setColor(Color.rgb(255,255,255));
            canvas.drawRect((float)(this.getWidth()*(0.72)), (float)(this.getHeight()*0.01),(float)(this.getWidth()*(0.72))+ (float)(this.getHeight()*0.05), (float)(this.getHeight()*0.01)+ (float)(this.getHeight()*0.06), g2);

            g2.setColor(Color.rgb(112,196,196));
            canvas.drawRect((int)(this.getWidth()*(0.85)), (int)(this.getHeight()*0.01), (int)(this.getWidth()*(0.85))+(int)(this.getHeight()*0.05), (float)(this.getHeight()*0.01)+ (int)(this.getHeight()*0.06),g2);

        }
    }

}


