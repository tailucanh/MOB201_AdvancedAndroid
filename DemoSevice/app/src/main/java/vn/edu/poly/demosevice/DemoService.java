package vn.edu.poly.demosevice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class DemoService extends Service {
    public DemoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        Log.e("DemoService","DemoService");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Log.e("onDestroyService","onDestroyService");
        Toast.makeText(this, "Stop service", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("onStartCommandService","onStartCommandService");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        Log.e("onCreateService","onCreateService");
        Toast.makeText(this, "Start service", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("onUnbindService","onUnbindService");
        return super.onUnbind(intent);
    }

    @Override
    public void onLowMemory() {
        Log.e("onLowMemoryService","onLowMemoryService");
        super.onLowMemory();
    }
}