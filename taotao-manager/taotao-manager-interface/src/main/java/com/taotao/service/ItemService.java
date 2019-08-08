package com.taotao.service;


import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
    TbItem getItemById(Long itemId);

    EasyUIDataGridResult getItemList(Integer page, Integer rows);

    TaotaoResult deleteItems(Integer[] ids);

    TaotaoResult addItems(TbItem tbItem, String desc);

    TaotaoResult updateup(Integer [] ids);

    TaotaoResult updateDown(Integer [] ids);

    TaotaoResult queryItemDescByItemId(Long id);

    TaotaoResult updateItemAndItemDesc(TbItem tbItem, String desc);
}
