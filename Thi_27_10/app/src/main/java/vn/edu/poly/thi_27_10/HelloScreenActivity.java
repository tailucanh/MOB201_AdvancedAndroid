package vn.edu.poly.thi_27_10;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HelloScreenActivity extends AppCompatActivity {
    TextView tvAni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_screen);
        tvAni = findViewById(R.id.tvAni);


            ObjectAnimator ani1 = ObjectAnimator.ofFloat(tvAni,"rotation",0f,360f);
        ani1.setDuration(2000);
        ani1.start();


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(HelloScreenActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);

    }
}