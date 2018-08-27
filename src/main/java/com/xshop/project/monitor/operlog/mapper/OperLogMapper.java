package com.xshop.project.monitor.operlog.mapper;

import com.xshop.common.mapper.BaseMapper;
import com.xshop.project.monitor.operlog.domain.OperLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 操作日志 数据层
 * 
 * @author xshop
 */
@Repository
public interface OperLogMapper extends BaseMapper<OperLog> {
    /**
     * 新增操作日志
     * 
     * @param operLog 操作日志对象
     */
    public void insertOperlog(OperLog operLog);

    /**
     * 查询系统操作日志集合
     * 
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    public List<OperLog> selectOperLogList(OperLog operLog);
    
    /**
     * 批量删除系统操作日志
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    public int batchDeleteOperLog(Long[] ids);
    
    /**
     * 查询操作日志详细
     * 
     * @param operId 操作ID
     * @return 操作日志对象
     */
    public OperLog selectOperLogById(Long operId);
}
