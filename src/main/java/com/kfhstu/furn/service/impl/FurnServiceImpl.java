package com.kfhstu.furn.service.impl;

import com.kfhstu.furn.bean.Furn;
import com.kfhstu.furn.bean.FurnExample;
import com.kfhstu.furn.dao.FurnMapper;
import com.kfhstu.furn.service.FurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author KFH
 * @version 1.0
 * FurnService实现类
 */
@Service
public class FurnServiceImpl  implements FurnService {

    //获取dao的接口对象，mapper对象
    @Autowired
    private FurnMapper furnMapper;

    @Override
    public void save(Furn furn) {
        furnMapper.insertSelective(furn);
    }

    @Override
    public List<Furn> findAll() {
        //传入null则没有where标签
        return furnMapper.selectByExample(null);
    }

    @Override//更新家具
    public void updateFurn(Furn furn) {
        furnMapper.updateByPrimaryKeySelective(furn);
    }

    @Override
    public void deleteFurn(Integer id) {
        furnMapper.deleteByPrimaryKey(id);
    }

    //根据关键词查找
    @Override
    public List<Furn> findByCondition(String name) {
        //创建furnExample对象，设置查询条件
        FurnExample furnExample = new FurnExample();
        FurnExample.Criteria criteria = furnExample.createCriteria();
        //不为空，则设置条件
        if (StringUtils.hasText(name)){
            criteria.andNameLike("%"+name+"%");
        }
        //否则默认查询所有
        return furnMapper.selectByExample(furnExample);
    }

//    删除家具
}
