package cn.anx.controller.shopadmin;

import cn.anx.dto.ShopExecution;
import cn.anx.enums.ShopStateEnum;
import cn.anx.pojo.Area;
import cn.anx.pojo.PersonInfo;
import cn.anx.pojo.Shop;
import cn.anx.pojo.ShopCategory;
import cn.anx.service.AreaService;
import cn.anx.service.ShopCategoryService;
import cn.anx.service.ShopService;
import cn.anx.util.HttpServletRequestUtil;
import cn.anx.util.ImageUtil;
import cn.anx.util.PathUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {

    @Autowired
    ShopService shopService;
    @Autowired
    ShopCategoryService shopCategoryService;
    @Autowired
    AreaService areaService;

    @RequestMapping(value = "getshopinitinfo",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getShopInitInfo(){
        Map<String,Object> modelMap = new HashMap<>();
        List<ShopCategory> shopCategoryList;
        List<Area> areaList;
        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.queryArea();
            modelMap.put("shopCategoryList",shopCategoryList);
            modelMap.put("areaList",areaList);
            modelMap.put("success",true);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }
        return modelMap;
    }

    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        //get and transform parameters ,including shopInfo and imgInfo
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "The picture uploaded shouldn't be null");
            return null;
        }
        //register shop
        //return result
        if (shop != null && shopImg != null) {
            PersonInfo owner = new PersonInfo();
            //Session TODO
            owner.setUserId(1L);
            shop.setOwner(owner);
            ShopExecution se = null;
            try {
                se = shopService.addShop(shop, shopImg.getInputStream(),shopImg.getOriginalFilename());
            } catch (IOException e) {
                modelMap.put("success",false);
                modelMap.put("errMsg",se.getStateInfo());
            }
            if (se.getState() == ShopStateEnum.CHECK.getState()){
                modelMap.put("success",true);
            }else {
                modelMap.put("success",false);
                modelMap.put("errMsg",se.getStateInfo());
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "Please input shopImg");
            return modelMap;
        }
    }

/*    private static void inputStreamToFile(InputStream ins, File file) {
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = ins.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            throw new RuntimeException("call inputStreamToFile failed:" + e.getMessage());
        } finally {
            try {
                if (os != null)
                    os.close();
                if (ins != null)
                    ins.close();
            } catch (IOException e) {
                throw new RuntimeException("io close exception:" + e.getMessage());
            }
        }
    }*/
}
