package com.howie.osdesign.jobScheduling;

import com.alibaba.fastjson.JSONArray;
import com.howie.osdesign.model.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    @RequestMapping(value = "/FCFS", method = RequestMethod.POST)
    public ResultMap fcfs(@RequestParam("processList") String processList) {
        System.out.println(processList);
        List<Process> list = JSONArray.parseArray(processList,
                Process.class);
        System.out.println(list);
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

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ResultMap test(Process results) {
        System.out.println(results);
//        Process entity = JSON.parseObject(results, Process.class);
        return resultMap.success().message(results.getArrivalTime());
    }
}
