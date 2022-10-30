package vn.edu.poly.demoanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgAni;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgAni = findViewById(R.id.imgAni);

        findViewById(R.id.btnProperty).setOnClickListener(btn ->{
//            imgAni.setImageResource(R.drawable.ic_course_flutter);
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(imgAni,"alpha",0f,1f);
            animator1.setDuration(2000);
            animator1.start();
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(imgAni,"rotation",0f,360f);
            animator2.setDuration(2000);
            animator2.start();
            animator2.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    super.onAnimationRepeat(animation);
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                }

                @Override
                public void onAnimationPause(Animator animation) {
                    super.onAnimationPause(animation);
                }

                @Override
                public void onAnimationResume(Animator animation) {
                    super.onAnimationResume(animation);
                }
            });
        });

        findViewById(R.id.btnView).setOnClickListener(btn ->{
//            imgAni.setImageResource(R.drawable.ic_course_flutter);
            Animation animation = AnimationUtils.loadAnimation(this,R.anim.animation);
            imgAni.startAnimation(animation);
        });

        findViewById(R.id.btnDrawable).setOnClickListener(btn ->{

            imgAni.setBackgroundResource(R.drawable.drawable_animation);
            AnimationDrawable animationDrawable = (AnimationDrawable) imgAni.getBackground();
            animationDrawable.start();

        });

    }
}