package com.howie.osdesign.philosopher;

import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 哲学家
 * @Date 2018-12-31
 * @Time 15:37
 */
public class Philosopher implements Runnable {
    /**
     * 第i个哲学家左侧的筷子对应的筷子id号为i
     */
    private int id;
    private Chopstick leftChop;
    private Chopstick rightChop;
    private Semaphore sem;

    public Philosopher() {
    }

    public Philosopher(int id, Chopstick leftChop, Chopstick rightChop, Semaphore sem) {
        this.id = id;
        this.leftChop = leftChop;
        this.rightChop = rightChop;
        this.sem = sem;
    }

    /**
     * 哲学家从饥饿到吃完饭到继续思考的过程
     */
    @Override
    public void run() {
        while (true) {
            try {
                Double d = Math.random() * 10000;
                //生成随机数表示哲学家思考的时间
                Thread.sleep(d.intValue());
                //线程获取许可
                sem.acquire();
                System.out.println("哲学家" + this.id + "感到饥饿，准备拿起筷子");
                //获取左筷子锁
                leftChop.getLock().lock();
                System.out.println("哲学家" + this.id + "拿起了左边的筷子(筷子号为" + leftChop.getId() + ")...");
                //获取右筷子锁
                rightChop.getLock().lock();
                System.out.println("哲学家" + this.id + "拿起了右边的筷子(筷子号为" + rightChop.getId() + ")...");
                System.out.println("哲学家" + this.id + "正在吃饭");
                d = Math.random() * 10000;
                //生成随机数表示哲学家吃饭的时间
                Thread.sleep(d.intValue());
                System.out.println("哲学家" + this.id + "吃完饭，继续思考");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //释放许可
                sem.release();
                //释放左筷子锁
                leftChop.getLock().unlock();
                //释放右筷子锁
                rightChop.getLock().unlock();
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Chopstick getLeftChop() {
        return leftChop;
    }

    public void setLeftChop(Chopstick leftChop) {
        this.leftChop = leftChop;
    }

    public Chopstick getRightChop() {
        return rightChop;
    }

    public void setRightChop(Chopstick rightChop) {
        this.rightChop = rightChop;
    }

    public Semaphore getSem() {
        return sem;
    }

    public void setSem(Semaphore sem) {
        this.sem = sem;
    }
}
