package vn.edu.poly.mob201_lab1;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class LabService2 extends Service {
    public LabService2() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getBundleExtra("contentMain");

        String content = bundle.getString("content").toLowerCase();
        if(content.equals("")){
            Toast.makeText(this, "Hãy nhập thông tin tùy ý.", Toast.LENGTH_SHORT).show();
        }else {
            String chooserRdo = bundle.getString("chooser").toLowerCase();
            countingLetters(content, chooserRdo);

        }

        return super.onStartCommand(intent, flags, startId);
    }


    public void countingLetters(String text,String textRegex){
        int textOri = text.length();
        int textCut = text.replaceAll(textRegex,"").length();
        int count = textOri - textCut;

        Toast.makeText(this, "Số lượng chữ "+textRegex +" là: "+count, Toast.LENGTH_SHORT).show();
    }
}