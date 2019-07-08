package com.yxj.thread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class ThreadExecutor {


    public static void main(String[] args) {

        List<FutureTask<Integer>> taskList = new ArrayList<FutureTask<Integer>>();


        ExecutorService excutor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {

            FutureTask<Integer> task = new FutureTask<Integer>(new comTask(5,"线程"+i));
            taskList.add(task);
            excutor.submit(task);
         }

         Iterator<FutureTask<Integer>> iterator= taskList.iterator();
        while(iterator.hasNext()){
            try {
                FutureTask<Integer> f = iterator.next();
                System.out.println(f.get());
                excutor.shutdown();
                System.out.println("线程"+ f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }




    }

    public static class comTask implements Callable<Integer>{

        private  Integer result = 0;

        private String taskName = "";

        public comTask(Integer result,String taskName){
            this.result = result;
            this.taskName = taskName;
            System.out.println("生成子线程:"+taskName);
        }

        public String getTaskName() {
            return taskName;
        }

        @Override
        public Integer call() throws Exception {
            for (int i=0;i<1000;i++){
                result++;
            }
            Thread.sleep(6000);
            System.out.println("生成子线程:"+taskName);
            return result;
        }
    }


}
