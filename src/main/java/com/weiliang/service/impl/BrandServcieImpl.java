package com.weiliang.service.impl;

import com.weiliang.entity.Brand;
import com.weiliang.entity.PageBean;
import com.weiliang.mapper.BrandMapper;
import com.weiliang.service.BrandService;
import com.weiliang.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author liweiliang
 * @description 1.0
 * @date 2024-03-07 18:25:45
 */
public class BrandServcieImpl implements BrandService{
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public List<Brand> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = brandMapper.selectAll();
        sqlSession.close();
        return brands;
    }

    @Override
    public Brand selectById(Integer id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        Brand brand = brandMapper.selectById(id);
        sqlSession.close();
        return brand;
    }

    @Override
    public List<Brand> selectByCondition(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = brandMapper.selectByCondition(brand);
        sqlSession.close();
        return brands;
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        PageBean<Brand> pageBean = new PageBean<>();
        int totalCount = brandMapper.selectTotalCount();
        int start = (currentPage - 1) * pageSize;
        List<Brand> brands = brandMapper.selectByPage(start, pageSize);
        pageBean.setRows(brands);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        PageBean<Brand> pageBean = new PageBean<>();
        int totalCount = brandMapper.selectTotalCountByCondition(brand);
        int start = (currentPage - 1) * pageSize;
        List<Brand> brands = brandMapper.selectByPageAndCondition(start, pageSize, brand);
        pageBean.setRows(brands);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    }


    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        brandMapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        brandMapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void add(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        brandMapper.add(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void update(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        brandMapper.update(brand);
        sqlSession.commit();
        sqlSession.close();
    }

}
