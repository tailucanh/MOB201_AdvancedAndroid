package vn.edu.poly.ph26495_mob201_lab7;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class FragmentAni extends Fragment {


    ImageView img;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ani,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        img = view.findViewById(R.id.image);


        view.findViewById(R.id.btnRotation).setOnClickListener((button)->{
            objAni(img,"rotation",0f,360f);
            objAni(img,"scaleX",1.5f,1f);
            objAni(img,"scaleY",1.5f,1f);

        });
        view.findViewById(R.id.btnMoving).setOnClickListener((button)->{
            animationView(img,getContext(),R.anim.moving);
        });
        view.findViewById(R.id.btnZoom).setOnClickListener((button)->{
            animationView(img,getContext(),R.anim.zoom);
        });

    }

    private void objAni(ImageView imageView, String ani, float... values){
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView,ani,values);
        animator.setDuration(2000);
        animator.start();
    }

    private void animationView(ImageView imageView, Context context, int id){
        Animation animation = AnimationUtils.loadAnimation(context, id);
        imageView.startAnimation(animation);
    }


}