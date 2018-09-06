package com.xshop.project.wechat.controller;

import com.xshop.project.cms.model.Shopcar;
import com.xshop.project.wechat.service.CarService;
import com.xshop.project.wechat.vo.AddShopCarVO;
import com.xshop.project.wechat.vo.CreateOrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wx/car")
@Api(value = "CarController", description = "购物车操作")
public class CarController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CarService carService;

    public CarController() {
    }

    @ApiOperation(
            value = "添加购物车",
            notes = "添加购物车",
            httpMethod = "POST"
    )
    @PostMapping({"/addCar"})
    public int addCar(@Validated @RequestBody AddShopCarVO addShopCarVO) {
        return this.carService.addCar(addShopCarVO);
    }

    @ApiOperation(
            value = "根据用户id查询购物车列表",
            notes = "根据用户id查询购物车列表",
            httpMethod = "GET"
    )
    @ApiImplicitParams({@ApiImplicitParam(
            name = "userId",
            paramType = "query",
            dataType = "Integer",
            value = "用户id"
    )})
    @GetMapping({"/getShopcarList/{userId}"})
    public List<Shopcar> getShopcarList(@PathVariable int userId) {
        return this.carService.getShopcarList(userId);
    }

    @ApiOperation(
            value = "根据用户id查询购物车列表",
            notes = "根据用户id查询购物车列表",
            httpMethod = "GET"
    )
    @ApiImplicitParams({@ApiImplicitParam(
            name = "userId",
            paramType = "query",
            dataType = "Integer",
            value = "用户id"
    )})
    @GetMapping({"/getOrderShopcarList"})
    public List<Shopcar> getOrderShopcarList(CreateOrderVO createOrderVO) {
        return this.carService.getOrderShopcarList(createOrderVO.getUserId(), createOrderVO.getCarIds());
    }

    @ApiOperation(
            value = "根据购物车id删除购物车",
            notes = "根据商品id删除购物车",
            httpMethod = "GET"
    )
    @ApiImplicitParams({@ApiImplicitParam(
            name = "carId",
            paramType = "query",
            dataType = "Integer",
            value = "购物车id"
    )})
    @GetMapping({"/delShopcarById/{carId}"})
    public int delShopcarById(@PathVariable int carId) {
        return this.carService.delShopcarById(carId);
    }

    @ApiOperation(
            value = "增加操作数量",
            notes = "操作数量",
            httpMethod = "GET"
    )
    @ApiImplicitParams({@ApiImplicitParam(
            name = "carId",
            paramType = "query",
            dataType = "Integer",
            value = "购物车id"
    ), @ApiImplicitParam(
            name = "num",
            paramType = "query",
            dataType = "Integer",
            value = "操作数量"
    )})
    @GetMapping({"/updateNum/{carId}/{num}"})
    public int updateNum(@PathVariable int carId, @PathVariable int num) {
        return this.carService.updateNum(carId, num);
    }

    @ApiOperation(
            value = "更新规格",
            notes = "更新规格",
            httpMethod = "GET"
    )
    @ApiImplicitParams({@ApiImplicitParam(
            name = "carId",
            paramType = "query",
            dataType = "Integer",
            value = "购物车id"
    ), @ApiImplicitParam(
            name = "specId",
            paramType = "query",
            dataType = "Integer",
            value = "规格id"
    )})
    @GetMapping({"/updateSpec/{carId}/{specId}"})
    public int updateSpec(@PathVariable int carId, @PathVariable int specId) {
        return this.carService.updateSpec(carId, specId);
    }
}