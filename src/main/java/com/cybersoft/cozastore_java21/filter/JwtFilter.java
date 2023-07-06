package com.cybersoft.cozastore_java21.filter;

import com.cybersoft.cozastore_java21.utils.JwtHelper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//Tất cả request đều phải chạy vào filter
@Component
public class JwtFilter extends OncePerRequestFilter {

    /**
     * Nhận được token truyền trên header
     * Giải mã token
     * Nếu giải mã thành công thì hợp lệ
     * Tạo chứng thực và cho đi vào link người dùng gọi
     */

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String header = request.getHeader("Authorization");
            if (header.startsWith("Bearer ")){
                String token = header.substring(7);
                Claims claims = jwtHelper.decodeToken(token);
                if (claims != null){
                    SecurityContext context = SecurityContextHolder.getContext();
                    UsernamePasswordAuthenticationToken user =
                            new UsernamePasswordAuthenticationToken("","", new ArrayList<>());
                    context.setAuthentication(user);
                }
            }
        }catch(Exception e) {

            filterChain.doFilter(request, response);
        }
    }
}
