package com.howie.osdesign.jobScheduling;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 进程
 * @Date 2018-12-28
 * @Time 20:41
 */
public class Process {
    /**
     * 进程名
     */
    private char name;
    /**
     * 到达时间
     */
    private int arrivalTime;
    /**
     * 要求服务时间
     */
    private int serviceTime;
    /**
     * 优先级
     */
    private int priority;

    public Process() {
    }

    public Process(char name, int arrivalTime, int serviceTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public Process(char name, int arrivalTime, int serviceTime, int priority) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.priority = priority;
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Process{" +
                "name=" + name +
                ", arrivalTime=" + arrivalTime +
                ", serviceTime=" + serviceTime +
                ", priority=" + priority +
                '}';
    }
}
