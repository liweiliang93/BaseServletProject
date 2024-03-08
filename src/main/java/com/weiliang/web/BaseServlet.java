package com.weiliang.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author liweiliang
 * @description 1.0
 * @date 2024-03-07 18:33:54
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取请求路径
        String uri = req.getRequestURI();

        //2.获取最后一段方法名
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index + 1);

        //3.获取执行方法的对象
        Class<? extends BaseServlet> aClass = this.getClass();

        //4.执行方法
        try {
            Method method = aClass.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}