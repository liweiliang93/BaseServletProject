package com.weiliang.util;

import lombok.Getter;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author liweiliang
 * @description 1.0
 * @date 2024-03-07 18:08:53
 */
public class SqlSessionFactoryUtils {

    //通过getSqlSessionFactory()方法获取同一个sqlSessionFactory,防止资源浪费.
    //声明sqlSessionFactory静态对象,只加载一次
    @Getter
    private static SqlSessionFactory sqlSessionFactory;

    //静态代码块内获取sqlSessionFactory对象,只生成一次
    static{
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}