package com.project.loveindp.dal;


import com.project.loveindp.model.UserModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Thu Mar 02 00:53:05 PST 2023
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Thu Mar 02 00:53:05 PST 2023
     */
    int insert(UserModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Thu Mar 02 00:53:05 PST 2023
     */
    int insertSelective(UserModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Thu Mar 02 00:53:05 PST 2023
     */
    UserModel selectByPrimaryKey(Integer id);

    // 同时使用telphone和password进行chaxun
    UserModel selectByTelphoneAndPassword(@Param("telphone") String telphone, @Param("password") String password);

    // 获取所有用户数
    Integer countAllUser();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Thu Mar 02 00:53:05 PST 2023
     */
    int updateByPrimaryKeySelective(UserModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Thu Mar 02 00:53:05 PST 2023
     */
    int updateByPrimaryKey(UserModel record);

}