package com.taotao.mapper;

import com.taotao.pojo.TbItemDesc;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TbItemDescMapper {
    @Insert("INSERT INTO tbitemdesc(itemId, itemDesc, created, updated) VALUE (#{itemId},#{itemDesc},#{created},#{updated})")
    int addItemDesc(TbItemDesc itemDesc);
    @Select("select  itemDesc from tbitemdesc where itemId = #{id}")
    TbItemDesc queryItemDescByItemId(Long id);

    @Update("<script>update tbitemdesc <set>" +
            "<if test='itemDesc != null'>itemDesc = #{itemDesc},</if>" +
            "<if test='created != null'>created = #{created},</if>" +
            "<if test='updated != null'>updated = #{updated},</if>" +
            "</set> where itemId = #{itemId}</script>")
    int updateItemDesc(TbItemDesc itemDesc);
   /*@Update("UPDATE  tbitemdesc SET itemDesc=#{itemDesc},created=#{created},updated=#{updated} WHERE itemId=#{itemId}")
    int updateItemDesc(TbItemDesc itemDesc);*/
}