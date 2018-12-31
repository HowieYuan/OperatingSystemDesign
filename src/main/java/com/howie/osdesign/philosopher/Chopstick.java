package com.howie.osdesign.philosopher;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 筷子
 * @Date 2018-12-31
 * @Time 15:40
 */
public class Chopstick {
    private int id;
    /**
     * 为每一双筷子设置一把锁
     */
    private ReentrantLock lock = new ReentrantLock();

    public Chopstick(int id) {
        this.id = id;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public int getId() {
        return id;
    }
}
