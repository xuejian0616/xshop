package com.xshop.project.wechat.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: xshop
 *
 * @Date：Created on 2018/9/5 18:30.
 */
@Data
public class CarToOrderVO {
    @ApiModelProperty("用户id")
    private int userId;
    @ApiModelProperty("购物车id")
    private List<Integer> carIds;
}