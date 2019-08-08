package com.taotao.content.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
   @Autowired
   private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getContentCategoryList(Long parentId) {
        List<TbContentCategory> contentCategories=tbContentCategoryMapper.findContentCategoryByParentId(parentId);
        List<EasyUITreeNode> nodes=new ArrayList<EasyUITreeNode>();

        for (TbContentCategory category:contentCategories){
            EasyUITreeNode node=new EasyUITreeNode();
            node.setId(category.getId());
            node.setText(category.getName());
            node.setState(category.getIsParent()?"closed":"open");
            nodes.add(node);
        }

        return nodes;
    }

    @Override
    public TaotaoResult addContentCategory(Long parentId, String name) {
      TbContentCategory contentCategory=new TbContentCategory();
        Date date=new Date();
      //默认没有父亲的
        contentCategory.setIsParent(false);
        //需要添加的名称
        contentCategory.setName(name);
        //关联父节点
        contentCategory.setParentId(parentId);

        contentCategory.setSortOrder(1);
        //状态 可选1  正常 2删除
        contentCategory.setStatus(1);
        contentCategory.setCreated(date);
        contentCategory.setUpdated(date);


        //添加分类
        tbContentCategoryMapper.addContentCategory(contentCategory);


    //根据parentid查询 分类对象 判断这个分类对象的iisparent是否为false如果为false改为true
        TbContentCategory category=tbContentCategoryMapper.findContentCategoryById(parentId);

        if(!category.getIsParent()){
            category.setIsParent(true);
            tbContentCategoryMapper.updateContentCategory(category);
        }
        return TaotaoResult.ok(contentCategory);
    }

    @Override
    public TaotaoResult updateCategory(Long id, String name) {
        TbContentCategory tbContentCategory = tbContentCategoryMapper.findContentCategoryById(id);
        tbContentCategory.setName(name);
        tbContentCategoryMapper.updateContentCategory(tbContentCategory);
        return TaotaoResult.ok();
    }

}
