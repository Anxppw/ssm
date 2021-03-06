package cn.anx.service.impl;


import cn.anx.mapper.ShopCategoryMapper;
import cn.anx.pojo.ShopCategory;
import cn.anx.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService{
    @Autowired
    private ShopCategoryMapper shopCategoryMapper;

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        return shopCategoryMapper.queryShopCategory(shopCategoryCondition);
    }
}
