package com.kfhstu.furn.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kfhstu.furn.bean.Furn;
import com.kfhstu.furn.bean.Msg;
import com.kfhstu.furn.service.FurnService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author KFH
 * @version 1.0
 * 家具的web层
 */
@Controller
public class FurnController {

    //服务层作为属性
    private final FurnService furnService;

    public FurnController(FurnService furnService) {
        this.furnService = furnService;
    }

    /**
     * 响应保存对象的方法
     * _@RequestBody 将请求的json数据转换为java对象
     * _@ResponseBody 将返回的数据直接写入响应体中,以json格式
     */
    @PostMapping("/save")
    @ResponseBody
    public Msg save(@Validated @RequestBody Furn furn, Errors errors) {
        //验证返回的字段是否有格式错误
        List<FieldError> fieldErrors = errors.getFieldErrors();
        Map<String, Object> map = new HashMap<>();
        //通过集合验证
        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        if (map.isEmpty()){
            furnService.save(furn);
            return Msg.success();
        }
        return Msg.fail().add("errors",map);

    }

    /**
     * 获取所有对象
     */
    @RequestMapping("/furns")
    @ResponseBody
    public Msg findAll() {
        //查出所有对象
        List<Furn> furns = furnService.findAll();
        //返回信息存入集合
        return Msg.success().add("furns", furns);
    }

    /**
     * 更新家具
     * 这里要使用请求方式过滤器
     */
    @PutMapping("/update")
    @ResponseBody
    public Msg updateFurn(@Validated @RequestBody Furn furn, Errors errors) {
        //验证返回的字段是否有格式错误
        List<FieldError> fieldErrors = errors.getFieldErrors();
        Map<String, Object> map = new HashMap<>();
        //通过集合验证
        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        if (map.isEmpty()){
            furnService.updateFurn(furn);
            //返回信息存入集合
            return Msg.success();
        }
        return Msg.fail().add("errors",map);
    }

    /**
     * 删除家具
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Msg delete(@PathVariable Integer id) {
        furnService.deleteFurn(id);
        return Msg.success();
    }

    /**
     * 分页获取家具,添加根据关键字搜索功能
     * 首先配置config文件
     *
     * _@RequestParam 因为这个可以设置默认值
     */
    @RequestMapping("/furnsByPage")
    @ResponseBody
    public Msg limitFurns(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //这里的结果已经被带了limit分页
        List<Furn> furns = furnService.findAll();
        //包含分页的信息
        PageInfo<Furn> pageInfo = new PageInfo<>(furns);
        //返回，postman看其结构extend.pageInfo.list
        return Msg.success().add("pageInfo", pageInfo);
    }

    /**
     * 分页获取家具,添加根据关键字搜索功能
     * 首先配置config文件
     *
     * _@RequestParam 因为这个可以设置默认值
     */
    @RequestMapping("/furnsByConditionPage")
    @ResponseBody
    public Msg limitFurnsByCondition(@RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "5") Integer pageSize,
                                     @RequestParam(defaultValue = "",required = false) String search) {
        PageHelper.startPage(pageNum, pageSize);
        //这里的结果已经被带了limit分页,并且选择性带条件
        List<Furn> furns = furnService.findByCondition(search);
        //所有查询的方法，包含分页的信息
        PageInfo<Furn> pageInfo = new PageInfo<>(furns);
        //返回，postman看其结构extend.pageInfo.list
        return Msg.success().add("pageInfo", pageInfo);
    }

}
