package com.kfhstu.furn.service;

import com.kfhstu.furn.bean.Furn;

import java.util.List;

/**
 * @author KFH
 * @version 1.0
 * 家具的各种服务接口
 */
public interface FurnService {
    //保存家具
    public void save(Furn furn);

    //查找所有家具
    public List<Furn> findAll();

    //更新家具
    public void updateFurn(Furn furn);

    //删除家具
    public void deleteFurn(Integer id);

    //查找关键词
    public List<Furn> findByCondition(String name);
}
