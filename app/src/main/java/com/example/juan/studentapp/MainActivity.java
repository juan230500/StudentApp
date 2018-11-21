package com.example.juan.studentapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private int y;
    private int x;
    Canvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView drawingImageView = (ImageView) this.findViewById(R.id.image);
        Bitmap bitmap = Bitmap.createBitmap((int) getWindowManager()
                .getDefaultDisplay().getWidth(), (int) getWindowManager()
                .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        drawingImageView.setImageBitmap(bitmap);

        // Line


    }

    public void go(View view) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        int[] ArrY={100,200};
        int[] ArrX={50,150};
        x=500;
        y=500;
        for (int i=0;i<ArrY.length;i++){
            canvas.drawLine(x, y,ArrX[i] , ArrY[i], paint);
            x=ArrX[i];
            y=ArrY[i];
        }

    }

    public void DrawLines(){

    }

}
