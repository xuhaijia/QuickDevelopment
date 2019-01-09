package com.xhj.quickdevelopment.utils;

import android.content.Context;
import android.os.Environment;

/**
 * @author Qiu
 * @version V1.0
 * @Description:文件管理
 * @date 2017/7/11 下午2:14
 */
public class FileUtils {
	/**
	 * 创建根缓存目录
	 *
	 * @return
	 */
	public static String createRootPath(Context context) {
		String cacheRootPath = "";
		if (isSdCardAvailable()) {
			// /sdcard/Android/data/<application package>/cache
			cacheRootPath = context.getExternalCacheDir().getPath();
		} else {
			// /data/data/<application package>/cache
			cacheRootPath = context.getCacheDir().getPath();
		}
		return cacheRootPath;
	}

	/**
	 * 判断是否有SD卡
	 *
	 * @return
	 */
	public static boolean isSdCardAvailable() {
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}
}
