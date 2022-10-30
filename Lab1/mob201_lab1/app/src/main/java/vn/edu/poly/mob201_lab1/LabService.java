package vn.edu.poly.mob201_lab1;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class LabService extends Service {

    public LabService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getBundleExtra("info");
        int id = bundle.getInt("id");
        String name = bundle.getString("name");
        String className = bundle.getString("class");
        String content = "   ~ Thêm thông tin thành công ~ "+"\n - Số thứ tự: "+id+"\n - Họ và tên: "+name+"\n - Lớp: "+className;
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public void onDestroy() {
        Toast.makeText(this, "Đã dừng Service", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}