package com.lange.commonplugin;

import android.content.Context;

/**
 * Created by shengxiang on 2016/1/20.
 */
public class SharePluginAgent {

    public SharePluginAgent(){

    }

    public Boolean init(Context context){
        return true;
    }

    public Boolean hashPlugin(String pluginName){
        return true;
    }

    public String getPluginUrl(String pluginName){
        return "";
    }

    public Boolean shareToChannel(String pluginName){
        return true;
    }

}
