package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbItemCat;
import org.apache.ibatis.annotations.Select;

public interface TbItemCatMapper {
    @Select("SELECT * FROM tbitemcat WHERE parentId = #{id}")
    List<TbItemCat> findTbItemCatByParentId(Long id);
}