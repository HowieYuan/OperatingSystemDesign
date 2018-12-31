package com.howie.osdesign.philosopher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-12-31
 * @Time 15:41
 */
public class Main {
    public static void main(String[] args) {
        //五个哲学家只能有4个哲学家在竞争筷子，这样才不会出现死锁
        Semaphore sem = new Semaphore(4);
        //新建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            //提交5个哲学家线程，每个哲学家申请2根筷子
            executorService.submit(new Philosopher(i, new Chopstick(i),
                    new Chopstick((i + 1) % 5), sem));
        }
    }
}
