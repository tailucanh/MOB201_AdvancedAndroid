package vn.edu.poly.democontentprovider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.ContentProvider;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    String linkContact = "content://contacts/people";
    String CONTACTS = Manifest.permission.READ_CONTACTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnShowContacts).setOnClickListener((btn) ->{
            // 1.Kiểm tra cấp quyền
            if(ActivityCompat.checkSelfPermission(this,CONTACTS) != PackageManager.PERMISSION_GRANTED){
                requestPermission();
            }else {
                readContacts();
            }
            // 2.Xin quyền đọc contacts từ người dùng


        });


    }

    public void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{CONTACTS},999);
    }

    public void readContacts(){
        Uri uri = Uri.parse(linkContact);

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        //Đọc con trỏ
        ArrayList<String> listContacts = new ArrayList<>();


        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()){
                String id = cursor.getString(0);
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                listContacts.add(id +" "+name);
                cursor.moveToNext();

            }
            cursor.close();
            Toast.makeText(this, listContacts.size(), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Không thấy dữ liệu !", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("zzzz",grantResults.length +" ");
        Log.e("zzzz",grantResults[0] +" ");
        if(requestCode == 999 & grantResults[0] == 0){
            readContacts();
        }else {
            Toast.makeText(this, "Do bạn k cấp quyền !", Toast.LENGTH_LONG).show();
        }
    }
}








