package vn.edu.poly.thimob201_27_10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RssSaveAdapter extends RecyclerView.Adapter<RssSaveAdapter.MyItemViewHolder> {

    ArrayList<ObjectRss> listsItem;
    Context context;


    public RssSaveAdapter(ArrayList<ObjectRss> listsItem, Context context) {
        this.listsItem = listsItem;
        this.context = context;

    }

    @NonNull
    @Override
    public MyItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        return new MyItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyItemViewHolder holder, int position) {
        ObjectRss items = listsItem.get(position);
        holder.tvTitle.setText(items.getTitle());
        holder.tvContent.setText(items.getDescription());
        holder.tvLink.setText("Link: "+items.getLink());

    }

    @Override
    public int getItemCount() {
        return listsItem.size();
    }


    public class MyItemViewHolder extends  RecyclerView.ViewHolder {
        TextView tvTitle, tvLink,tvContent;
        ConstraintLayout layoutItem;
        public MyItemViewHolder(@NonNull View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvContent = view.findViewById(R.id.tvContent);
            tvLink = view.findViewById(R.id.tvLink);
            layoutItem = view.findViewById(R.id.layoutItem);

        }
    }

}
