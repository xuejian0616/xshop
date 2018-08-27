package com.xshop.project.system.user.controller;

import com.xshop.framework.aspectj.lang.annotation.Log;
import com.xshop.framework.web.controller.BaseController;
import com.xshop.framework.web.domain.Message;
import com.xshop.framework.web.page.TableDataInfo;
import com.xshop.project.system.role.domain.Role;
import com.xshop.project.system.role.service.IRoleService;
import com.xshop.project.system.user.domain.User;
import com.xshop.project.system.user.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息
 * 
 * @author xshop
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {

    private String prefix = "system/user";

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;


    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    @RequiresPermissions("system:user:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(User user)
    {
        startPage();
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 修改用户
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "系统管理", action = "用户管理-修改用户")
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, Model model)
    {
        User user = userService.selectUserById(userId);
        List<Role> roles = roleService.selectRolesByUserId(userId);
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return prefix + "/edit";
    }

    /**
     * 新增用户
     */
    @RequiresPermissions("system:user:add")
    @Log(title = "系统管理", action = "用户管理-新增用户")
    @GetMapping("/add")
    public String add(Model model)
    {
        List<Role> roles = roleService.selectRoleAll();
        model.addAttribute("roles", roles);
        return prefix + "/add";
    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "系统管理", action = "用户管理-重置密码")
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, Model model)
    {
        User user = userService.selectUserById(userId);
        model.addAttribute("user", user);
        return prefix + "/resetPwd";
    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "系统管理", action = "用户管理-重置密码")
    @PostMapping("/resetPwd")
    @ResponseBody
    public Message resetPwd(User user)
    {
        int rows = userService.resetUserPwd(user);
        if (rows > 0)
        {
            return Message.success();
        }
        return Message.error();
    }

    @RequiresPermissions("system:user:remove")
    @Log(title = "系统管理", action = "用户管理-删除用户")
    @RequestMapping("/remove/{userId}")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Message remove(@PathVariable("userId") Long userId)
    {
        User user = userService.selectUserById(userId);
        if (user == null)
        {
            return Message.error("用户不存在");
        }
        if (userService.deleteUserById(userId) > 0)
        {
            return Message.success();
        }
        return Message.error();
    }

    @RequiresPermissions("system:user:batchRemove")
    @Log(title = "系统管理", action = "用户管理-批量删除")
    @PostMapping("/batchRemove")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Message batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = userService.batchDeleteUser(ids);
        if (rows > 0)
        {
            return Message.success();
        }
        return Message.error();
    }

    /**
     * 保存用户
     */
    @RequiresPermissions("system:user:save")
    @Log(title = "系统管理", action = "用户管理-保存用户")
    @PostMapping("/save")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Message save(User user)
    {
        if (userService.saveUser(user) > 0)
        {
            return Message.success();
        }
        return Message.error();
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(User user)
    {
        String uniqueFlag = "0";
        if (user != null)
        {
            uniqueFlag = userService.checkLoginNameUnique(user.getLoginName());
        }
        return uniqueFlag;
    }


    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(User user)
    {
        String uniqueFlag = "0";
        if (user != null)
        {
            uniqueFlag = userService.checkPhoneUnique(user);
        }
        return uniqueFlag;
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(User user)
    {
        String uniqueFlag = "0";
        if (user != null)
        {
            uniqueFlag = userService.checkEmailUnique(user);
        }
        return uniqueFlag;
    }
}