package vn.edu.poly.thimob201_27_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnListRss).setOnClickListener(btn ->{
            Intent intent = new Intent(MainActivity.this, ListNewsActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.btnSaveRss).setOnClickListener(btn ->{
            Intent intent = new Intent(MainActivity.this, SaveNewsActivity.class);
            startActivity(intent);
        });

    }
}