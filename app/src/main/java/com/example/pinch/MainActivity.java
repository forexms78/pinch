package com.example.pinch;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    float r, base, now;
    Matrix m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView)findViewById(R.id.image);
        m = new Matrix();

    }

    float getDistance(MotionEvent me){
        float dx, dy;
        dx =  me.getX(1) - me.getX(0);
        dy = me.getY(1) - me.getY(0);
        return (float)Math.sqrt(dx*dx + dy*dy);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getPointerCount()==2){
            int act = event.getAction() & 0xff;
            if ( act == MotionEvent.ACTION_POINTER_DOWN){
                base = getDistance(event);
            } else if(act == MotionEvent.ACTION_POINTER_UP){
                now = getDistance(event);
                r =(float)now/base;
                //if(r <0.2f)
                 //   r = 0.2f;
                //else if ( r>5.f)
                 //   r=5.f;

                m.postScale(r,r);
                iv.setImageMatrix(m);
            }

        }


        return true;//return super.onTouchEvent(event);
    }
}
