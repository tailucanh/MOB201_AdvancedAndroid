package vn.edu.poly.mob201_lab1.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.edu.poly.mob201_lab1.LabService;
import vn.edu.poly.mob201_lab1.R;

public class Fragment1 extends Fragment {

    Button btnStart, btnStop;
    Intent intent;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_1,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        intent = new Intent(getActivity(), LabService.class);
        view.findViewById(R.id.btn_start).setOnClickListener(button ->{
            Bundle bundle = new Bundle();
            bundle.putInt("id",1);
            bundle.putString("name","Lục Anh Tài");
            bundle.putString("class","CP17312");
            intent.putExtra("info",bundle);
            getActivity().startService(intent);
        });

        view.findViewById(R.id.btn_stop).setOnClickListener(button ->{
            getActivity().stopService(intent);
        });


    }
}
