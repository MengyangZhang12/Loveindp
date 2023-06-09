package com.project.loveindp.controller;

import com.project.loveindp.common.*;
import com.project.loveindp.model.UserModel;
import com.project.loveindp.request.LoginReq;
import com.project.loveindp.request.RegisterReq;
import com.project.loveindp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Controller("/user")
@RequestMapping("/user")
public class UserController {

    public static final String CURRENT_USER_SESSION = "currentUserSession";

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserService userService;

//    @RequestMapping("/test")
//    @ResponseBody
//    public String test(){
//        return "test";
//    }
//
//    @RequestMapping("/index")
//    public ModelAndView index(){
//        String userName = "imooc";
//        ModelAndView modelAndView = new ModelAndView("/index.html");//root is templates
//        modelAndView.addObject("name", userName);
//        return modelAndView;
//    }


    @RequestMapping("/get")
    @ResponseBody
    public CommonRes getUser(@RequestParam(name="id")Integer id) throws BusinessException {
        UserModel userModel = userService.getUser(id);
        if(userModel == null) {
            //return CommonRes.create(new CommonError(EmBusinessError.NO_OBJECT_FOUND),"fail");
            throw new BusinessException(EmBusinessError.NO_OBJECT_FOUND);
        } else {
            return CommonRes.create(userModel);
        }
    }

    @ResponseBody
    @RequestMapping("/register")
    public CommonRes register(@Valid @RequestBody RegisterReq registerReq, BindingResult bindingResult) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (bindingResult.hasErrors()){    // 如果有失败操作
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        UserModel registerUser = new UserModel();
        registerUser.setTelphone(registerReq.getTelphone());
        registerUser.setPassword(registerReq.getPassword());
        registerUser.setGender(registerReq.getGender());
        registerUser.setNickName(registerReq.getNickName());

        UserModel resUserModel = userService.register(registerUser);    // 进行密码加密，以及保证事务性

        return CommonRes.create(resUserModel);
    }
    /*----------------------- 注册方法 -----------------------*/

//    /*----------------------- 登录方法 -----------------------*/
    @ResponseBody
    @RequestMapping("/login")
    public CommonRes login(@Valid @RequestBody LoginReq loginReq, BindingResult bindingResult) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (bindingResult.hasErrors()){    // 如果有失败操作
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        UserModel userModel=userService.login(loginReq.getTelphone(),loginReq.getPassword());
        httpServletRequest.getSession().setAttribute(CURRENT_USER_SESSION, userModel);

        return CommonRes.create(userModel);
    }
//    /*----------------------- 登录方法 -----------------------*/
//
//    /*----------------------- 注销登录 -----------------------*/
    @ResponseBody
    @RequestMapping("/logout")
    public CommonRes logout() throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        httpServletRequest.getSession().invalidate();
        return CommonRes.create(null);    // 当前session无效化
    }
//    /*----------------------- 注销登录 -----------------------*/
//
//    /*----------------------- 获取当前用户信息 -----------------------*/
    @ResponseBody
    @RequestMapping("/getcurrentuser")
    public CommonRes getCurrentUser(){
        // 根据session获取当前用户信息
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute(CURRENT_USER_SESSION);
        return CommonRes.create(userModel);
    }
//    /*----------------------- 获取当前用户信息 -----------------------*/

}
