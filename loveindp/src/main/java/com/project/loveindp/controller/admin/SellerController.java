package com.project.loveindp.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.loveindp.common.*;
import com.project.loveindp.model.SellerModel;
import com.project.loveindp.request.PageQuery;
import com.project.loveindp.request.SellerCreateReq;
import com.project.loveindp.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller("/admin/seller")
@RequestMapping("/admin/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    /*----------------------- 商户列表 -----------------------*/
    @AdminPermission
    @RequestMapping("/index")
    public ModelAndView index(PageQuery pageQuery){

        PageHelper.startPage(pageQuery.getPage(),pageQuery.getSize());
        List<SellerModel> sellerModelList = sellerService.selectAll();
        PageInfo<SellerModel> sellerModelPageInfo = new PageInfo<>(sellerModelList);

        ModelAndView modelAndView = new ModelAndView("/admin/seller/index");
        modelAndView.addObject("data",sellerModelPageInfo);
        modelAndView.addObject("CONTROLLER_NAME","seller");
        modelAndView.addObject("ACTION_NAME","index");
        return modelAndView;
    }
    /*----------------------- 商户列表 -----------------------*/

    /*----------------------- 创建商户 -----------------------*/
    @RequestMapping("/createpage")
    @AdminPermission
    public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView("/admin/seller/create.html");
        modelAndView.addObject("CONTROLLER_NAME","seller");
        modelAndView.addObject("ACTION_NAME","create");
        return modelAndView;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @AdminPermission
    public String create(@Valid SellerCreateReq sellerCreateReq, BindingResult bindingResult) throws BusinessException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        SellerModel sellerModel = new SellerModel();
        sellerModel.setName(sellerCreateReq.getName());
        sellerService.create(sellerModel);

        return "redirect:/admin/seller/index";

    }
    /*----------------------- 创建商户 -----------------------*/

    /*----------------------- 禁用商户 -----------------------*/
    @RequestMapping(value = "down",method = RequestMethod.POST)
    @AdminPermission
    @ResponseBody
    public CommonRes down(@RequestParam(value = "id")Integer id) throws BusinessException {
        SellerModel sellerModel = sellerService.changeStatus(id,1);
        return CommonRes.create(sellerModel);
    }

    @RequestMapping(value = "up",method = RequestMethod.POST)
    @AdminPermission
    @ResponseBody
    public CommonRes up(@RequestParam(value = "id")Integer id) throws BusinessException {
        SellerModel sellerModel = sellerService.changeStatus(id,0);
        return CommonRes.create(sellerModel);
    }
    /*----------------------- 禁用商户 -----------------------*/

}
