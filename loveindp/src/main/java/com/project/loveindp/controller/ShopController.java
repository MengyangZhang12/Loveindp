package com.project.loveindp.controller;


import com.project.loveindp.common.BusinessException;
import com.project.loveindp.common.CommonRes;
import com.project.loveindp.common.EmBusinessError;
import com.project.loveindp.model.CategoryModel;
import com.project.loveindp.model.ShopModel;
import com.project.loveindp.service.CategoryService;
import com.project.loveindp.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller("/shop")
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private CategoryService categoryService;

    // 推荐服务
    @RequestMapping("/recommend")
    @ResponseBody
    public CommonRes recommend(@RequestParam(name = "longitude")BigDecimal longitude,
                               @RequestParam(name = "latitude")BigDecimal latitude) throws BusinessException {
        if (longitude == null || latitude == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        List<ShopModel> shopModelList = shopService.recommend(longitude,latitude);
        return CommonRes.create(shopModelList);
    }

    // 搜索服务
    @RequestMapping("/search")
    @ResponseBody
    public CommonRes search(@RequestParam(name="longitude")BigDecimal longitude,
                            @RequestParam(name="latitude")BigDecimal latitude,
                            @RequestParam(name="keyword")String keyword,
                            @RequestParam(name="orderby",required = false)Integer orderby,
                            @RequestParam(name="categoryId",required = false)Integer categoryId,
                            @RequestParam(name="tags",required = false)String tags
    ) throws BusinessException, IOException {
        if(StringUtils.isEmpty(keyword) || longitude == null || latitude == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        // search v2.0
        List<ShopModel> shopModelList = (List<ShopModel>)shopService.searchByEsHighLevel(longitude,latitude,keyword, orderby, categoryId,tags).get("shop");
        //search by es only by name
//        List<ShopModel> shopModelList = (List<ShopModel>)shopService.searchES(longitude,latitude,keyword, orderby, categoryId,tags).get("shop");
        // search v1.0
        //List<ShopModel> shopModelList = shopService.search(longitude,latitude,keyword, orderby, categoryId,tags);
        List<CategoryModel> categoryModelList = categoryService.selectAll();
        List<Map<String,Object>> tagsAggregation = shopService.searchGroupByTags(keyword,categoryId,tags);
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("shop",shopModelList);
        resMap.put("category",categoryModelList);
        resMap.put("tags",tagsAggregation);
        return CommonRes.create(resMap);

    }

}
