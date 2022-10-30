package vn.edu.poly.mob201_lab1.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.edu.poly.mob201_lab1.LabService2;
import vn.edu.poly.mob201_lab1.R;

public class Fragment2 extends Fragment {
    Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_2,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText edContent = view.findViewById(R.id.ed_content);
        RadioGroup rdgContent = view.findViewById(R.id.rdg_chooser);
         intent = new Intent(getActivity(), LabService2.class);

        view.findViewById(R.id.btn_search).setOnClickListener(button ->{

            Bundle bundle = new Bundle();
            bundle.putString("content",edContent.getText().toString());

            String rdoChooser = null;
            int chooser = rdgContent.getCheckedRadioButtonId();
            switch (chooser){
                case R.id.rdo_1:
                    rdoChooser = "A";
                    bundle.putString("chooser",rdoChooser);
                    break;
                case R.id.rdo_2:
                    rdoChooser = "B";
                    bundle.putString("chooser",rdoChooser);
                    break;
                case R.id.rdo_3:
                    rdoChooser = "c";
                    bundle.putString("chooser",rdoChooser);
                    break;
                default:
                    bundle.putString("chooser",null);
            }

            intent.putExtra("contentMain",bundle);
            getActivity().startService(intent);

        });



    }
}
