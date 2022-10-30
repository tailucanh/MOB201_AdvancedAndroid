package vn.edu.poly.ph26495_mob201_lab7;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AdapterTabLayoutMain extends FragmentStateAdapter {


    public AdapterTabLayoutMain(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return new FragmentAni();
        }else if(position == 1){
            return new FragmentSlideShow();
        }else {
            return new FragmentClock();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
