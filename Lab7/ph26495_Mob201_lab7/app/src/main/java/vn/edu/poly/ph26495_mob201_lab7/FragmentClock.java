package vn.edu.poly.ph26495_mob201_lab7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentClock extends Fragment {

    ImageView imgHour,imgSecond,imgMinute;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_clock,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgHour = view.findViewById(R.id.imgHour);
        imgSecond = view.findViewById(R.id.imgSecond);
        imgMinute = view.findViewById(R.id.imgMinute);


        view.findViewById(R.id.btnRun).setOnClickListener((button)->{
            startClock();
        });
    }


    private void startClock(){
        Animation aniHour = AnimationUtils.loadAnimation(getContext(),R.anim.clock_hour);
        Animation aniSecond = AnimationUtils.loadAnimation(getContext(),R.anim.clock_second);
        Animation aniMinute = AnimationUtils.loadAnimation(getContext(),R.anim.clock_minute);
        imgHour.startAnimation(aniHour);
        imgSecond.startAnimation(aniSecond);
        imgMinute.startAnimation(aniMinute);


    }


}