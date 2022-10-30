package vn.edu.poly.mob201_lab3_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listPhones;
    PhoneAdapter phoneAdapter;
    ArrayList<PhoneDTO> lists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listPhones = findViewById(R.id.listPhone);
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        }
        findViewById(R.id.btnClick).setOnClickListener(btn ->{
            getContactListPhone();
            phoneAdapter = new PhoneAdapter(lists,this);
            listPhones.setAdapter(phoneAdapter);
        });


    }
    public void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.READ_PHONE_STATE},999);
    }


    public void getContactListPhone(){
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        while(cursor !=null && cursor.moveToNext()){
            int idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            int hasPhone = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);

            String strID = cursor.getString(idIndex);
            String strName= cursor.getString(nameIndex);
            if(cursor.getInt(hasPhone)>0){
                Cursor pCur = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        new String[]{strID},null
                );
                while(pCur.moveToNext()) {
                    int numberPhone = pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String strPhone = pCur.getString(numberPhone);

                    PhoneDTO obj = new PhoneDTO();
                    obj.setId(Integer.parseInt(strID));
                    obj.setName(strName);
                    obj.setPhoneNumber(strPhone);
                    lists.add(obj);
                }
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode != 999 & grantResults[0] != 0){
            Toast.makeText(this, "Do bạn chưa cấp quyền !", Toast.LENGTH_LONG).show();
        }
    }
}