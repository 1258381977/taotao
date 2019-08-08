package com.taotao.mapper;


import com.taotao.pojo.TbContent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TbContentMapper {
    @Select("SELECT * FROM tbcontent WHERE  categoryId=#{categoryId}")
    List<TbContent> findContentByCategoryId(Long contentCategoryId);
    @Insert("INSERT  INTO  tbcontent(categoryId, title, subTitle, titleDesc, url, pic, pic2, content, created, updated)VALUE (#{categoryId},#{title},#{subTitle},#{titleDesc},#{url},#{pic},#{pic2},#{content},#{created},#{updated})")
    void addContent(TbContent tbContent);
    @Update("update tbcontent set title=#{title},subTitle=#{subTitle},titleDesc=#{titleDesc},url=#{url},pic=#{pic},pic2=#{pic2},content = #{content},updated=#{updated} where id = #{id}")
    void updateContent(TbContent tbContent);
}