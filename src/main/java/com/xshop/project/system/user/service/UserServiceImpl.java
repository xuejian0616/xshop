package com.xshop.project.system.user.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xshop.common.constant.UserConstants;
import com.xshop.common.utils.StringUtils;
import com.xshop.common.utils.security.ShiroUtils;
import com.xshop.framework.shiro.service.PasswordService;
import com.xshop.project.system.role.domain.Role;
import com.xshop.project.system.role.mapper.RoleMapper;
import com.xshop.project.system.user.domain.User;
import com.xshop.project.system.user.domain.UserRole;
import com.xshop.project.system.user.mapper.UserMapper;
import com.xshop.project.system.user.mapper.UserRoleMapper;

/**
 * 用户 业务层处理
 * 
 * @author xshop
 */
@Service("userService")
public class UserServiceImpl implements IUserService
{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PasswordService passwordService;

    /**
     * 根据条件分页查询用户对象
     * 
     * @param user 用户信息
     * 
     * @return 用户信息集合信息
     */
    @Override
    public List<User> selectUserList(User user)
    {
        return userMapper.selectUserList(user);
    }

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public User selectUserByLoginName(String userName)
    {
        return userMapper.selectUserByLoginName(userName);
    }

    /**
     * 通过手机号码查询用户
     * 
     * @param phoneNumber 用户名
     * @return 用户对象信息
     */
    @Override
    public User selectUserByPhoneNumber(String phoneNumber)
    {
        return userMapper.selectUserByPhoneNumber(phoneNumber);
    }

    /**
     * 通过邮箱查询用户
     * 
     * @param email 邮箱
     * @return 用户对象信息
     */
    @Override
    public User selectUserByEmail(String email)
    {
        return userMapper.selectUserByEmail(email);
    }

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public User selectUserById(Long userId)
    {
        return userMapper.selectUserById(userId);
    }

    /**
     * 通过用户ID删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int deleteUserById(Long userId)
    {
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        return userMapper.deleteUserById(userId);
    }

    /**
     * 批量删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int batchDeleteUser(Long[] ids)
    {
        userRoleMapper.deleteUserRole(ids);
        return userMapper.batchDeleteUser(ids);
    }

    /**
     * 保存用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int saveUser(User user)
    {
        int count = 0;
        Long userId = user.getUserId();
        if (StringUtils.isNotNull(userId))
        {
            user.setUpdateBy(ShiroUtils.getLoginName());
            // 修改用户信息
            count = updateUser(user);
            // 删除用户与角色关联
            userRoleMapper.deleteUserRoleByUserId(userId);
            // 新增用户与角色管理
            insertUserRole(user);

        }
        else
        {
            user.randomSalt();
            user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
            user.setCreateBy(ShiroUtils.getLoginName());
            // 新增用户信息
            count = userMapper.insertUser(user);
            // 新增用户与角色管理
            insertUserRole(user);
        }
        return count;
    }

    /**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUser(User user)
    {
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户密码
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int resetUserPwd(User user)
    {
        user.randomSalt();
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        return updateUser(user);
    }

    /**
     * 新增用户角色信息
     * 
     * @param user 用户对象
     */
    public void insertUserRole(User user)
    {
        // 新增用户与角色管理
        List<UserRole> list = new ArrayList<UserRole>();
        for (Long roleId : user.getRoleIds())
        {
            UserRole ur = new UserRole();
            ur.setUserId(user.getUserId());
            ur.setRoleId(roleId);
            list.add(ur);
        }
        if (list.size() > 0)
        {
            userRoleMapper.batchUserRole(list);
        }
    }

    /**
     * 校验用户名称是否唯一
     * 
     * @param loginName 用户名
     * @return
     */
    @Override
    public String checkLoginNameUnique(String loginName)
    {
        int count = userMapper.checkLoginNameUnique(loginName);
        if (count > 0)
        {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_NAME_UNIQUE;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户
     * @return
     */
    @Override
    public String checkPhoneUnique(User user)
    {
        if (user.getUserId() == null)
        {
            user.setUserId(-1L);
        }
        Long userId = user.getUserId();
        User info = userMapper.checkPhoneUnique(user.getPhonenumber());
        if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getUserId())
                && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.USER_PHONE_NOT_UNIQUE;
        }
        return UserConstants.USER_PHONE_UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param user 用户
     * @return
     */
    @Override
    public String checkEmailUnique(User user)
    {
        if (user.getUserId() == null)
        {
            user.setUserId(-1L);
        }
        Long userId = user.getUserId();
        User info = userMapper.checkEmailUnique(user.getEmail());
        if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getUserId())
                && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.USER_EMAIL_NOT_UNIQUE;
        }
        return UserConstants.USER_EMAIL_UNIQUE;
    }

    /**
     * 查询用户所属角色组
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(Long userId)
    {
        List<Role> list = roleMapper.selectRolesByUserId(userId);
        StringBuffer idsStr = new StringBuffer();
        for (Role role : list)
        {
            idsStr.append(role.getRoleName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString()))
        {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

}
