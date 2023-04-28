package com.project.loveindp.recommend;

import org.apache.spark.ml.classification.LogisticRegressionModel;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.Vectors;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RecommencSortService {

    private SparkSession spark;

    private LogisticRegressionModel lrModel;

    @PostConstruct
    public void init() {
        // 加载LR模型
        spark = SparkSession.builder().master("local").appName("Loveindp").getOrCreate();
        lrModel = LogisticRegressionModel.load("file:///Users/zhangkuankuan/Downloads/Devtools/data/lrmodel");
    }

    public List<Integer> sort(List<Integer> shopIdList, Integer userId) {

        // 需要根据lrModel所需要的11维的x，生成特征，然后调用其预测方法
        List<ShopSortModel> list = new ArrayList<>();
            System.out.println("list:" + shopIdList.size());
            for (int i : shopIdList) {
                System.out.println(i);
            }
        for (Integer shopId : shopIdList) {
            // 这里是使用假数据，可从数据库中取得真实的性别、年龄、评分、价格等做特征转化生成feture向量
            Vector vector = Vectors.dense(1, 0, 0, 0, 0, 1, 0.6, 0, 0, 1, 0);//模拟用户
                System.out.println("spark: " + spark);
                System.out.println("lrModel: "  + lrModel);
            Vector result = lrModel.predictProbability(vector);//概率，eg：0.9
                System.out.println("for: " + 3);
            double[] array = result.toArray();//有两个值
            double score = array[1];//正样本数值
                System.out.println("shopId: " + shopId);
                System.out.println("score: " + score);
            ShopSortModel shopSortModel = new ShopSortModel();
            shopSortModel.setShopId(shopId);
            shopSortModel.setScore(score);

            list.add(shopSortModel);
        }

        //对list做排序
        list.sort(new Comparator<ShopSortModel>() {
            @Override
            public int compare(ShopSortModel o1, ShopSortModel o2) {
                if (o1.getScore() < o2.getScore()) {
                    return 1;
                } else if (o1.getScore() > o2.getScore()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        System.out.println("after sort: ");
        for (ShopSortModel s : list) {
            System.out.println(s.getShopId());
        }
        return list.stream().map(shopSortModel -> shopSortModel.getShopId()).collect(Collectors.toList());
    }
}
