package com.project.loveindp.service.impl;

import com.project.loveindp.common.BusinessException;
import com.project.loveindp.common.EmBusinessError;
import com.project.loveindp.dal.SellerModelMapper;
import com.project.loveindp.model.SellerModel;
import com.project.loveindp.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商家相关接口实现
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerModelMapper sellerModelMapper;

    /*----------------------- 商家入住 -----------------------*/
    @Override
    @Transactional
    public SellerModel create(SellerModel sellerModel) {
        sellerModel.setCreatedAt(new Date());
        sellerModel.setUpdatedAt(new Date());
        sellerModel.setRemarkScore(new BigDecimal(0));
        sellerModel.setDisabledFlag(0);
        sellerModelMapper.insertSelective(sellerModel);
        return get(sellerModel.getId());
    }
    /*----------------------- 商家入住 -----------------------*/

    /*----------------------- 查找商家 -----------------------*/
    @Override
    public SellerModel get(Integer id) {
        return sellerModelMapper.selectByPrimaryKey(id);
    }
    /*----------------------- 查找商家 -----------------------*/

    /*----------------------- 查找所有商家 -----------------------*/
    @Override
    public List<SellerModel> selectAll() {
        return sellerModelMapper.selectAll();
    }
    /*----------------------- 查找所有商家 -----------------------*/

    /*----------------------- 商家禁用 -----------------------*/
    @Override
    public SellerModel changeStatus(Integer id, Integer disabledFlag) throws BusinessException {
        SellerModel sellerModel = get(id);
        if (sellerModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        sellerModel.setDisabledFlag(disabledFlag);
        sellerModelMapper.updateByPrimaryKeySelective(sellerModel);
        return null; // sellerModel?
    }

    /*----------------------- 商家禁用 -----------------------*/

    @Override
    public Integer countAllSeller() {
        return sellerModelMapper.countAllSeller();
    }
}
