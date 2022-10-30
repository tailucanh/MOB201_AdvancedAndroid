package vn.edu.poly.mob201_lab1.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.edu.poly.mob201_lab1.Fragment.Fragment1;
import vn.edu.poly.mob201_lab1.Fragment.Fragment2;


public class AdapterTabLayoutMain extends FragmentStateAdapter {


    public AdapterTabLayoutMain(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
               return new Fragment1();
            case 1:
                return new Fragment2();
            default:
                return new Fragment1();

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
