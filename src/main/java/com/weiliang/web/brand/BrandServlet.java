package com.weiliang.web.brand;

import com.alibaba.fastjson.JSON;
import com.weiliang.entity.Brand;
import com.weiliang.entity.PageBean;
import com.weiliang.service.BrandService;
import com.weiliang.service.impl.BrandServcieImpl;
import com.weiliang.web.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * @author liweiliang
 * @description 1.0
 * @date 2024-03-07 18:37:04
 */
@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private final BrandService brandService = new BrandServcieImpl();

    /**
     * @details: 查询所有品牌
     * @param: [request, response]
     * @paramType: [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse]
     * @return: void            2024/3/7 18:53
     **/
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws IOException{
        List<Brand> brands = brandService.selectAll();
        String jsonString = JSON.toJSONString(brands);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * @details: 根据id查询品牌
     * @param: [request, response]
     * @paramType: [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse]
     * @return: void            2024/3/7 18:53
     **/
    public void selectById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //请求方式:http://localhost:8080/BaseServletProject/brand/selectById?id=?
        String id = request.getParameter("id");
        Brand brand = brandService.selectById(Integer.parseInt(id));
        String jsonString = JSON.toJSONString(brand);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * @details: 分页查询
     * @param: [request, response]
     * @paramType: [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse]
     * @return: void            2024/3/7 18:53
     **/
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //请求方式:http://localhost:8080/BaseServletProject/brand/selectByPage?currentPage=?&pageSize=?
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        //分页查询
        PageBean<Brand> pageBean = brandService.selectByPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * @details: 根据条件查询
     * @param: [request, response]
     * @paramType: [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse]
     * @return: void            2024/3/7 18:53
     **/
    public void selectByCondition( HttpServletRequest request, HttpServletResponse response) throws IOException {
        //请求方式:http://localhost:8080/BaseServletProject/brand/selectByCondition?brandName=?&companyName=?&status=?
        request.setCharacterEncoding("utf-8");
        BufferedReader reader = request.getReader();
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        String jsonString = stringBuilder.toString();
        //将json字符串转换为对象
        Brand brand = JSON.parseObject(jsonString, Brand.class);
        List<Brand> brands = brandService.selectByCondition(brand);
        String jsonStrings = JSON.toJSONString(brands);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(jsonStrings);
    }

    /**
     * @details: 根据条件分页查询
     * @param: [request, response]
     * @paramType: [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse]
     * @return: void            2024/3/7 18:53
     **/
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //请求方式:http://localhost:8080/BaseServletProject/brand/selectByPageAndCondition?currentPage=?&pageSize=?条件在请求体中
        request.setCharacterEncoding("utf-8");
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");

        BufferedReader reader = request.getReader();
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        String jsonString = stringBuilder.toString();

        //将json字符串转换为对象
        Brand brand = JSON.parseObject(jsonString, Brand.class);
        //分页查询
        if(brand != null){
            PageBean<Brand> pageBean = brandService.selectByPageAndCondition(Integer.parseInt(currentPage), Integer.parseInt(pageSize), brand);
            jsonString = JSON.toJSONString(pageBean);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(jsonString);
        }
    }

    /**
     * @details: 批量删除
     * @param: [request, response]
     * @paramType: [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse]
     * @return: void            2024/3/7 18:53
     **/
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //请求方式:http://localhost:8080/BaseServletProject/brand/deleteByIds?ids=1,2,3.....
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        int[] ids = JSON.parseObject(params, int[].class);
        if(ids != null){
            brandService.deleteByIds(ids);
        }
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write("删除成功");
    }

    /**
     * @details: 单个删除
     * @param: [request, response]
     * @paramType: [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse]
     * @return: void            2024/3/7 18:53
     **/
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //请求方式:http://localhost:8080/BaseServletProject/brand/deleteById?id=?
        String id = request.getParameter("id");
        brandService.deleteById(Integer.parseInt(id));
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write("删除成功");
    }

    /**
     * @details: 增加品牌
     * @param: [request, response]
     * @paramType: [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse]
     * @return: void            2024/3/7 18:53
     **/
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //请求方式:http://localhost:8080/BaseServletProject/brand/add
        //请求参数:{"brandName":"?","companyName":"?","ordered":?,"description":"?","status":?}
        request.setCharacterEncoding("utf-8");
        //获取请求体并读取
        BufferedReader reader = request.getReader();
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        String jsonString = stringBuilder.toString();
        //将json字符串转换为对象
        Brand brand = JSON.parseObject(jsonString, Brand.class);
        brandService.add(brand);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write("添加成功");
    }

    /**
     * @details: 修改品牌
     * @param: [request, response]
     * @paramType: [javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse]
     * @return: void            2024/3/7 18:53
     **/
    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //请求方式:http://localhost:8080/BaseServletProject/brand/update
        //请求参数:{"id":?,"brandName":"?","companyName":"?","ordered":?,"description":"?","status":?}
        request.setCharacterEncoding("utf-8");
        //获取请求体并读取
        BufferedReader reader = request.getReader();
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        String jsonString = stringBuilder.toString();
        //将json字符串转换为对象
        Brand brand = JSON.parseObject(jsonString, Brand.class);
        System.out.println(brand);
        brandService.update(brand);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write("修改成功");
    }

}
