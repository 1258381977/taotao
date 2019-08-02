package com.taotao.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Override
    public List<EasyUITreeNode> getCatList(Long id) {
        List<TbItemCat> tbItemCats=tbItemCatMapper.findTbItemCatByParentId(id);
        List<EasyUITreeNode> result=new ArrayList<EasyUITreeNode>();

        for (TbItemCat itemCat:tbItemCats){
            EasyUITreeNode node=new EasyUITreeNode();
            //绑定id
            node.setId(itemCat.getId());

            node.setText(itemCat.getName());

            node.setState(itemCat.getIsParent()?"closed":"open");

            result.add(node);
        }

        return result;
    }
}
