package vn.edu.poly.thimob201_27_10;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class RssAdapter extends RecyclerView.Adapter<RssAdapter.MyItemViewHolder> {

    ArrayList<ObjectRss> listsItem;
    Context context;
    ObjectDAO objectDAO;

    public RssAdapter(ArrayList<ObjectRss> listsItem, Context context,  ObjectDAO objectDAO) {
        this.listsItem = listsItem;
        this.context = context;
        this.objectDAO = objectDAO;
    }
    public void setFilter(ArrayList<ObjectRss> filterList){
        this.listsItem = filterList;
        notifyDataSetChanged();
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
        holder.tvLink.setText("Link: " +items.getLink());
        holder.tvContent.setText(Html.fromHtml(items.getDescription()));
        holder.layoutItem.setOnClickListener(layout ->{
            dialogInfo(layout.getContext(), items,objectDAO);
        });
    }

    @Override
    public int getItemCount() {
        return listsItem.size();
    }


    public class MyItemViewHolder extends  RecyclerView.ViewHolder {
        TextView tvTitle, tvContent,tvLink;
        ConstraintLayout layoutItem;
        public MyItemViewHolder(@NonNull View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvLink = view.findViewById(R.id.tvLink);
            tvContent = view.findViewById(R.id.tvContent);
            layoutItem = view.findViewById(R.id.layoutItem);

        }
    }

    public void dialogInfo(Context context,ObjectRss objectRss,ObjectDAO objectDAO){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_info);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        EditText edTitle = dialog.findViewById(R.id.edTitle);
        EditText edLink = dialog.findViewById(R.id.edLink);
        EditText edContent = dialog.findViewById(R.id.edContent);
        edTitle.setText(objectRss.getTitle());
        edLink.setText(objectRss.getLink());
        edContent.setText(Html.fromHtml(objectRss.getDescription()));

        ObjectRss objectRss1 = new ObjectRss();

        dialog.findViewById(R.id.btnSave).setOnClickListener(btn ->{
            objectRss1.setTitle(edTitle.getText().toString());
            objectRss1.setLink(edLink.getText().toString());
            objectRss1.setDescription(edContent.getText().toString());
            objectDAO.insertItem(objectRss1);
            Toast.makeText(context, "Lưu thành công!", Toast.LENGTH_SHORT).show();
            dialog.cancel();
        });
        dialog.show();


    }


}
