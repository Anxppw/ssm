package cn.anx.test;


import cn.anx.mapper.AreaMapper;
import cn.anx.mapper.ShopCategoryMapper;
import cn.anx.mapper.ShopMapper;
import cn.anx.pojo.Area;
import cn.anx.pojo.PersonInfo;
import cn.anx.pojo.Shop;
import cn.anx.pojo.ShopCategory;
import cn.anx.service.AreaService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {cn.anx.config.Dao.class,cn.anx.config.Rootconfig.class})
public class MybatisTest {

    @Autowired
    ShopMapper shopMapper;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private AreaService areaService;
    @Autowired
    ShopCategoryMapper shopCategoryMapper;

    @Test
    public void test1(){
        List<Area> areaList= areaMapper.queryArea();
        assertEquals(2,areaList.size());
    }

    @Test
    public void test2(){
        List<Area> areaList = areaService.queryArea();
        System.out.println(areaList);
    }

    @Test
    @Ignore
    public void test3(){
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
        shop.setShopName("testShop");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("checking");
        int effectedNum = shopMapper.insertShop(shop);
        System.out.println(effectedNum);
    }

    @Test
    public void test4(){
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopDesc("test desc");
        shop.setShopAddr("test address");
        shop.setLastEditTime(new Date());
        int effectedNum = shopMapper.updateShop(shop);
        System.out.println(effectedNum);
    }

    @Test
    public void test5(){
        List<ShopCategory> shopCategoryList = shopCategoryMapper.queryShopCategory(new ShopCategory());
        System.out.println(shopCategoryList);
    }
}
