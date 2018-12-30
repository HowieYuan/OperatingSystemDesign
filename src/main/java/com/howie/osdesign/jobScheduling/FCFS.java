package com.howie.osdesign.jobScheduling;

import com.alibaba.fastjson.JSONArray;
import com.howie.osdesign.model.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 作业调度——先进先出
 * @Date 2018-12-28
 * @Time 20:39
 */
@RestController
@RequestMapping("jobScheduling")
public class FCFS {
    private final ResultMap resultMap;

    @Autowired
    public FCFS(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    /**
     * 先进先出 first-come first-served
     *
     * @param processList 进程列表
     */
    @RequestMapping(value = "/FCFS", method = RequestMethod.POST)
    public ResultMap fcfs(@RequestParam("processList") String processList) {
        List<Process> list = JSONArray.parseArray(processList,
                Process.class);
        List<JobScheduling> results = new ArrayList<>();
        list.sort(Comparator.comparingInt(Process::getArrivalTime));
        int startTime = 0;
        for (Process process : list) {
            if (process.getArrivalTime() > startTime) {
                startTime = process.getArrivalTime();
            }
            int finishTime = startTime + process.getServiceTime();
            int turnAroundTime = finishTime - process.getArrivalTime();
            results.add(new JobScheduling(process.getName(), startTime, finishTime,
                    turnAroundTime, ((float) turnAroundTime) / process.getServiceTime()));
            startTime = finishTime;
        }
        return resultMap.success().message(results);
    }

    /**
     * 短作业优先 short job first
     *
     * @param processList 进程列表
     */
    @RequestMapping(value = "/SJF", method = RequestMethod.POST)
    public ResultMap sjf(@RequestParam("processList") String processList) {
        //结果列表
        List<JobScheduling> results = scheduling(processList,
                Comparator.comparingInt(Process::getServiceTime));
        return resultMap.success().message(results);
    }

    /**
     * 优先级调度 priority-scheduling algorithm
     *
     * @param processList 进程列表
     */
    @RequestMapping(value = "/PSA", method = RequestMethod.POST)
    public ResultMap psa(@RequestParam("processList") String processList) {
        //结果列表
        List<JobScheduling> results = scheduling(processList,
                (Process p1, Process p2) -> p2.getPriority() - p1.getPriority());
        return resultMap.success().message(results);
    }

    /**
     * 高响应比优先调度
     *
     * @param processList 进程列表
     */
    @RequestMapping(value = "/HRRN", method = RequestMethod.POST)
    public ResultMap hrrn(@RequestParam("processList") String processList) {
        //结果列表
        List<JobScheduling> results = new ArrayList<>();
        //进程列表
        List<Process> list = JSONArray.parseArray(processList, Process.class);
        //对进程列表按到达时间排序
        list.sort(Comparator.comparingInt(Process::getArrivalTime));
        //使用一个list存放当前时间需要考虑的进程，以进行排序
        List<Process> currentList = new ArrayList<>();
        /*
        startTime: 到达时间小于该时间的进程可以进入堆
        currentTime: 当前进行到的时间
        index: 下一个要进入小顶堆的进程的索引
         */
        int i = list.size(), index = 0, startTime = 0, currentTime = 0;
        while (i-- > 0) {
            //当到达时间小于startTime，该进程可以进入堆
            while (index < list.size() && list.get(index).getArrivalTime() <= startTime) {
                currentList.add(list.get(index++));
            }
            int finalCurrentTime = currentTime;
            currentList.sort((p1, p2) ->
                    ((finalCurrentTime - p2.getArrivalTime() + p2.getServiceTime()) /
                            p2.getServiceTime()) - (finalCurrentTime - p1.getArrivalTime() +
                            p1.getServiceTime()) / p1.getServiceTime());
            //出队列，得到当前运行进程
            Process process = currentList.get(0);
            currentList.remove(0);
            int finishTime = currentTime + process.getServiceTime();
            int turnAroundTime = finishTime - process.getArrivalTime();
            results.add(new JobScheduling(process.getName(), currentTime, finishTime,
                    turnAroundTime,
                    ((float) turnAroundTime) / process.getServiceTime()));
            //更新 currentTime，startTime 数值
            currentTime = finishTime;
            startTime = process.getArrivalTime() + process.getServiceTime();
        }
        results.sort(Comparator.comparingInt(JobScheduling::getName));
        return resultMap.success().message(results);
    }

    /**
     * 获得调度后的序列
     *
     * @param processList 进程序列
     * @param comparator  比较器
     */
    private List<JobScheduling> scheduling(String processList, Comparator<Process> comparator) {
        //结果列表
        List<JobScheduling> results = new ArrayList<>();
        //进程列表
        List<Process> list = JSONArray.parseArray(processList, Process.class);
        //对进程列表按到达时间排序
        list.sort(Comparator.comparingInt(Process::getArrivalTime));
        //小顶堆优先队列，优先级为优先级
        PriorityQueue<Process> queue = new PriorityQueue<>(comparator);
        /*
        startTime: 到达时间小于该时间的进程可以进入堆
        currentTime: 当前进行到的时间
        index: 下一个要进入小顶堆的进程的索引
         */
        int i = list.size(), index = 0, startTime = 0, currentTime = 0;
        while (i-- > 0) {
            //当到达时间小于startTime，该进程可以进入堆
            while (index < list.size() && list.get(index).getArrivalTime() <= startTime) {
                queue.add(list.get(index++));
            }
            //出堆，得到当前运行进程
            Process process = queue.poll();
            int finishTime = currentTime + process.getServiceTime();
            int turnAroundTime = finishTime - process.getArrivalTime();
            results.add(new JobScheduling(process.getName(), currentTime, finishTime,
                    turnAroundTime,
                    ((float) turnAroundTime) / process.getServiceTime()));
            //更新 currentTime，startTime 数值
            currentTime = finishTime;
            startTime = process.getArrivalTime() + process.getServiceTime();
        }
        results.sort(Comparator.comparingInt(JobScheduling::getName));
        return results;
    }


//    public static void main(String[] args) {
//        Process process1 = new Process("A", 0, 2);
//        Process process2 = new Process("B", 0, 3);
//        Process process3 = new Process("C", 1, 5);
//        Process process4 = new Process("D", 2, 2);
//        Process process5 = new Process("E", 3, 1);
//        Process process6 = new Process("F", 4, 4);
//        Process process7 = new Process("G", 5, 3);
//        List<Process> list = Arrays.asList(process1, process3, process4, process2,
//                process6, process7, process5);
//        System.out.println(sjf(list));
//    }

    public static void main(String[] args) {
        int a = 1, b = 100;
        PriorityQueue<Integer> queue = new PriorityQueue<>((i1, i2) -> (i1 + a) - (i2 + b));
        queue.add(100);
        queue.add(0);
        System.out.println(queue);
//        a = 0;
//        b = 100;
        queue.add(0);
        System.out.println(queue);
    }
}
