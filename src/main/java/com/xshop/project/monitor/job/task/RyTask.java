package com.xshop.project.monitor.job.task;

import org.springframework.stereotype.Component;

/**
 * 定时任务调度测试
 * 
 * @author xshop
 */
@Component("ryTask")
public class RyTask
{

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }

}
