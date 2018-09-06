package com.xshop.project.wechat.service.impl;

import com.xshop.project.cms.mapper.CarMapper;
import com.xshop.project.cms.mapper.GoodsSpecMapper;
import com.xshop.project.cms.model.GoodsSpec;
import com.xshop.project.cms.model.Shopcar;
import com.xshop.project.wechat.service.CarService;
import com.xshop.project.wechat.vo.AddShopCarVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CarServiceImpl implements CarService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CarMapper carMapper;
    @Autowired
    GoodsSpecMapper goodsSpecMapper;

    public CarServiceImpl() {
    }

    public int addCar(AddShopCarVO addShopCarVO) {
        Shopcar shopcar = new Shopcar();
        int price = BigDecimal.valueOf(addShopCarVO.getPrice()).multiply(new BigDecimal("100")).intValue();
        int oldPrice = BigDecimal.valueOf(addShopCarVO.getPrice()).multiply(new BigDecimal("100")).intValue();
        int lowerPrice = BigDecimal.valueOf(addShopCarVO.getPrice()).multiply(new BigDecimal("100")).intValue();
        if (price < lowerPrice) {
            return 0;
        } else {
            shopcar.setUserId(addShopCarVO.getUserId());
            shopcar.setGoodsId(addShopCarVO.getGoodsId());
            shopcar.setSpecId(addShopCarVO.getSpecId());
            shopcar = (Shopcar)this.carMapper.selectOne(shopcar);
            int type = addShopCarVO.getType();
            int num = addShopCarVO.getNum();
            Date date = new Date();
            if (Objects.isNull(shopcar)) {
                if (type != 1 && type != 2) {
                    shopcar = new Shopcar();
                    shopcar.setUserId(addShopCarVO.getUserId());
                    shopcar.setGoodsId(addShopCarVO.getGoodsId());
                    shopcar.setSpecId(addShopCarVO.getSpecId());
                    shopcar.setGoodsName(addShopCarVO.getGoodsName());
                    shopcar.setGoodsSpec(addShopCarVO.getSpec());
                    shopcar.setImg(addShopCarVO.getImg());
                    shopcar.setNum(num);
                    shopcar.setPrice(price);
                    shopcar.setOldPrice(oldPrice);
                    shopcar.setCreateTime(date);
                    shopcar.setUpdateTime(date);
                    return this.carMapper.insert(shopcar);
                } else {
                    return 0;
                }
            } else {
                if (type == 0) {
                    shopcar.setNum(shopcar.getNum() + num);
                    shopcar.setPrice(price);
                    shopcar.setImg(addShopCarVO.getImg());
                    shopcar.setUpdateTime(date);
                } else if (type == 1) {
                    num = shopcar.getNum() - num;
                    if (num <= 0) {
                        return this.carMapper.deleteByPrimaryKey(shopcar);
                    }

                    shopcar.setNum(num);
                    shopcar.setPrice(price);
                    shopcar.setImg(addShopCarVO.getImg());
                    shopcar.setUpdateTime(date);
                } else {
                    if (type == 2) {
                        return this.carMapper.delete(shopcar);
                    }

                    if (type == 3) {
                        shopcar.setNum(num);
                        shopcar.setPrice(price);
                        shopcar.setImg(addShopCarVO.getImg());
                        shopcar.setUpdateTime(date);
                    }
                }

                this.logger.info("shopcar: " + shopcar);
                return this.carMapper.updateByPrimaryKeySelective(shopcar);
            }
        }
    }

    public List<Shopcar> getShopcarList(int userId) {
        Shopcar shopcar = new Shopcar();
        shopcar.setUserId(userId);
        return this.carMapper.select(shopcar);
    }

    public List<Shopcar> getOrderShopcarList(int userId, List<Integer> carIds) {
        return this.carMapper.getOrderShopcarList(userId, carIds);
    }

    public int delShopcarById(int carId) {
        Shopcar shopcar = new Shopcar();
        shopcar.setId(carId);
        return this.carMapper.deleteByPrimaryKey(shopcar);
    }

    public int updateNum(int carId, int num) {
        Shopcar shopcar = new Shopcar();
        shopcar.setId(carId);
        shopcar = (Shopcar)this.carMapper.selectByPrimaryKey(shopcar);
        if (!Objects.isNull(shopcar)) {
            num += shopcar.getNum();
            if (num <= 0) {
                return this.carMapper.deleteByPrimaryKey(shopcar);
            } else {
                shopcar.setNum(num);
                return this.carMapper.updateByPrimaryKeySelective(shopcar);
            }
        } else {
            return 0;
        }
    }

    public int updateSpec(int carId, int specId) {
        Shopcar shopcar = new Shopcar();
        shopcar.setId(carId);
        Shopcar oldshopcar = (Shopcar)this.carMapper.selectByPrimaryKey(shopcar);
        this.carMapper.deleteByPrimaryKey(oldshopcar);
        shopcar.setId((Integer)null);
        shopcar.setGoodsId(oldshopcar.getGoodsId());
        shopcar.setSpecId(specId);
        shopcar = (Shopcar)this.carMapper.selectOne(shopcar);
        Date date = new Date();
        if (Objects.isNull(shopcar)) {
            GoodsSpec goodsSpec = new GoodsSpec();
            goodsSpec.setId(specId);
            goodsSpec = (GoodsSpec)this.goodsSpecMapper.selectByPrimaryKey(goodsSpec);
            oldshopcar.setSpecId(specId);
            oldshopcar.setImg(goodsSpec.getImg());
            oldshopcar.setGoodsSpec("\"" + goodsSpec.getNorm() + "\" \"" + goodsSpec.getSize() + "\"");
            oldshopcar.setUpdateTime(date);
            return this.carMapper.insert(oldshopcar);
        } else {
            shopcar.setNum(shopcar.getNum() + oldshopcar.getNum());
            shopcar.setUpdateTime(date);
            return this.carMapper.updateByPrimaryKeySelective(shopcar);
        }
    }
}