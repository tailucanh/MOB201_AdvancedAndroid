package vn.edu.poly.mob201_lab3_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Browser;
import android.provider.ContactsContract;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listBookMark;
    BookMarkAdapter bookMarkAdapter;
    ArrayList<BookmarkDTO> lists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    
}