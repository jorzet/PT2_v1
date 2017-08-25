package com.eeg.pt1_v1.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.eeg.pt1_v1.R;

/**
 * Created by Jorge Zepeda Tinoco on 24/08/17.
 */

public class CalibrationCanvas extends android.support.v7.widget.AppCompatImageView{
    public Paint p;

    public static final int FP1_X = 178;
    public static final int FP1_Y = 89;
    public static final int G_X =   248;
    public static final int G_Y =   72;
    public static final int FP2_X = 310;
    public static final int FP2_Y = 89;
    public static final int F7_X =  100;
    public static final int F7_Y =  170;
    public static final int F3_X =  171;
    public static final int F3_Y =  186;
    public static final int FZ_X =  246;
    public static final int FZ_Y =  205;
    public static final int F4_X =  322;
    public static final int F4_Y =  183;
    public static final int F8_X =  394;
    public static final int F8_Y =  168;
    public static final int A1_X =  -4;
    public static final int A1_Y =  279;
    public static final int T3_X =  68;
    public static final int T3_Y =  276;
    public static final int C3_X =  159;
    public static final int C3_Y =  276;
    public static final int CZ_X =  246;
    public static final int CZ_Y =  276;
    public static final int C4_X =  338;
    public static final int C4_Y =  276;
    public static final int T4_X =  423;
    public static final int T4_Y =  276;
    public static final int A2_X =  500;
    public static final int A2_Y =  279;
    public static final int T5_X =  97;
    public static final int T5_Y =  388;
    public static final int P3_X =  169;
    public static final int P3_Y =  365;
    public static final int PZ_X =  247;
    public static final int PZ_Y =  348;
    public static final int P4_X =  324;
    public static final int P4_Y =  365;
    public static final int T6_X =  394;
    public static final int T6_Y =  385;
    public static final int O1_X =  183;
    public static final int O1_Y =  467;
    public static final int O2_X =  313;
    public static final int O2_Y =  467;


    public static final int[] circles = {
            R.drawable.ic_red_circule,
            R.drawable.ic_orange_circle,
            R.drawable.ic_green_circle
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p=new Paint();
        p.setColor(Color.WHITE);

        Bitmap System1020 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_10_20_system);

        Bitmap redCircle = BitmapFactory.decodeResource(getResources(), circles[0]);
        Bitmap orangeCircle = BitmapFactory.decodeResource(getResources(), circles[1]);
        Bitmap greenCircle = BitmapFactory.decodeResource(getResources(), circles[2]);

        canvas.drawBitmap(System1020, 0, 0, p);

        canvas.drawBitmap(redCircle,CalibrationCanvas.FP1_X, CalibrationCanvas.FP1_Y,p); // FP1
        canvas.drawBitmap(redCircle,CalibrationCanvas.G_X, CalibrationCanvas.G_Y,p); // G
        canvas.drawBitmap(redCircle, CalibrationCanvas.FP2_X, CalibrationCanvas.FP2_Y, p); // FP2

        canvas.drawBitmap(redCircle, CalibrationCanvas.F7_X, CalibrationCanvas.F7_Y, p);
        canvas.drawBitmap(redCircle, CalibrationCanvas.F3_X, CalibrationCanvas.F3_Y, p);
        canvas.drawBitmap(redCircle, CalibrationCanvas.FZ_X, CalibrationCanvas.FZ_Y, p);
        canvas.drawBitmap(redCircle, CalibrationCanvas.F4_X, CalibrationCanvas.F4_Y, p);
        canvas.drawBitmap(redCircle, CalibrationCanvas.F8_X, CalibrationCanvas.F8_Y, p);

        canvas.drawBitmap(redCircle, CalibrationCanvas.A1_X, CalibrationCanvas.A1_Y, p);
        canvas.drawBitmap(redCircle, CalibrationCanvas.T3_X, CalibrationCanvas.T3_Y, p);
        canvas.drawBitmap(redCircle, CalibrationCanvas.C3_X, CalibrationCanvas.C3_Y, p);
        canvas.drawBitmap(redCircle, CalibrationCanvas.CZ_X, CalibrationCanvas.CZ_Y, p);
        canvas.drawBitmap(redCircle, CalibrationCanvas.C4_X, CalibrationCanvas.C4_Y, p);
        canvas.drawBitmap(redCircle, CalibrationCanvas.T4_X, CalibrationCanvas.T4_Y, p);
        canvas.drawBitmap(redCircle, CalibrationCanvas.A2_X, CalibrationCanvas.A2_Y, p);

        canvas.drawBitmap(redCircle, CalibrationCanvas.T5_X, CalibrationCanvas.T5_Y, p);
        canvas.drawBitmap(redCircle, CalibrationCanvas.P3_X, CalibrationCanvas.P3_Y, p);
        canvas.drawBitmap(redCircle, CalibrationCanvas.PZ_X, CalibrationCanvas.PZ_Y, p);
        canvas.drawBitmap(redCircle, CalibrationCanvas.P4_X, CalibrationCanvas.P4_Y, p);
        canvas.drawBitmap(redCircle, CalibrationCanvas.T6_X, CalibrationCanvas.T6_Y, p);

        canvas.drawBitmap(redCircle, CalibrationCanvas.O1_X, CalibrationCanvas.O1_Y, p);
        canvas.drawBitmap(redCircle, CalibrationCanvas.O2_X, CalibrationCanvas.O2_Y, p);
    }

    public CalibrationCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
