package vn.edu.poly.demobroadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnClick).setOnClickListener(btn ->{
            Intent intent = new Intent(MainActivity.this,MainActivity2.class);
            startActivity(intent);
        });
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String name = intent.getStringExtra("name");
                Button btn = findViewById(R.id.btnClick);
                btn.setText(name);
                Log.e("ZZZZ","broadcastReceiver");
            }
        };

        IntentFilter intentFilter = new IntentFilter("TEST");
        registerReceiver(broadcastReceiver,intentFilter);

    }
}