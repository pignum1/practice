package com.cloud.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author WXY
 * @ClassName MultithreadScheduleTask
 * @Description 多线程定时任务
 * @Date 2019/8/19 23:33
 * @Version 1.0
 **/
@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
public class MultiThreadScheduleTask {
//    Logger logger = LoggerFactory.getLogger( MultiThreadScheduleTask.class );
//
//    @Async("taskExecutor")
//    @Scheduled(fixedDelay = 1000)  //间隔1秒
//    public void first() throws InterruptedException {
//        System.out.println("第一个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
//        System.out.println();
//        Thread.sleep(1000 * 10);
//    }
//
//    @Async("taskExecutor")
//    @Scheduled(fixedDelay = 2000)
//    public void second() {
//        System.out.println("第二个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
//        System.out.println();
//    }
}