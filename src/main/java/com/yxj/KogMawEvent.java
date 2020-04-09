package com.yxj;

import lombok.Data;

/**
 * @author:ycjx
 * @descriptio
 * @create:2019-07-06 15:28
 */
@Data
public class KogMawEvent {

    private String id;

    private String value;

    public KogMawEvent(String id, String value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        return "YcjxEvent{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
