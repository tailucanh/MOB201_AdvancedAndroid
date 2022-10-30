package vn.edu.poly.mob201_lab2_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getStringExtra("name");
        Toast.makeText(context.getApplicationContext(),"MyBroadcast: "+ name , Toast.LENGTH_LONG).show();

    }
}
