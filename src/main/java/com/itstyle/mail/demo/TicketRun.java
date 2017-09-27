package com.itstyle.mail.demo;

public class TicketRun implements Runnable {
	private int num = 100;

	@Override
	public void run() {
		System.out.println("开始售票:" + num--);
	}

	public static void main(String[] args) {
		TicketRun tr =  new TicketRun();
	}
}
