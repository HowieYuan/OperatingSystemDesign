package com.howie.osdesign.jobScheduling;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 算法运行结果
 * @Date 2018-12-28
 * @Time 20:45
 */
public class JobScheduling {
    /**
     * 进程名
     */
    private String name;
    /**
     * 开始时间
     */
    private int startTime;
    /**
     * 完成时间
     */
    private int finishTime;
    /**
     * 周转时间
     */
    private int turnAroundTime;
    /**
     * 带权周转时间
     */
    private float weightTurnAroundTime;

    public JobScheduling(String name, int startTime, int finishTime, int turnAroundTime, float weightTurnAroundTime) {
        this.name = name;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.turnAroundTime = turnAroundTime;
        this.weightTurnAroundTime = weightTurnAroundTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public float getWeightTurnAroundTime() {
        return weightTurnAroundTime;
    }

    public void setWeightTurnAroundTime(float weightTurnAroundTime) {
        this.weightTurnAroundTime = weightTurnAroundTime;
    }
}
