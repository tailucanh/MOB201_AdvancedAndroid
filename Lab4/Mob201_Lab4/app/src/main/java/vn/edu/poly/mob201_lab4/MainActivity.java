package vn.edu.poly.mob201_lab4;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationRequest;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    private FusedLocationProviderClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvLocationParameters = findViewById(R.id.tvInformation);
        //Kiểm tra kết nối mang
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo checkWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo check3G =  connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if(checkWifi.isConnected()){ Toast.makeText(this, "Bạn đang dùng WIFI", Toast.LENGTH_LONG).show(); }
        if(check3G.isConnected()){ Toast.makeText(this, "Bạn đang dùng 3G", Toast.LENGTH_LONG).show(); }
        //Lấy tọa độ
        client = LocationServices.getFusedLocationProviderClient(this);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            ActivityResultLauncher<String[]> locationPermissionRequest = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                        Boolean fineLocationGranted = result.getOrDefault( Manifest.permission.ACCESS_FINE_LOCATION, false);
                        Boolean coarseLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION,false);
                      if (fineLocationGranted != null && fineLocationGranted) {
                            client.getCurrentLocation(LocationRequest.QUALITY_HIGH_ACCURACY,null).addOnSuccessListener(new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {
                                    tvLocationParameters.setText("- Kinh độ: "+location.getLongitude()+" \n"+"- Vĩ độ: "+location.getLatitude());

                                    findViewById(R.id.btnClick).setOnClickListener(btn ->{
                                        String urlGPS = "https://www.google.com/maps/@"+location.getLatitude()+" , "+location.getLongitude()+" ,15z";
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setData(Uri.parse(urlGPS));
                                        startActivity(intent);
                                    });
                                }
                            });
                        } else if (coarseLocationGranted != null && coarseLocationGranted) {
                            // Only approximate location access granted.

                        } else {
                          AlertDialog.Builder builder = new AlertDialog.Builder(this);
                          builder.setTitle("Xác nhận !!!");
                          builder.setMessage("Bạn cần cho phép truy cập GPS thì mới lấy được vị trí !");
                          builder.setCancelable(false);
                          builder.setPositiveButton("Đã hiểu", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {
                                  findViewById(R.id.btnClick).setOnClickListener(btn ->{
                                      dialog.cancel();
                                  });
                              }
                          });
                          builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {
                                  Toast.makeText(MainActivity.this,"Đã hủy !",Toast.LENGTH_SHORT).show();
                                  dialog.cancel();
                              }
                          });
                          AlertDialog sh = builder.create();
                          sh.show();
                        }
                    }
                );
                locationPermissionRequest.launch(new String[] {
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                });
            }else{
                int requestGPS1 =  ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
                int requestGPS2 =  ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

                if(requestGPS1 != PackageManager.PERMISSION_DENIED  && requestGPS2 != PackageManager.PERMISSION_DENIED){
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},999);
                }else{

                }
            }
    }
}