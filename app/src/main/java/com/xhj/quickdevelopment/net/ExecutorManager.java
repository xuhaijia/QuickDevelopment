package com.xhj.quickdevelopment.net;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author @Qiu
 * @version V1.0
 * @Description:线程池管理
 * @date 2017/7/7 15:58
 */
public class ExecutorManager {

	// 线程池的大小
	private static int threadSize = 5;
	// 创建一个可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程
	protected static ExecutorService threadPool;

	// 创建
	public static ExecutorService getInstance() {
		// 线程池
		if (threadPool == null) {
			synchronized (ExecutorManager.class) {
				if (threadPool == null) {
					// 缓存线程池
					threadPool = Executors.newCachedThreadPool();
					// threadPool = Executors.newFixedThreadPool(threadSize);
				}
			}
		}
		return threadPool;
	}
}
