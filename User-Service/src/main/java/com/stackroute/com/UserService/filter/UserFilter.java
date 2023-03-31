package com.stackroute.com.UserService.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import net.bytebuddy.description.type.TypeList;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String headerToken = req.getHeader("authorization");

        if(headerToken != null){
            System.out.println(headerToken);
            String token = headerToken.substring(7);
            Claims claims = Jwts.parser().setSigningKey("success").parseClaimsJws(token).getBody();
            System.out.println(claims);
            request.setAttribute(token, headerToken);
            chain.doFilter(request,response);
        }
    }
}
