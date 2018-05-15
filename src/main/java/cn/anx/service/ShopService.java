package cn.anx.service;

import cn.anx.dto.ShopExecution;
import cn.anx.pojo.Shop;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName);
}
