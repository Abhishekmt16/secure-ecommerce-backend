package com.securecommerce.app.security;

import com.securecommerce.app.security.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

    @Component
    public class JwtFilter{

        @Autowired
        private JwtUtil jwtUtil;

        /*@Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain filterChain)
                throws ServletException, IOException {

            String path = request.getServletPath();

            // ✅ Allow public URLs
            if (
                    path.equals("/") ||
                            path.equals("/index.html") ||
                            path.equals("/admin.html") ||
                            path.equals("/checkout.html") ||
                            path.startsWith("/css") ||
                            path.startsWith("/js") ||
                            path.startsWith("/images") ||
                            path.equals("/api/users/login") ||
                            path.equals("/api/users/register")
            ) {
                filterChain.doFilter(request, response);
                return;
            }

            String authHeader = request.getHeader("Authorization");

            // ❌ IF NO TOKEN → JUST CONTINUE (DON'T BLOCK)
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            // ✅ TOKEN EXISTS → CONTINUE
            filterChain.doFilter(request, response);
        }*/
    }

