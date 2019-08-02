package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

public interface TbItemMapper {

    @Select("SELECT * FROM tbitem WHERE id =#{id}")
    TbItem findItemById(long itemId);
    @Select("SELECT * FROM tbitem")
    List<TbItem> findItemAll();
    @Delete("<script> DELETE FROM tbitem WHERE id IN <foreach collection='array' item='id' open='(' separator=',' close=')'>#{id}</foreach> </script>")
    int deleteItems(Integer[] ids);
}