package com.taotao.mapper;


import com.taotao.pojo.TbContentCategory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TbContentCategoryMapper {
    @Select("SELECT * FROM  tbcontentcategory WHERE  parentId=#{parentId}")
    List<TbContentCategory> findContentCategoryByParentId(long parentId);
    @Select("SELECT * FROM  tbcontentcategory WHERE  id=#{id}")
    TbContentCategory findContentCategoryById(Long parentId);
    @Update("UPDATE tbcontentcategory SET  isParent=#{isParent} WHERE id=#{id}")
    void updateContentCategory(TbContentCategory category);
    @Insert(("INSERT  INTO tbcontentcategory(parentId, name, status, sortOrder, isParent, created, updated) VALUE (#{parentId},#{name},#{status},#{sortOrder},#{isParent},#{created},#{updated}) "))
    void addContentCategory(TbContentCategory contentCategory);
}