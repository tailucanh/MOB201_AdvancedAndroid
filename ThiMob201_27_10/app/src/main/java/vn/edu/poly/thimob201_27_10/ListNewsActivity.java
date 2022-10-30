package vn.edu.poly.thimob201_27_10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Xml;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class ListNewsActivity extends AppCompatActivity {
    String link = "https://danviet.vn/rss/home.rss";
    RecyclerView recyclerViewItems;
    RssAdapter rssAdapter;
    ObjectDAO objectDAO;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);
        recyclerViewItems = findViewById(R.id.listNews);
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                return getData();
            }
            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                ArrayList<ObjectRss> lists = (ArrayList<ObjectRss>)o;
                objectDAO = new ObjectDAO(getApplicationContext());
                rssAdapter = new RssAdapter(lists,getApplicationContext(),objectDAO);
                recyclerViewItems.setAdapter(rssAdapter);
                searchView = findViewById(R.id.searchView);
                searchView.clearFocus();
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }
                    @Override
                    public boolean onQueryTextChange(String newText) {
                        ArrayList<ObjectRss> lists = new ArrayList<>();
                        for(ObjectRss objectRss : objectDAO.selectAll()){
                            if(objectRss.getTitle().toLowerCase().contains(newText.toLowerCase())){
                                lists.add(objectRss);
                            }
                        }
                        if(lists.isEmpty()){
                            Toast.makeText(ListNewsActivity.this, "không tìm thấy!", Toast.LENGTH_SHORT).show();
                        }else {
                            rssAdapter.setFilter(lists);
                        }

                        return true;
                    }
                });

            }
        };
        asyncTask.execute();

    }

    private ArrayList<ObjectRss> getData() {
        ArrayList<ObjectRss> listItems = new ArrayList<>();
        try {

            URL url = new URL(link);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, "utf-8");
            int event = parser.getEventType();
            String text = "";
            ObjectRss objItems = null;
            while (event != XmlPullParser.END_DOCUMENT) {
                String tag = parser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if(tag.equalsIgnoreCase("item")){
                            objItems = new ObjectRss();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(objItems != null){
                            if (tag.equalsIgnoreCase("title")) {
                                objItems.setTitle(text);
                            }else if (tag.equalsIgnoreCase("description")) {
                                    objItems.setDescription(text);
                            } else if (tag.equalsIgnoreCase("link")) {
                                objItems.setLink(text);
                            }else if(tag.equalsIgnoreCase("item")){
                                listItems.add(objItems);
                            }
                        }
                        break;
                }
                event = parser.next();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  listItems;
    }

}