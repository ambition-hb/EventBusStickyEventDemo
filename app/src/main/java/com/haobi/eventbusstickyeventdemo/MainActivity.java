package com.haobi.eventbusstickyeventdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private Button bt_message;//跳转到SecondActivity
    private Button bt_subscription;//注册事件
    private TextView tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_subscription = (Button)findViewById(R.id.bt_subscription);
        bt_message = (Button)findViewById(R.id.bt_message);
        tv_message = (TextView)findViewById(R.id.tv_message);
        bt_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
        bt_subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注册事件
                EventBus.getDefault().register(MainActivity.this);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }

    //事件订阅者处理黏性事件，用TexiView来展示接收到的事件消息
    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onMoonStickyEvent(MessageEvent messageEvent){
        tv_message.setText(messageEvent.getMessage());
    }
}
