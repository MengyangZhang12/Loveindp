package com.project.loveindp.recommend;


import com.project.loveindp.dal.RecommendDOMapper;
import com.project.loveindp.model.RecommendDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Service
public class RecommendService implements Serializable{

    @Autowired
    private RecommendDOMapper recommendDOMapper;


    public List<Integer> recall(Integer userId){
        RecommendDO recommendDO = recommendDOMapper.selectByPrimaryKey(userId);
        if (recommendDO == null){
            // 设置默认初始化推荐
            recommendDO = recommendDOMapper.selectByPrimaryKey(0);
        }
        String[] shopIdArr = recommendDO.getRecommend().split(",");
        ArrayList<Integer> shopIdList = new ArrayList<>();
        for (int i = 0; i < shopIdArr.length; i++) {
            shopIdList.add(Integer.valueOf(shopIdArr[i]));
        }
        return shopIdList;
    }
}
