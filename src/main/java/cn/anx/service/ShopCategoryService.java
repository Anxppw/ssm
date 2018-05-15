package cn.anx.service;

import cn.anx.pojo.ShopCategory;

import java.util.List;

public interface ShopCategoryService {
    List<ShopCategory>  getShopCategoryList(ShopCategory shopCategoryCondition);
}
