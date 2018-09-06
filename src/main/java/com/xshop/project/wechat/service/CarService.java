package com.xshop.project.wechat.service;

import com.xshop.project.cms.model.Shopcar;
import com.xshop.project.wechat.vo.AddShopCarVO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @program: xshop
 * @Date: 2018/7/1 18:46
 * @Author: Mr.Deng
 * @Description:
 */
public interface CarService {

    int addCar(AddShopCarVO addShopCarVO);

    List<Shopcar> getShopcarList(int userId);

    List<Shopcar> getOrderShopcarList(int userId, List<Integer> carIds);

    int delShopcarById(int carId);

    int updateNum(int carId, int num);

    int updateSpec(@PathVariable int carId, @PathVariable int specId);
}
