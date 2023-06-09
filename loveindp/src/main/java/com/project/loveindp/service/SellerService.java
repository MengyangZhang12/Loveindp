package com.project.loveindp.service;

import com.project.loveindp.common.BusinessException;
import com.project.loveindp.model.SellerModel;

import java.util.List;

public interface SellerService {
    // 商家入住功能，本质上是创建
    SellerModel create(SellerModel sellerModel);

    // 根据id进行查询
    SellerModel get(Integer id);

    // 查询所有
    List<SellerModel> selectAll();

    // 禁用状态
    SellerModel changeStatus(Integer id,Integer disabledFlag) throws BusinessException;

    // 统计数量
    Integer countAllSeller();

}
