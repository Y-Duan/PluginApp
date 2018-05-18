package com.lange.pluginapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lange.pluginlib.IShowString;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化插件管理对象
        PluginResource pluginResource = PluginResource.getInstance();
        if(!pluginResource.initPluginResource(this))return;
        //获取接口对象
        IShowString iShowString = pluginResource.getPluginString();
        if(iShowString!=null){
            String toast = iShowString.showString("1","2");
            Toast.makeText(this,toast,Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"对象为空",Toast.LENGTH_SHORT).show();
        }
    }
}
