package vn.edu.poly.mob201_lab2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvName = findViewById(R.id.ed_name);

        findViewById(R.id.btn_send).setOnClickListener(btn ->{
            Intent intent = new Intent(getApplicationContext(),MyBroadcastReceiver.class);
            intent.putExtra("name",tvName.getText().toString());
            intent.setAction("fpoly.android.CUSTOM_INTENT");
            sendBroadcast(intent);

        });



    }
}