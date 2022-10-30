package vn.edu.poly.democameramedia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.accounts.AccountsException;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    String urlMusic = "https://data.chiasenhac.com/down2/2261/2/2260237-44280fbe/128/Phao%20Hong%20-%20Dat%20Long%20Vinh.mp3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnPlayMusic).setOnClickListener(btn ->{
            mediaPlayer = MediaPlayer.create(this, Uri.parse(urlMusic) );
            if(!mediaPlayer.isPlaying()){
                mediaPlayer.start();
            }
            //Lấy tổng thời gian bài hát
            int duration = mediaPlayer.getDuration();  //Tính theo s
            int current = mediaPlayer.getCurrentPosition(); // lấy thời gian đang chạy
        });

        //Camera intent
        //Camera API
        findViewById(R.id.btnLoadImg).setOnClickListener(btn->{
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},999);
            }else {
                try {
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture,1111);
                }catch (ActivityNotFoundException e){

                }
            }
        });

        //Load ảnh từ thư viện



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1111){
            ImageView img = findViewById(R.id.imgThumb);
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(bitmap);
        }
    }
}