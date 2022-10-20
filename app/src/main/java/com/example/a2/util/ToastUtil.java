package com.example.a2.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtil {
    private static Toast mToast;
    public static void showMsg(Context context, String msg){
        //mToast.setGravity(Gravity.CENTER,0,0);
        if(mToast==null){
            mToast=Toast.makeText(context,msg,Toast.LENGTH_SHORT);

        }else{
            mToast.setText(msg);

        }

        mToast.show();
    }
}
