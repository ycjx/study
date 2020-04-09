package com.yxj;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author:ycjx
 * @descriptio
 * @create:2019-07-06 15:38
 */
@Slf4j
public class KogMawListener {


    @TransactionalEventListener(classes = KogMawEvent.class)
    public void callBack(KogMawEvent kogMawEvent) {
        log.info("收到消息");
        log.info(kogMawEvent.toString());
    }

}
