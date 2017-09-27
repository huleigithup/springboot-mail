package com.itstyle.mail.demo;

public class Ticket extends Thread {
	private int num = 100;
    public void run(){
    	System.out.println("开始售票:"+num--);
    }
    
    public static void main(String[] args) throws InterruptedException {
    	//new Ticket().start();
    	//new Ticket().start();
    	//new Ticket().start();
    	Ticket t =  new Ticket();
    	new Thread(t).start();
    	new Thread(t).start();
    	new Thread(t).start();
	}
}
