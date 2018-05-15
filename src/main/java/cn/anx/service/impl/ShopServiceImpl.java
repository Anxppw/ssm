package cn.anx.service.impl;

import cn.anx.dto.ShopExecution;
import cn.anx.enums.ShopStateEnum;
import cn.anx.exceptions.ShopOperationException;
import cn.anx.mapper.ShopMapper;
import cn.anx.pojo.Shop;
import cn.anx.service.ShopService;
import cn.anx.util.ImageUtil;
import cn.anx.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopMapper shopMapper;

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName) {
        //judge null
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOPID);
        }
        try {
            //initialize shopInfo
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //add shopInfo
            int effectedNum = shopMapper.insertShop(shop);
            if(effectedNum <=0){
                //only the transaction will rollback when the thread throws ShopOperationException
                throw new ShopOperationException("creation failed");
            }else {
                if (shopImgInputStream != null){
                    //store image
                    try {
                        addShopImg(shop,shopImgInputStream,fileName);
                    }catch (Exception e){
                        throw new ShopOperationException("addShop error:"+e.getMessage());
                    }
                    //update the address of shop image
                    effectedNum = shopMapper.updateShop(shop);
                    if (effectedNum <= 0){
                        throw new ShopOperationException("updating image address failed");
                    }
                }
            }
        }catch (Exception e){
            throw new ShopOperationException("addShop error:"+e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    private void addShopImg(Shop shop, InputStream shopImgInputStream,String fileName) {
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream,fileName,dest);
        shop.setShopImg(shopImgAddr);
    }
}
