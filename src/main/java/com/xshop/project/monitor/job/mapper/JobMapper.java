package com.xshop.project.monitor.job.mapper;

import com.xshop.common.mapper.BaseMapper;
import com.xshop.project.monitor.job.domain.Job;
import com.xshop.project.system.menu.domain.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 调度任务信息 数据层
 * 
 * @author xshop
 */
@Repository
public interface JobMapper extends BaseMapper<Job> {
    /**
     * 查询调度任务日志集合
     * 
     * @param job 调度信息
     * @return 操作日志集合
     */
    public List<Job> selectJobList(Job job);

    /**
     * 查询所有调度任务
     * 
     * @return 调度任务列表
     */
    public List<Job> selectJobAll();

    /**
     * 通过调度ID查询调度任务信息
     * 
     * @param jobId 调度ID
     * @return 角色对象信息
     */
    public Job selectJobById(Long jobId);

    /**
     * 通过调度ID删除调度任务信息
     * 
     * @param jobId 调度ID
     * @return 结果
     */
    public int deleteJobById(Job job);

    /**
     * 批量删除调度任务信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteJob(Long[] ids);

    /**
     * 修改调度任务信息
     * 
     * @param job 调度任务信息
     * @return 结果
     */
    public int updateJob(Job job);

    /**
     * 新增调度任务信息
     * 
     * @param job 调度任务信息
     * @return 结果
     */
    public int insertJob(Job job);

}
