package com.lange.commonplugin;

import android.util.Log;

/**
 * Created by jianan on 2015/6/9.
 * 日志工具
 */
public class CommLog {
	private static  String TAG = "mark";
	private static boolean isShowLog = true;
	
	public static void setShowLog(boolean bl){
		isShowLog = bl;
	}
	
	public static void initLogger(String tmpTag){
		TAG = tmpTag;
	}

	public static void v(String tag, String msg) {
		if (isShowLog)
			Log.v(tag, msg == null ? "" : msg);
	}

	public static void v(String tag, String msg, Throwable tr) {
		if (isShowLog)
			Log.v(tag, msg == null ? "" : msg, tr);
	}

	public static void v(String msg) {
		if (isShowLog)
			Log.v(TAG, msg == null ? "" : msg);
	}

	public static void v(String msg, Throwable tr) {
		if (isShowLog)
			Log.v(TAG, msg == null ? "" : msg, tr);
	}

	public static void d(String tag, String msg) {
		if (isShowLog)
			Log.d(tag, msg == null ? "" : msg);
	}

	public static void d(String tag, String msg, Throwable tr) {
		if (isShowLog)
			Log.d(tag, msg == null ? "" : msg, tr);
	}

	public static void d(String msg) {
		if (isShowLog)
			Log.d(TAG, msg == null ? "" : msg);
	}

	public static void d(String msg, Throwable tr) {
		if (isShowLog)
			Log.d(TAG, msg == null ? "" : msg, tr);
	}

	public static void e(String tag, String msg) {
		if (isShowLog)
			Log.e(tag, msg == null ? "" : msg);
	}

	public static void e(String tag, String msg, Throwable tr) {
		if (isShowLog)
			Log.e(tag, msg == null ? "" : msg, tr);
	}

	public static void e(String msg) {
		if (isShowLog)
			Log.e(TAG, msg == null ? "" : msg);
	}

	public static void e(String msg, Throwable tr) {
		if (isShowLog)
			Log.e(TAG, msg == null ? "" : msg, tr);
	}

}
