package com.project.loveindp.service;


import com.project.loveindp.common.BusinessException;
import com.project.loveindp.model.CategoryModel;

import java.util.List;


public interface CategoryService {

    CategoryModel create(CategoryModel categoryModel) throws BusinessException;

    CategoryModel get(Integer id);

    List<CategoryModel> selectAll();

    Integer countAllCategory();

}
