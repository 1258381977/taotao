package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Override
    public TbItem getItemById(Long itemId) {
        return tbItemMapper.findItemById(itemId);
    }

    @Override
    public EasyUIResult getItemList(Integer page, Integer rows) {
      //初始化分页 设置  他的底层原理是自动获取limt
        PageHelper.startPage(page,rows);
        List<TbItem> items =tbItemMapper.findItemAll();

        PageInfo<TbItem> pageInfo = new PageInfo<>(items);

        EasyUIResult result=new EasyUIResult();

        result.setTotal(pageInfo.getTotal());

        result.setRows(items);
        return result;
    }
}
