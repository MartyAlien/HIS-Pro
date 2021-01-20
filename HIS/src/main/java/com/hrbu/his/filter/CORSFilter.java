package com.hrbu.his.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: CORSFilter
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/16 14:10
 */
@WebFilter("/*")
public class CORSFilter implements Filter {
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 响应标头指定 指定可以访问资源的URI路径
        response.setHeader("Access-Control-Allow-Origin", "*");
        //响应标头指定响应访问所述资源到时允许的一种或多种方法
        response.setHeader("Access-Control-Allow-Methods", "*");
        //设置 缓存可以生存的最大秒数
        response.setHeader("Access-Control-Max-Age", "3600");
        //设置  受支持请求标头
        response.setHeader("Access-Control-Allow-Headers", "*");
        // 指示的请求的响应是否可以暴露于该页面。当true值返回时它可以被暴露
        response.setHeader("Access-Control-Allow-Credentials","true");

        filterChain.doFilter(servletRequest, servletResponse);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}