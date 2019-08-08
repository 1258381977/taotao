package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TbItemMapper {

    @Select("SELECT * FROM tbitem WHERE id =#{id}")
    TbItem findItemById(long itemId);
    @Select("SELECT * FROM tbitem")
    List<TbItem> findItemAll();

    @Delete("<script> DELETE FROM tbitem WHERE id IN <foreach collection='array' item='id' open='(' separator=',' close=')'>#{id}</foreach> </script>")
    int deleteItems(Integer[] ids);
    @Insert("INSERT INTO tbitem(id, title, sellPoint, price, num, barcode, image, cid, created, updated) VALUE(#{id},#{title},#{sellPoint},#{price},#{num},#{barcode},#{image},#{cid},#{created},#{updated})")
    int addItems(TbItem tbItem);
    @Update("<script> UPDATE tbitem SET status='1' WHERE id IN <foreach collection='array' item='id' open='(' separator=',' close=')'>#{id}</foreach> </script>")
    int updateup(Integer[] ids);
    @Update("<script> UPDATE tbitem SET status='2' WHERE id IN <foreach collection='array' item='id' open='(' separator=',' close=')'>#{id}</foreach> </script>")
    int updatedown(Integer[] ids);

    @Select("select * from tbitem where id = #{id}")
    TbItem updateByQueryByItemId(Long id);
    @Update("<script>update tbitem <set>" +
            "<if test='title != null'>title = #{title},</if>" +
            "<if test='sellPoint != null'>sellPoint = #{sellPoint},</if>" +
            "<if test='price != null'>price = #{price},</if>" +
            "<if test='num != null'>num = #{num},</if>" +
            "<if test='barcode != null'>barcode = #{barcode},</if>" +
            "<if test='image != null'>image = #{image},</if>" +
            "<if test='cid != null'>cid = #{cid},</if>" +
            "<if test='status != null'>status = #{status},</if>" +
            "<if test='created != null'>created = #{created},</if>" +
            "<if test='updated != null'>updated = #{updated},</if>" +
            "</set> where id = #{id}</script>")
    int updateItem(TbItem tbItem);
}
