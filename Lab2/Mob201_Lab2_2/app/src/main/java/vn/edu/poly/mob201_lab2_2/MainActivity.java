package vn.edu.poly.mob201_lab2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edContent = findViewById(R.id.ed_content);
        findViewById(R.id.btn_check).setOnClickListener(btn ->{
            Intent intent = new Intent(getApplicationContext(),MyBroadcastReceiver.class);
            intent.putExtra("content",edContent.getText().toString());
            intent.setAction("vn.poly.CUSTOM_INTENT");
            sendBroadcast(intent);
        });
    }
}