package vn.edu.poly.mob201_lab2_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String content = intent.getStringExtra("content");
        checkData(context,content);

    }

    private void checkData(Context context,String content){
        if(!content.matches(".*\\w.*")){
            notificatorToast(context,"Hãy nhập dữ liệu!");
        }else if(!(content.length() == 9)){
            notificatorToast(context,"Hãy nhập đủ 9 kí tự!");
        }else if(content.equals("MEM537128")){
            notificatorToast(context,"Bạn được khuyến mãi 10%!");
        } else if(content.equals("MEM537129")){
            notificatorToast(context,"Bạn được khuyến mãi 20%!");
        } else if(content.equals("VIP537128")){
            notificatorToast(context,"Bạn được khuyến mãi 30%!");
        } else if(content.equals("VIP537129")){
            notificatorToast(context,"Bạn được khuyến mãi 50%!");
        }else if(content.substring(0,3).equals("MEM")){
            notificatorToast(context,"Bạn được khuyến mãi từ 10 -> 20%!");
        } else if(content.substring(0,3).equals("VIP")){
            notificatorToast(context,"Bạn được khuyến mãi từ 30 -> 50%!");
        } else if(!content.substring(0,3).equals("MEM") || !content.substring(0,3).equals("VIP")) {
            notificatorToast(context, "Chuỗi nhập bắt đầu bằng “MEM” hay “VIP”!");
        }else {
            notificatorToast(context,"Bạn không có khuyến mãi!");
        }

    }


    private void notificatorToast(Context context,String content){
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
