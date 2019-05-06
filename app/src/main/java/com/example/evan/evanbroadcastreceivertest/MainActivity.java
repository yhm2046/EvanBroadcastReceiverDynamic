package com.example.evan.evanbroadcastreceivertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnTest;
    public TextView tvShow;
    private String brocastReceiverStartLog="android.intent.action.gmt.logupload";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTest=(Button)findViewById(R.id.btn_show);
        tvShow=(TextView)findViewById(R.id.tv_show);
        //动态注册广播
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(brocastReceiverStartLog);
        MyReceiver2 receiver2=new MyReceiver2();
        registerReceiver(receiver2,intentFilter);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //按下按钮发送广播
                Intent intent=new Intent();
                intent.setAction(brocastReceiverStartLog);
                intent.putExtra("msg","开始记录log");
                sendBroadcast(intent);

            }
        });
    }

    /**
     * 动态注册需在Acticity生命周期onPause通过
     *      *unregisterReceiver()方法移除广播接收器，
     *      * 优化内存空间，避免内存溢出
     */
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(new MyReceiver2());
    }

    //广播接收类
    public class MyReceiver2 extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
//        MainActivity中显示要怎么写？
            Toast.makeText(context,"wp接到的信息是--->"+intent.getAction()+"\n消息内容是--》"
                    +intent.getStringExtra("msg"),Toast.LENGTH_LONG).show();
            tvShow.setText("wp show -->"+intent.getStringExtra("msg"));
        }
    }

}
