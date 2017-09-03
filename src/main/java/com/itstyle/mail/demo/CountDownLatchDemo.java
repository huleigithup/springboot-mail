package com.itstyle.mail.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
/**
 * CountDownLatch，一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待
 * 构造方法参数指定了计数的次数
 * countDown方法，当前线程结束执行后调用，计数减一 
 * awaint方法，调用此方法会一直阻塞当前线程，直到计时器的值为0
 * 创建者 科帮网
 * 创建时间	2017年8月15日
 *
 */
public class CountDownLatchDemo {
	final static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	
	final static String startTime = sdf.format(new Date());

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(2);// 两个赛跑者
		Runer runer1 = new Runer("刘翔", 5000, latch);
		Runer runer2 = new Runer("罗伯斯", 8000, latch);
		runer1.start();//刘翔   开始跑步
		runer2.start();//罗伯斯 开始跑步
		latch.await();// 等待所有人赛跑结束
		System.out.println("all runer done at " + sdf.format(new Date()));
	}

	static class Runer extends Thread {
		String runerName;
		int runTime;
		CountDownLatch latch;

		public Runer(String runerName, int runTime, CountDownLatch latch) {
			this.runerName = runerName;
			this.runTime = runTime;
			this.latch = latch;
		}

		public void run() {
			System.out.println("Runer " + runerName + " do run begin at "
					+ startTime);
			doRun();//跑步
			System.out.println("Runer " + runerName + " do run complete at "
					+ sdf.format(new Date()));
			latch.countDown();// 终点结束，计数器减一

		}

		private void doRun() {
			try {
				Thread.sleep(runTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
