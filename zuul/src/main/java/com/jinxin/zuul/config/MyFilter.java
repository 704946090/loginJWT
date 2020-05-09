package com.jinxin.zuul.config;

import com.jinxin.zuul.redis.RedisDao;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(MyFilter.class);
    private static String PRE_TYPE = "pre";

    @Autowired
    private RedisDao redisDao;

    @Override
    public String filterType() {
        //type有4种类型:per,post,routing,error
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 自定义执行逻辑,如:校验请求头里是否有username，没有则报错
     * 从redis中读取jwt令牌
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String url = request.getRequestURI();
//        System.out.println("url:"+url);
        if("/api/userservice/foo".equals(url)){
            System.out.println("helloUrl");
            Object username = request.getParameter("username");
            System.out.println("username:"+username);
            if(username==null||"".equals(username)){
                /**
                 * 如果用户名为空直接返回
                 */
                log.warn("username is empty");
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(401);
                try {
                    ctx.getResponse().getWriter().write("username is empty!");
                }catch (Exception e){
                    return null;
                }
            }else {
                /**
                 * 从redis中读取username的JWT令牌
                 */
                String authorization = redisDao.getValue(username.toString());
                if(authorization!=null && !"".equals(authorization)){
                    System.out.println("authorization:"+authorization);
                    ctx.addZuulRequestHeader("Authorization","Bearer "+authorization);
                }else {
                    ctx.setSendZuulResponse(false);
                    ctx.setResponseStatusCode(401);
                    try {
                        ctx.getResponse().getWriter().write("username:"+username+": JWT is empty!please login agin.");
                    }catch (Exception e){
                        return null;
                    }
                }
            }
        }
        return null;
    }
}
