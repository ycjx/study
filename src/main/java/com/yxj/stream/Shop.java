package com.yxj.stream;

public class Shop {

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //距离 单位米
    private int distance;

    private String name;


    public Shop(String name,int distance){
        this.name = name;
        this.distance = distance;
    }



}
