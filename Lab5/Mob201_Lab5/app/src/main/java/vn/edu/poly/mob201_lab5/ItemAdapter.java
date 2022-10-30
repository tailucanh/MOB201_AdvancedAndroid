package vn.edu.poly.mob201_lab5;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyItemViewHolder> {

    ArrayList<ObjItems> listsItem;

    public ItemAdapter(ArrayList<ObjItems> listsItem) {
        this.listsItem = listsItem;
    }

    @NonNull
    @Override
    public MyItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_rss,parent,false);
        return new ItemAdapter.MyItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyItemViewHolder holder, int position) {
        ObjItems items = listsItem.get(position);
        holder.tvTitle.setText(items.getTitle());
        holder.tvContent.setText(Html.fromHtml(items.getDescription()));
        holder.tvDate.setText(items.getPubDate());
    }

    @Override
    public int getItemCount() {
        return listsItem.size();
    }


    public class MyItemViewHolder extends  RecyclerView.ViewHolder{
        TextView tvTitle, tvContent,tvDate;
        public MyItemViewHolder(@NonNull View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvContent = view.findViewById(R.id.tvContent);
            tvDate = view.findViewById(R.id.tvDateTime);
        }
    }

}
