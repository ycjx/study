package com.yxj.arithmetic;

import java.util.*;
import java.util.stream.Collectors;

public class Demo4 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int cityCount = in.nextInt();
        int actionCount = in.nextInt();
        in.nextLine();
//        Scanner in2 = new Scanner(System.in);
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<cityCount-1;i++){
            list.add(in.nextInt());
        }
        System.out.println(real(cityCount,actionCount,list));


    }

    private static int real(int n,int l ,List<Integer> parten){

        int max = 0;
        int[] arr = new int[n];
        arr[0] = 0;

        for(int i=1;i<parten.size()+1;i++){
            arr[i] = arr[parten.get(i-1)]+1;
            if(arr[i]>max){
                max = arr[i];
            }

        }

        if(l<max){
            return l+1;
        }else{
            return Math.min(n,max+1+(l-max)/2);
        }

    }

//    private static  void getDistance(List<Vertex> vertices ,Vertex v,HashMap<Integer,List<Integer>> map){
//        List<Integer> list = map.get(v.name);
//        for(int i=0;i<list.size();i++){
//            Vertex vv = vertices.get(list.get(i));
//            if(vv.path>v.path+1){
//                vv.path = v.path+1;
//            }
//        }
//
//    }
//
//    private static void poll(Queue<Vertex> queue){
//        queue.poll();
//
//    }
//
//    public static class Vertex implements Comparable<Vertex>{
//
//        /**
//         * 节点名称(A,B,C,D)
//         */
//        public int name;
//
//        /**
//         * 最短路径长度
//         */
//        public int path;
//
//        /**
//         * 节点是否已经出列(是否已经处理完毕)
//         */
//        private boolean isMarked;
//
//        public Vertex(int name){
//            this.name = name;
//            this.path = Integer.MAX_VALUE; //初始设置为无穷大
//            this.setMarked(false);
//        }
//
//        public Vertex(int name, int path){
//            this.name = name;
//            this.path = path;
//            this.setMarked(false);
//        }
//
//        public void setMarked(boolean bool){
//            this.isMarked = bool;
//        }
//
//        @Override
//        public int compareTo(Vertex o) {
//            return o.path > path?-1:1;
//        }
//    }


}
