package com.gabe.mychat.test;

import java.util.Date;

import static java.lang.Thread.sleep;

public class datetest {
    public static void main(String[] args) throws InterruptedException {
        Date date=new Date();
        thread thread=new thread();
        thread.start();
        sleep(3000);
        thread.exit=true;
        thread.join();
        Date date1=new Date();
        System.out.print(date1.getTime()-date.getTime());
    }
    static class thread extends Thread{
        public volatile boolean exit = false;
        @Override
        public void run() {
            while(!exit);
        }
    }

}
