package com.xshop.project.system.user.domain;

import com.xshop.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

import java.util.Date;

/**
 * 用户对象 sys_user
 * 
 * @author xshop
 */
@Data
public class User extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /** 用户ID */
    private Long userId;
    /** 登录名 */
    private String loginName;
    /** 用户名称 */
    private String userName;
    /** 用户邮箱 */
    private String email;
    /** 手机号码 */
    private String phonenumber;
    /** 用户性别 */
    private String sex;
    /** 用户头像 */
    private String avatar;
    /** 密码 */
    private String password;
    /** 盐加密 */
    private String salt;
    /** 类型:Y默认用户,N非默认用户 */
    private String userType;
    /** 帐号状态:0正常,1禁用 */
    private int status;
    /** 拒绝登录描述 */
    private String refuseDes;
    /** 最后登陆IP */
    private String loginIp;
    /** 最后登陆时间 */
    private Date loginDate;
    /** 角色id组 */
    private Long[] roleIds;
    /** 角色组 */
    private String roles;

    /**
     * 生成随机盐
     */
    public void randomSalt()
    {
        // 一个Byte占两个字节，此处生成的3字节，字符串长度为6
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        String hex = secureRandom.nextBytes(3).toHex();
        setSalt(hex);
    }

}
