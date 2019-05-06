package com.example.evan.evanbroadcastreceivertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        MainActivity中显示要怎么写？
        Toast.makeText(context,"接到的信息是--->"+intent.getAction()+"\n消息内容是--》"
                +intent.getStringExtra("msg"),Toast.LENGTH_LONG).show();
    }
}
