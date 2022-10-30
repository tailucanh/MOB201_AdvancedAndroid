package vn.edu.poly.thimob201_27_10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class SaveNewsActivity extends AppCompatActivity {

    RecyclerView listSave;
    ObjectDAO objectDAO;
    RssSaveAdapter rssSaveAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_news);
        listSave = findViewById(R.id.listSaveNews);

        objectDAO = new ObjectDAO(getApplicationContext());
        rssSaveAdapter = new RssSaveAdapter(objectDAO.selectAll(),getApplicationContext());
        listSave.setAdapter(rssSaveAdapter);





    }
}