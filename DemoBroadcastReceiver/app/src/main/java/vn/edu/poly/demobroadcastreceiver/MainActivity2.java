package vn.edu.poly.demobroadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findViewById(R.id.btnXem).setOnClickListener(bnt->{
            Intent intent = new Intent("TEST");
            intent.putExtra("name","I am Tai Smile");
            sendBroadcast(intent);
            onBackPressed();
        });
    }
}