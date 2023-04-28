package com.project.loveindp.service;


import com.project.loveindp.common.BusinessException;
import com.project.loveindp.model.ShopModel;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface ShopService {

    ShopModel create(ShopModel shopModel) throws BusinessException;

    ShopModel get(Integer id);

    List<ShopModel> selectAll();

    Integer countAllShop();

    // 推荐
    List<ShopModel> recommend(BigDecimal longitude,BigDecimal latitude);

    // 搜索

    List<ShopModel> search(BigDecimal longitude,BigDecimal latitude,String keyword,
                           Integer orderby,Integer categoryId,String tags);

    Map<String, Object> searchES(BigDecimal longitude,BigDecimal latitude,String keyword,
                           Integer orderby,Integer categoryId,String tags) throws IOException;

    Map<String, Object> searchByEsHighLevel(BigDecimal longitude, BigDecimal latitude,
                                            String keyword, Integer orderby, Integer categoryId, String tags) throws IOException;
    // 根据标签进行分组查找
    List<Map<String,Object>> searchGroupByTags(String keyword,Integer categoryId,String tags);
}
