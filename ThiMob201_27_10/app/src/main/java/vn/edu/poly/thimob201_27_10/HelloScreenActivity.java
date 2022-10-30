package vn.edu.poly.thimob201_27_10;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class HelloScreenActivity extends AppCompatActivity {
    TextView tvAni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_screen);
        tvAni = findViewById(R.id.tvAni);

        animationView(tvAni,this,R.anim.zoom);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(HelloScreenActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);

    }


    private void animationView(TextView tv, Context context, int id){
        Animation animation = AnimationUtils.loadAnimation(context, id);
        tv.startAnimation(animation);
    }
}