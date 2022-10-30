package vn.edu.poly.mob201_lab3_2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BookMarkAdapter extends BaseAdapter {
    ArrayList<BookmarkDTO> lists;
    Context context;

    public BookMarkAdapter(ArrayList<BookmarkDTO> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        BookmarkDTO bookmarkDTO = lists.get(position);

        return bookmarkDTO;
    }

    @Override
    public long getItemId(int position) {
        BookmarkDTO bookmarkDTO = lists.get(position);
        return bookmarkDTO.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View items;
        if(convertView == null){
            items= View.inflate(context,R.layout.item_bookmark,null);
        }
        else{
            items = convertView;
        }

        BookmarkDTO obj = lists.get(position);


        TextView name = items.findViewById(R.id.tvTitle);
        TextView phone = items.findViewById(R.id.tvLink);

        name.setText(obj.getTitle());
        phone.setText(obj.getLinkUrl());

        return items;
    }
}
