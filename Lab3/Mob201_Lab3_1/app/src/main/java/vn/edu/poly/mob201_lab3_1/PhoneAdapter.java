package vn.edu.poly.mob201_lab3_1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PhoneAdapter extends BaseAdapter {
    ArrayList<PhoneDTO> listPhone;
    Context context;

    public PhoneAdapter(ArrayList<PhoneDTO> listPhone, Context context) {
        this.listPhone = listPhone;
        this.context = context;
    }
    @Override
    public int getCount() {
        return listPhone.size();
    }

    @Override
    public Object getItem(int position) {
        PhoneDTO phoneDTO = listPhone.get(position);
        return phoneDTO;
    }

    @Override
    public long getItemId(int position) {
        PhoneDTO phoneDTO = listPhone.get(position);
        return phoneDTO.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemPhone;
        if(convertView == null){
            itemPhone= View.inflate(context,R.layout.item_phone,null);
        } else{
            itemPhone = convertView;
        }
        PhoneDTO obj = listPhone.get(position);
        TextView name = itemPhone.findViewById(R.id.tvName);
        TextView phone = itemPhone.findViewById(R.id.tvPhone);
        name.setText(obj.getName());
        phone.setText(obj.getPhoneNumber());
        return itemPhone;
    }
}
