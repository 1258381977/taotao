package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
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

//查询
    @RequestMapping("/item/{itemId}")

    @ResponseBody
    public TbItem findItem(@PathVariable long itemId) {
        TbItem item = itemService.getItemById(itemId);

        return item;
    }
    //分页
    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page, Integer rows){
        EasyUIDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }
    //删除
    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public TaotaoResult deleteItem(Integer[] ids){
        TaotaoResult result = itemService.deleteItems(ids);
        return result;
    }
    @RequestMapping("/rest/page/item-edit/{ids}")
    public void showUpdate(@PathVariable Integer ids){
        System.out.println(ids);
    }
    //修改
    @RequestMapping("/item/save")
    @ResponseBody
    public TaotaoResult addItems(TbItem tbItem, String desc){
        TaotaoResult result = itemService.addItems(tbItem, desc);
        return result;
    }
    //上架
    @RequestMapping("/rest/item/reshelf")
    @ResponseBody
    public TaotaoResult updataup(Integer []ids){
        TaotaoResult updateup = itemService.updateup(ids);
        return updateup;
    }
    //下架
    @RequestMapping("/rest/item/instock")
    @ResponseBody
    public TaotaoResult updatadown(Integer []ids){
        TaotaoResult updateDown = itemService.updateDown(ids);
        return updateDown;
    }
    //修改
   /* @RequestMapping("/rest/item/update")
    @ResponseBody
    public TaotaoResult updateItemList(TbItem tbItem,String desc){
        TaotaoResult result=itemService.updateItems(tbItem,desc);
        return result;
    }*/
    @RequestMapping("/item/query/item/desc/{id}")
    @ResponseBody
    public TaotaoResult findItemDescByItemId(@PathVariable Long id){
        TaotaoResult taotaoResult = itemService.queryItemDescByItemId(id);
        //System.out.println(taotaoResult);
        return taotaoResult;
    }

    @RequestMapping("/rest/item/update")
    @ResponseBody
    public TaotaoResult updateItem(TbItem tbItem,String desc){

        TaotaoResult taotaoResult = itemService.updateItemAndItemDesc(tbItem, desc);
        System.out.println(taotaoResult.getStatus()+","+taotaoResult.getMsg());
        return taotaoResult;
    }
}
