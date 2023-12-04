package com.web.Thread;


import java.util.Scanner;

public class MyThread extends Thread
{
	int count = 0,a=0;
	String threadName;
	long time = System.currentTimeMillis();
	BookingThread b = new BookingThread();

	public MyThread(String threadName, int i) {
		this.threadName = threadName;
		this.count = i * 1000;
		this.a	= i;
	}

	@Override
	public void run() {
		try {
				while (true) {
//					System.out.println(Thread.activeCount() + " " +threadName +"\n");
//					System.out.println("Waiting for "+this.a+" seconds \n");
					Thread.sleep(this.count);
//					System.out.println(" Booking No "+b.count+" \n");
//					b.updateBooking();
//					if ( b.count1 >=2)
					{
						System.out.println("**********************End************************");
						break;
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		System.out.println("Enter a Time in seconds to Start the Process ?");
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		System.out.println("**********************Start**********************\n");
		MyThread m1 = new MyThread("Booking Thread", a);
		m1.start();
		sc.close();
	}
}
