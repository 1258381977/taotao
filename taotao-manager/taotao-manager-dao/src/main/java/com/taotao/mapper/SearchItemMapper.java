package com.taotao.mapper;

import com.taotao.common.pojo.SearchItem;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SearchItemMapper {

    @Select("SELECT a.id,a.title,a.sellPoint,a.price, a.image, b.name categoryName,c.itemDesc FROM tbitem a LEFT JOIN tbitemcat b ON a.cid=b.id LEFT JOIN tbitemdesc c ON a.id=c.itemId ")
    List<SearchItem> getItemList();

    @Select("SELECT a.id,a.title,a.sellPoint,a.price, a.image, b.name categoryName,c.itemDesc FROM tbitem a LEFT JOIN tbitemcat b ON a.cid=b.id LEFT JOIN tbitemdesc c ON a.id=c.itemId WHERE a.id=#{itemId}")
    SearchItem getItemById(Long itemId);
}
