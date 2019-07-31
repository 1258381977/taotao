package com.taotao.controller;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ItemController {
    /**
     *
     */
    @Autowired
    private ItemService itemService;


    @RequestMapping("/item/{itemId}")

    @ResponseBody
    public TbItem findItem(@PathVariable long itemId) {
        TbItem item = itemService.getItemById(itemId);

        return item;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIResult getItemList(Integer page, Integer rows) {
        EasyUIResult result = itemService.getItemList(page, rows);
        return  result;
    }
}
