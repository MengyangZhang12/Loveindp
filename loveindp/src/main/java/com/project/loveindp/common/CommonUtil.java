package com.project.loveindp.common;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class CommonUtil {
    // 拼接BindingResult中的错误信息，放入CommonErr对象中
    public static String processErrorString(BindingResult bindingResult){
        // 没有错误的情况下返回空字符串
        if(!bindingResult.hasErrors()){
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(FieldError fieldError:bindingResult.getFieldErrors()){
            stringBuilder.append(fieldError.getDefaultMessage()+",");    // 获得注解后面message中的字符串
        }
        return stringBuilder.substring(0,stringBuilder.length()-1);
    }

}
