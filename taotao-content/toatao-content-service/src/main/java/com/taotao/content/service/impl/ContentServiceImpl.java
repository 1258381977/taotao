package com.taotao.content.service.impl;


import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.content.jedis.JedisClient;
import com.taotao.content.service.ContentService;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
  @Autowired
  private TbContentMapper tbContentMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${CONIENT_KEY}")
    private String CONIENT_KEY;
    @Override
    public EasyUIResult findContentAll(Long contentCategoryId) {



        List<TbContent> contents =tbContentMapper.findContentByCategoryId(contentCategoryId);
       EasyUIResult result=new EasyUIResult();
       result.setTotal((long) contents.size());
       result.setRows(contents);

     /*//走到这里 就把数据存入redis中
        jedisClient.set(CONIENT_KEY, JsonUtils.objectToJson(result));
        System.out.println("从数据库中获取数据");*/
        return  result;
    }

    @Override
    public TaotaoResult addContent(TbContent tbContent) {
        Date date=new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        tbContentMapper.addContent(tbContent);

        jedisClient.del(CONIENT_KEY);
        return TaotaoResult.ok(tbContent);
    }

    @Override
    public TaotaoResult updateContent(TbContent tbContent) {
        Date date = new Date();
        tbContent.setUpdated(date);
        tbContentMapper.updateContent(tbContent);
        return TaotaoResult.ok();
    }

    @Override
    public List<TbContent> getContentAll(Long contentCategoryId) {
        String json = jedisClient.get(CONIENT_KEY);
        if(StringUtils.isNotBlank(json)){
            List<TbContent> contents = JsonUtils.jsonToList(json, TbContent.class);
            return  contents;
        }
        //判断json不等于null 不为空
        List<TbContent> contents =tbContentMapper.findContentByCategoryId(contentCategoryId);
        //走到这里 就把数据存入redis中
        jedisClient.set(CONIENT_KEY, JsonUtils.objectToJson(contents));

        return contents;
    }
}
