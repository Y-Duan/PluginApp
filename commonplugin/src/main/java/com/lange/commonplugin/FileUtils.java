package com.lange.commonplugin;

import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jianan on 2015/6/9.
 * 文件工具
 */
public class FileUtils {
	
	public static final int  SIZE_MB = 1*1024*1024;
	public static final int  SIZE_KB = 1*1024;

	/**
	 * 外置存储卡的路径
	 */
	public static String getExternalStorePath() {
		if (isExistExternalStore()) {
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		}
		return null;
	}
	
	/**
	 * 是否有外存卡
	 */
	public static boolean isExistExternalStore() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}


	//拷贝文件到 targetFile
	public static void copyFile(File sourceFile,File targetFile)
			throws IOException{
		// 新建文件输入流并对它进行缓冲
		FileInputStream input = new FileInputStream(sourceFile);
		BufferedInputStream inBuff=new BufferedInputStream(input);

		// 新建文件输出流并对它进行缓冲
		FileOutputStream output = new FileOutputStream(targetFile);
		BufferedOutputStream outBuff=new BufferedOutputStream(output);

		// 缓冲数组
		byte[] b = new byte[1024 * 5];
		int len = -1;
		while ((len =inBuff.read(b)) != -1) {
			outBuff.write(b, 0, len);
		}
		// 刷新此缓冲的输出流
		outBuff.flush();

		//关闭流
		inBuff.close();
		outBuff.close();
		output.close();
		input.close();
	}


	public static void writeToFile(InputStream dataIns, File target) throws IOException {
		final int BUFFER = 1024;
		int count;
		byte data[] = new byte[BUFFER];
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(target));
		try{
			while ((count = dataIns.read(data, 0, BUFFER)) != -1) {
				bos.write(data, 0, count);
			}
		} finally {
			bos.close();
		}
	}


}
