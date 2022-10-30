package vn.edu.poly.demoxmlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.net.UrlQuerySanitizer;
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

public class MainActivity extends AppCompatActivity {

    String link = "https://vnexpress.net/rss/tin-moi-nhat.rss";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                return getData();
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                ArrayList<TinTuc> tinTucs = (ArrayList<TinTuc>) o;
                //Nạp tin tức vào trong adapter hiển thị listView, Recycle view
            }
        };
        asyncTask.execute();

    }

    private ArrayList<TinTuc> getData() {
        ArrayList<TinTuc> listTinTuc = new ArrayList<>();
        try {
            //1.validate url
            URL url = new URL(link);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            //2.khởi tạo đối tượng paster- bóc dữ liệu xml
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, "utf-8");
            //3 - parse
            int event = parser.getEventType();
            String text = "";
            TinTuc tinTuc = null;
            while (event != XmlPullParser.END_DOCUMENT) {
                String tag = parser.getName();// lấy ra tên của tag
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if(tag.equalsIgnoreCase("item")){
                            tinTuc = new TinTuc();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(tinTuc != null){
                            if (tag.equalsIgnoreCase("title")) {
                                tinTuc.title = text;
                            } else if (tag.equalsIgnoreCase("description")) {
                                tinTuc.description = text;
                            } else if (tag.equalsIgnoreCase("pubDate")) {
                                tinTuc.pubData = text;
                            } else if (tag.equalsIgnoreCase("link")) {
                                tinTuc.link = text;
                            }else if(tag.equalsIgnoreCase("item")){
                                listTinTuc.add(tinTuc);
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
        return  listTinTuc;
    }
}