package com.weiliang.service;

import com.weiliang.entity.Brand;
import com.weiliang.entity.PageBean;

import java.util.List;

/**
 * @author liweiliang
 * @description 1.0
 * @date 2024-03-07 18:24:44
 */
public interface BrandService {

    /**
     * @details: 查询所有品牌
     * @param: []
     * @paramType: []
     * @return: java.util.List<com.weiliang.entity.Brand>            2024/3/7 18:25
     **/
    List<Brand> selectAll();

    /**
     * @details: 根据id查询品牌
     * @param: [id]
     * @paramType: [java.lang.Integer]
     * @return: com.weiliang.entity.Brand            2024/3/7 18:25
     **/
    Brand selectById(Integer id);

/**
     * @details: 根据条件查询
     * @param: [brand]
     * @paramType: [com.weiliang.entity.Brand]
     * @return: java.util.List<com.weiliang.entity.Brand>            2024/3/7 23:19
     **/
    List<Brand> selectByCondition(Brand brand);

    /**
     * @details: 分页查询
     * @param: [currentPage, pageSize]
     * @paramType: [int, int]
     * @return: com.weiliang.entity.PageBean<com.weiliang.entity.Brand>            2024/3/7 23:19
     **/
    PageBean<Brand> selectByPage(int currentPage, int pageSize);

    /**
     * @details: 根据条件分页查询
     * @param: [currentPage, pageSize, brand]
     * @paramType: [int, int, com.weiliang.entity.Brand]
     * @return: com.weiliang.entity.PageBean<com.weiliang.entity.Brand>            2024/3/7 23:19
     **/
    PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand);

    /**
     * @details: 批量删除
     * @param: [ids]
     * @paramType: [int[]]
     * @return: void            2024/3/7 19:07
     **/
    void deleteByIds(int[] ids);

    /**
     * @details: 单个删除
     * @param: [id]
     * @paramType: [int]
     * @return: void            2024/3/7 19:07
     **/
    void deleteById(int id);

    /**
     * @details: 增加品牌
     * @param: [brand]
     * @paramType: [com.weiliang.entity.Brand]
     * @return: void            2024/3/7 19:07
     **/
    void add(Brand brand);

    /**
     * @details: 修改品牌
     * @param: [brand]
     * @paramType: [com.weiliang.entity.Brand]
     * @return: void            2024/3/7 23:19
     **/
    void update(Brand brand);

}