package com.xshop.project.system.user.controller;

import com.xshop.framework.config.XshopConfig;
import com.xshop.framework.web.controller.BaseController;
import com.xshop.project.system.menu.domain.Menu;
import com.xshop.project.system.menu.service.IMenuService;
import com.xshop.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 首页 业务处理
 * 
 * @author xshop
 */
@Controller
public class IndexController extends BaseController
{
    @Autowired
    private IMenuService menuService;

    @Autowired
    private XshopConfig xshopConfig;

    // 系统首页
    @GetMapping("/index")
    public String index(Model model)
    {
        // 取身份信息
        User user = getUser();
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUserId(user.getUserId());
        model.addAttribute("menus", menus);
        model.addAttribute("user", user);
        model.addAttribute("copyrightYear", xshopConfig.getCopyrightYear());
        return "index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(Model model)
    {
        model.addAttribute("version", xshopConfig.getVersion());
        return "main";
    }

}
