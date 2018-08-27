package com.xshop.project.monitor.logininfor.mapper;

import com.xshop.common.mapper.BaseMapper;
import com.xshop.project.monitor.logininfor.domain.Logininfor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统访问日志情况信息 数据层
 * 
 * @author xshop
 */
@Repository
public interface LogininforMapper extends BaseMapper<Logininfor> {
    /**
     * 新增系统登录日志
     * 
     * @param logininfor 访问日志对象
     */
    public void insertLogininfor(Logininfor logininfor);

    /**
     * 查询系统登录日志集合
     * 
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    public List<Logininfor> selectLogininforList(Logininfor logininfor);

    /**
     * 批量删除系统登录日志
     * 
     * @param ids 需要删除的数据
     * @return
     */
    public int batchDeleteLogininfor(Long[] ids);
}
