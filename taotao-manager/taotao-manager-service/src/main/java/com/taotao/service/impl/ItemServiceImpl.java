package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
   @Autowired
   private JmsTemplate jmsTemplate;
   @Autowired
   private Destination topicDestination;
    @Override
    public TbItem getItemById(Long itemId) {
        return tbItemMapper.findItemById(itemId);
    }

    @Override
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        //初始化分页 设置  他的底层原理是自动获取limt
        PageHelper.startPage(page, rows);
        List<TbItem> items = tbItemMapper.findItemAll();

        PageInfo<TbItem> pageInfo = new PageInfo<>(items);

        EasyUIDataGridResult result = new EasyUIDataGridResult();

        result.setTotal(pageInfo.getTotal());

        result.setRows(items);
        return result;
    }

    @Override
    public TaotaoResult deleteItems(Integer[] ids) {
        int i = tbItemMapper.deleteItems(ids);
        if (i != 0) {
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public TaotaoResult addItems(TbItem tbItem, String desc) {
        final Long itemId = IDUtils.genItemId();
        tbItem.setId(itemId);
        tbItem.setStatus((byte) 1);
        Date date = new Date();
        tbItem.setCreated(date);
        tbItem.setUpdated(date);
        //所有数据准备完毕才能添加商品信息
        int itemCount = tbItemMapper.addItems(tbItem);
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(itemId);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        //准备了描述信息的所有数据才能添加描述信息
        int itemDescCount = tbItemDescMapper.addItemDesc(itemDesc);

        if (itemCount != 0 && itemDescCount != 0) {
            jmsTemplate.send(topicDestination, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage message=session.createTextMessage(itemId+"");
                    return message;
                }
            });

            return TaotaoResult.ok();
        }


        return TaotaoResult.build(500, "添加商品有误，请重新输入");
    }

    @Override
    public TaotaoResult updateup(Integer[] ids) {
        int itemup = tbItemMapper.updateup(ids);
        if (itemup != 0) {
            return TaotaoResult.ok();
        }
        return null;
    }
    @Override
    public TaotaoResult updateDown(Integer[] ids) {
        int itemdown = tbItemMapper.updatedown(ids);
        if (itemdown != 0) {
            return TaotaoResult.ok();
        }
        return  null;
    }

    @Override
    public TaotaoResult queryItemDescByItemId(Long id) {
        TaotaoResult taotaoResult = new TaotaoResult();
        TbItemDesc itemDesc = tbItemDescMapper.queryItemDescByItemId(id);
        taotaoResult.setStatus(200);
        taotaoResult.setData(itemDesc.getItemDesc());
        System.out.println(taotaoResult);
        return taotaoResult;
    }

    @Override
    public TaotaoResult updateItemAndItemDesc(TbItem tbItem, String desc) {
        tbItem.setStatus((byte) 1);
        Date tiem = new Date();
        //TbItem item = itemMapper.queryItemById(tbItem.getId());
        //tbItem.setCreated(item.getCreated());
        tbItem.setUpdated(tiem);
        int itemCount = tbItemMapper.updateItem(tbItem);

        //TbItemDesc tbItemDesc = itemDescMapper.queryItemDescByItemId(tbItem.getId());
        //TbItemDesc itemDesc = itemDescMapper.queryItemDescByItemId(tbItem.getId());
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(tbItem.getId());
        itemDesc.setItemDesc(desc);
        //tbItemDesc.setCreated(tiem);
        itemDesc.setUpdated(tiem);
        int itemDescCount =tbItemDescMapper.updateItemDesc(itemDesc);
        //System.out.println(itemDesc);
        System.out.println(itemCount + "," + itemDescCount);
        if (itemCount != 0 && itemDescCount != 0) {
            return TaotaoResult.ok();
        } else {
            return TaotaoResult.build(500, "商品修改失败");
        }
    }

}
