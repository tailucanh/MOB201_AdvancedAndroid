package vn.edu.poly.ph26495_mob201_lab7;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentSlideShow extends Fragment {

    ImageView imgShow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_slide_show,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgShow = view.findViewById(R.id.image);

        view.findViewById(R.id.btnAll).setOnClickListener((button)->{
            showImage("All");
        });
        view.findViewById(R.id.btnDoraemon).setOnClickListener((button)->{
            showImage("Doraemon");
        });
        view.findViewById(R.id.btnNobita).setOnClickListener((button)->{
            showImage("Nobita");
        });
        view.findViewById(R.id.btnChaien).setOnClickListener((button)->{
            showImage("Chaien");
        });
        view.findViewById(R.id.btnXuka).setOnClickListener((button)->{
            showImage("Xuka");
        });
        view.findViewById(R.id.btnSuneo).setOnClickListener((button)->{
            showImage("Suneo");
        });

    }


    private  void showImage(String img){
        ObjectAnimator aniHide = ObjectAnimator.ofFloat(imgShow,"translationX",0f,500f);
        aniHide.setDuration(2000);
        ObjectAnimator aniAlphaHide = ObjectAnimator.ofFloat(imgShow,"alpha",1f,0f);
        aniAlphaHide.setDuration(2000);

        ObjectAnimator aniTrasShow = ObjectAnimator.ofFloat(imgShow,"translationX",-500f,0f);
        aniTrasShow.setDuration(2000);
        ObjectAnimator aniAlphaShow = ObjectAnimator.ofFloat(imgShow,"alpha",0f,1f);
        aniAlphaShow.setDuration(2000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(aniTrasShow).with(aniAlphaShow).after(aniHide).after(aniAlphaHide);
        animatorSet.start();
        final  String imgName = img;
        aniAlphaHide.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                switch (imgName){
                    case "All":
                        imgShow.setImageResource(R.drawable.all);
                        break;
                    case "Nobita":
                        imgShow.setImageResource(R.drawable.nobita);
                        break;
                    case "Doraemon":
                        imgShow.setImageResource(R.drawable.doraemon);
                        break;
                    case "Chaien":
                        imgShow.setImageResource(R.drawable.chaien);
                        break;
                    case "Xuka":
                        imgShow.setImageResource(R.drawable.shizuka);
                        break;
                    case "Suneo":
                        imgShow.setImageResource(R.drawable.suneo);
                        break;


                }


            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });


    }


}