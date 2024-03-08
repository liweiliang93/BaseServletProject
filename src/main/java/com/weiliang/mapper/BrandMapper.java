package com.weiliang.mapper;

import com.weiliang.entity.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author liweiliang
 * @description 1.0
 * @date 2024-03-07 18:08:18
 */
public interface BrandMapper {

    /**
     * @details: 查询所有品牌
     * @param: [id]
     * @paramType: [java.lang.Integer]
     * @return: java.util.List<com.weiliang.entity.Brand>            2024/3/7 18:17
     **/
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    /**
     * @details: 根据id查询品牌
     * @param: [id]
     * @paramType: [java.lang.Integer]
     * @return: com.weiliang.entity.Brand            2024/3/7 19:03
     **/
    @Select("select * from tb_brand where id = #{id}")
    @ResultMap("brandResultMap")
    Brand selectById(@Param("id") Integer id);

    /**
     * @details: 查询总记录数
     * @param: []
     * @paramType: []
     * @return: int            2024/3/7 19:11
     **/
    @Select("select count(*) from tb_brand")
    int selectTotalCount();

    /**
     * @details: 分页查询
     * @param: [start, rows]
     * @paramType: [int, int]
     * @return: java.util.List<com.weiliang.entity.Brand>            2024/3/7 19:11
     **/
    @Select("select * from tb_brand limit #{start},#{rows}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("start") int start, @Param("rows") int rows);

    /**
     * @details: 根据条件查询总记录数
     * @param: [brand]
     * @paramType: [com.weiliang.entity.Brand]
     * @return: int            2024/3/7 19:11
     **/
    int selectTotalCountByCondition(Brand brand);

    /**
     * @details: 根据条件查询
     * @param: [brand]
     * @paramType: [com.weiliang.entity.Brand]
     * @return: java.util.List<com.weiliang.entity.Brand>            2024/3/7 19:11
     **/
    List<Brand> selectByCondition(@Param("brand")Brand brand);

    /**
     * @details: 分页查询
     * @param: [start, rows, brand]
     * @paramType: [int, int, com.weiliang.entity.Brand]
     * @return: java.util.List<com.weiliang.entity.Brand>            2024/3/7 19:11
     **/
    List<Brand> selectByPageAndCondition(@Param("start") int start, @Param("rows") int rows, @Param("brand") Brand brand);

    /**
     * @details: 批量删除
     * @param: [ids]
     * @paramType: [int[]]
     * @return: void            2024/3/7 19:05
     **/
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * @details: 单个删除
     * @param: [id]
     * @paramType: [int]
     * @return: void            2024/3/7 19:11
     **/
    @Delete("delete from tb_brand where id = #{id}")
    void deleteById(int id);

    /**
     * @details: 增加品牌
     * @param: [brand]
     * @paramType: [com.weiliang.entity.Brand]
     * @return: void            2024/3/7 19:11
     **/
    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    /**
     * @details: 修改品牌
     * @param: [brand]
     * @paramType: [com.weiliang.entity.Brand]
     * @return: void            2024/3/7 19:11
     **/
    @Update("update tb_brand set brand_name=#{brandName},company_name=#{companyName},ordered=#{ordered},description=#{description},status=#{status} where id=#{id}")
    void update(Brand brand);

}
