package vn.edu.poly.demosevice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(MainActivity.this,DemoService.class);

        findViewById(R.id.btnStart).setOnClickListener(button ->{
            startService(intent);

        });

        findViewById(R.id.btnStop).setOnClickListener(button ->{
            stopService(intent);

        });

        String name = "VIET A BANK";
        int lengthOri = name.length();
        int lengthAfterCut = name.replaceAll("A","").length();

    }
}