package com.yxj.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @author:yuxj
 * @descriptio  Exchanger
 *
 *
 *
 *
 * @create:2019-03-18 22:12
 */
public class ExchangeExample {



    static class Producer implements Runnable{

        private List<String> buffer;

        private Exchanger<List<String>> exchanger;

        Producer(List<String> buffer,Exchanger<List<String>> exchanger){
            this.buffer = buffer;
            this.exchanger = exchanger;
        }

        @Override
        public void run() {

            for(int i=1;i<5;i++){
                System.out.println("生产者第"+i+"次提供");
                for (int j = 1;j<3;j++){
                    System.out.println("生产者装入"+i+"————"+j);
                    buffer.add("buffer：" + i + "--" + j);
                }
                System.out.println("生产者装满，等待与消费者交换...");
                try {


                    //生产者将buff填充满数据，与exchanger中的buffer交换
                    //在消费者获取数据前，生产者线程将被挂起
                    exchanger.exchange(buffer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    static class Consumer implements Runnable {
        private List<String> buffer;

        private final Exchanger<List<String>> exchanger;

        public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
            this.buffer = buffer;
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            for (int i = 1; i < 5; i++) {
                //调用exchange()与消费者进行数据交换
                try {
                    //消费者获取生产者存的buffer
                    buffer = exchanger.exchange(buffer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(name+"第" + i + "次提取");
                if(buffer.size() != 2){
                    System.out.println(name+" 没有获取到数据 " );
                    continue;
                }
                for (int j = 1; j < 3 ; j++) {
                    System.out.println(name+" : " + buffer.get(0));
                    buffer.remove(0);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        List<String> buffer1 = new ArrayList<String>();
        List<String> buffer2 = new ArrayList<String>();

        Exchanger<List<String>> exchanger = new Exchanger<List<String>>();

        Thread producerThread = new Thread(new Producer(buffer1,exchanger));
        Thread consumerThread = new Thread(new Consumer(buffer2,exchanger));
//        Thread consumerThread2 = new Thread(new Consumer(buffer2,exchanger));


        producerThread.start();
        consumerThread.setName("消费者一号");
//        consumerThread2.setName("消费者二号");
        Thread.sleep(5000L);
        consumerThread.start();
//        consumerThread2.start();
    }



}
