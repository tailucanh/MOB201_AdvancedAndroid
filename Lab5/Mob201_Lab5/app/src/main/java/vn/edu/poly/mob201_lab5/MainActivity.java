package vn.edu.poly.mob201_lab5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {

    String link = "http://rss.weather.com.au/nsw/sydney";
    RecyclerView recyclerViewItems;
    TextView tvNotification;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewItems = findViewById(R.id.viewRss);
        tvNotification = findViewById(R.id.tvNotification);
        tvNotification.setVisibility(View.INVISIBLE);

        AsyncTask asyncTask = new AsyncTask() {
                @Override
                protected Object doInBackground(Object[] objects) {
                    return getData();
                }
                @Override
                protected void onPostExecute(Object o) {
                    super.onPostExecute(o);
                    findViewById(R.id.btnDownload).setOnClickListener(btn ->{
                        ArrayList<ObjItems> lists = (ArrayList<ObjItems>)o;
                        itemAdapter = new ItemAdapter(lists);
                        recyclerViewItems.setAdapter(itemAdapter);
                        tvNotification.setVisibility(View.VISIBLE);
                        if(lists.isEmpty()){
                            tvNotification.setText("-> Tải dữ liệu không thành công!");
                        }else {
                            tvNotification.setText("-> Tải dữ liệu thành công");
                        }
                    });
                }
            };
        asyncTask.execute();
    }

    private ArrayList<ObjItems> getData() {
        ArrayList<ObjItems> listItems = new ArrayList<>();
        try {

            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, "utf-8");
            int event = parser.getEventType();
            String text = "";
            ObjItems objItems = null;
            while (event != XmlPullParser.END_DOCUMENT) {
                String tag = parser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if(tag.equalsIgnoreCase("item")){
                            objItems = new ObjItems();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(objItems != null){
                            if (tag.equalsIgnoreCase("title")) {
                                objItems.setTitle(text);
                            } else if (tag.equalsIgnoreCase("link")) {
                                objItems.setLink(text);
                            } else if (tag.equalsIgnoreCase("description")) {
                                objItems.setDescription(text);
                            } else if (tag.equalsIgnoreCase("pubDate")) {
                                objItems.setPubDate(text);
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