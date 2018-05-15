package cn.anx.test;

import cn.anx.dto.ShopExecution;
import cn.anx.enums.ShopStateEnum;
import cn.anx.pojo.Area;
import cn.anx.pojo.PersonInfo;
import cn.anx.pojo.Shop;
import cn.anx.pojo.ShopCategory;
import cn.anx.service.ShopService;
import cn.anx.util.PathUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {cn.anx.config.Dao.class,cn.anx.config.Rootconfig.class})
public class ServiceTest {

    @Autowired
    ShopService shopService;

    @Test
    public void shopTest() throws FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("testShop3");
        shop.setShopDesc("test3");
        shop.setShopAddr("test3");
        shop.setPhone("test3");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("checking");
        File shopImg = new File(PathUtil.getImgBasePath()+"miku.jpg");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.addShop(shop, is,shopImg.getName());
        System.out.println(shopExecution.getState());
    }
}
