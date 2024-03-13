## 基础Servlet后端实现 + html中使用vue和axios基础实现

### 1.servlet项目问题解决
#### 1.1 servlet项目中的跨域问题

    通过添加过滤器解决跨域问题：CrossOrignFilter.java
    ```java
    @WebFilter(filterName = "CrossOriginFilter",urlPatterns = "/*")
    public class CrossOrignFilter implements Filter {
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
    
        }
    
        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    
            // 响应参数格式设置
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
    
            HttpServletResponse response1 = (HttpServletResponse) response;
            /* 允许跨域的主机地址 */
            response1.setHeader("Access-Control-Allow-Origin", "*");
            /* 允许跨域的请求方法GET, POST, HEAD 等 */
            response1.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
            /* 重新预检验跨域的缓存时间 (s) */
            response1.setHeader("Access-Control-Max-Age", "3600");
            /* 允许跨域的请求头 */
            response1.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
            /* 是否携带cookie */
            response1.setHeader("Access-Control-Allow-Credentials", "true");
    
            chain.doFilter(request, response1);
        }
    
        @Override
        public void destroy() {
    
        }
    }
    ```
#### 1.2 servlet项目中的数据格式问题

##### 1.2.1 Mapper中注解和参数的使用

​	①多参数使用**@Param()**注解,并且使用

​	②单个参数**直接引用**

​	③返回值是实体类时,使用自定义表和实体类的resultMap映射,并且使用属性时使用对象.属性的方式获取

​	④返回值不是实体列时,直接使用属性方式获取即可

##### 1.2.2 BaseServlet的执行方式

通过反射将获取到的请求路径进行拆解获取对象并执行方法

```
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
```

##### 1.2.3 日志格式配置

```
<configuration>
    <!--
        CONSOLE ：表示当前的日志信息是可以输出到控制台的。
    -->
    <appender name="Console" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>[%level] %blue(%d{HH:mm:ss.SSS}) %cyan([%thread]) %boldGreen(%logger{15}) - %msg %n</pattern>
        </encoder>
    </appender>

    <logger name="com.itheima" level="DEBUG" additivity="false">
        <appender-ref ref="Console"/>
    </logger>

    <!--
      level:用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF
     ， 默认debug
      <root>可以包含零个或多个<appender-ref>元素，标识这个输出位置将会被本日志级别控制。
      -->
    <root level="DEBUG">
        <appender-ref ref="Console"/>
    </root>
</configuration>
```

##### 1.2.4 servlet的request和response接收和发送

**获取请求头**

```
request.setCharacterEncoding("utf-8");
request.getParameter("currentPage");
```

**获取请求体**

```
request.setCharacterEncoding("utf-8");
BufferedReader reader = request.getReader();
String line = null;
StringBuilder stringBuilder = new StringBuilder();
while ((line = reader.readLine()) != null) {
    stringBuilder.append(line);
}
String jsonString = stringBuilder.toString();
```

**将json字符串转为对象**

```
Brand brand = JSON.parseObject(jsonString, Brand.class);
```

**response回传**

```
response.setContentType("application/json;charset=utf-8");
response.getWriter().write(jsonString);
```