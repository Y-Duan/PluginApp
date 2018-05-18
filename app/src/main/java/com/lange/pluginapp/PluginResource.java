package com.lange.pluginapp;

import android.content.Context;
import android.widget.Toast;

import com.lange.commonplugin.CommLog;
import com.lange.commonplugin.FileUtils;
import com.lange.commonplugin.Globals;
import com.lange.commonplugin.PlugInfo;
import com.lange.commonplugin.PluginManager;
import com.lange.pluginlib.IShowString;

import java.io.File;
import java.io.Serializable;

/**
 * Created by lange on 2016/3/1.
 */
public class PluginResource implements Serializable{

    public static  String PLUGIN_PATH = FileUtils.getExternalStorePath() + File.separator + "plugin";//插件存放的外部存储地址
    public static  String PLUGIN_LAST_NAME = "plugin.apk";//插件名称
    private Context instance;
    public static IShowString iShowString;
    public static PluginManager pluginManager;
    public static PlugInfo plugInfo;

    private static PluginResource _p;
    private PluginResource(){}
    public static PluginResource getInstance(){
        if (_p == null) _p = new PluginResource();
        return _p;
    }


    public boolean initPluginResource(Context instance){
        this.instance = instance;
        //ini Plugin
        PluginManager.init(instance);
        String s = PluginManager.getPhoneCPU();
        PluginResource.pluginManager = PluginManager.getSingleton();
        String apk_path = PLUGIN_PATH+ File.separator+PLUGIN_LAST_NAME;
        String apkDexPath = instance.getDir(Globals.PRIVATE_PLUGIN_ODEX_OUTPUT_DIR_NAME, Context.MODE_PRIVATE).getAbsolutePath()+File.separator+PLUGIN_LAST_NAME;
        //checkPluginIsExists(instance, apkDexPath, apk_path);
        if(PluginResource.plugInfo==null){
            if(new File(apk_path).exists()){
                loadApk(apk_path);
            }else{
                Toast.makeText(instance,"插件不存在",Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if(PluginResource.iShowString==null){
            initSpeechTool(instance);
        }
        /*if(PluginResource.plugInfo!=null){
            PlugInfo plugInfo = PluginResource.plugInfo;
            Resources r = plugInfo.getResources();
            String res="";
            try{
                InputStream in = r.getAssets().open("plugin.txt");
// \Test\assets\yan.txt这里有这样的文件存在
                int length = in.available();
                byte [] buffer = new byte[length];
                in.read(buffer);
                res = EncodingUtils.getString(buffer, "UTF-8");
            }catch(Exception e){
                e.printStackTrace();
            }
            CommUtils.showToast("这是插件中的Assets:="+res);
        }*/
        return true;
    }


    public static void loadApk(String path) {
        try {
            CommLog.e("start  loading:" + PluginResource.plugInfo);
            PluginResource.plugInfo = PluginResource.pluginManager.loadPlugin(new File(path));
            CommLog.e("load  success:"+PluginResource.plugInfo);
        }catch (Exception e){
            PluginResource.plugInfo = null;
            e.getStackTrace();
        }
    }

    /*public static void loadApk(String path,String dexfile) {
        try {
            PluginResource.plugInfo = PluginResource.pluginManager.loadPluginDex(new File(path), dexfile);
        }catch (Exception e){
            PluginResource.plugInfo = null;
            e.getStackTrace();
        }
    }*/

    public static void loadMyApk(String path){
        try {
            PluginResource.plugInfo = PluginResource.pluginManager.loadMyPlugin(new File(path));
        }catch (Exception e){
            PluginResource.plugInfo = null;
            e.getStackTrace();
        }
    }

    public static void initSpeechTool(Context instance) {
        //File apk = new File("/storage/emulated/0/Download/app-release-unsigned.apk");
        try {
            CommLog.e("PluginResource.plugInfo:"+PluginResource.plugInfo);

            PluginResource.iShowString = (IShowString) plugInfo.getClassLoader().loadClass("com.lange.pluginapk.ShowStringImpl").newInstance();
            //PluginResource.iShowString = (IShowString) PluginResource.pluginManager.getClassInstance(PluginResource.plugInfo, "com.lange.pluginapk.ShowStringImpl");
            Object o = PluginResource.iShowString;
        }catch (Exception e){
            PluginResource.iShowString = null;
            e.printStackTrace();
        }
    }

    /*public static void initLameMp3Tool(){
        try {
            PluginResource.pluginLameMp3Encoder = (PluginLameMp3Encoder)PluginResource.pluginManager.getClassInstance(PluginResource.plugInfo, "com.anybeen.mark.plugin.xunfeispeech.MyPluginLameMp3Encoder");
        }catch (Exception e){
            PluginResource.pluginLameMp3Encoder = null;
            e.printStackTrace();
        }
    }*/

    /**
     * 检测插件是否存在
     * @param instance
     * @param apkDexPath
     * @param apk_path
     */
    public static void checkPluginIsExists(Context instance,String apkDexPath,String apk_path){
        File pluginApk = new File(apkDexPath);
        //插件
        if(!pluginApk.exists()){
            //TODO 不存在就去下载
            //TODO 这里就不实现了
            /*if(CommUtils.isNetAvailable(instance)){
                //initDownloadPluginTool(instance,phoneInfo,apk_path);
            }*/
        }
        else if(pluginApk.length()>1000){
            long s = pluginApk.length();
            CommLog.e(s+"大小");
            //CommUtils.showToast("存在插件!");
        }else{
            pluginApk.delete();
        }
    }


    /**
     * 下载插件
     */
    public static void initDownloadPluginTool(final Context instance,String phoneInfo,final String apk_path) {
       /* DownLoadPlugin downLoadPlugin = new DownLoadPlugin(instance,phoneInfo+PluginManager.PLUGIN_LAST_NAME);
        downLoadPlugin.downloadPlugin(new OnDownloadCompleteListener() {
            @Override
            public void onDownloadComplete() {
                //下载完成
                //CommUtils.showToast("下载完成!");
                //语音
                loadApk(apk_path);

                *//**
         * 发送广播
         *//*
                Intent i = new Intent("com.anybeen.mark.app.VOICE_PLUGIN");
                i.putExtra("isLoaded",true);
                instance.sendBroadcast(i);
                *//*PluginManager.initSpeechTool(instance);
                PluginManager.initLameMp3Tool();*//*
                File apk = new File(apk_path);
                if(apk.exists())
                    apk.delete();
            }
        });*/
    }

   /* public static void loadApkDex(String dexPath){
        try {
            PluginResource.plugInfo = PluginResource.pluginManager.loadPlugin(new File(dexPath));
        }catch (Exception e){
            PluginResource.plugInfo = null;
            e.getStackTrace();
        }
    }*/

    /**
     * 获得插件中的对象
     * @return
     */
    public IShowString getPluginString(){
        if(null==iShowString){
            initSpeechTool(instance);
        }
        return iShowString;
    }
}
