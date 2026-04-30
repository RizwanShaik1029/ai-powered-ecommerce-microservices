package com.srb.auth.filter;

import com.srb.auth.service.CustomUserDetails;
import com.srb.auth.util.UtilJwt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final UtilJwt utilJwt;

    private final CustomUserDetails customUserDetails;

    public JwtFilter(UtilJwt utilJwt, CustomUserDetails customUserDetails)
    {
        this.utilJwt=utilJwt;
        this.customUserDetails = customUserDetails;
    }




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = null;
        String phNumber = null;

        String authHeader = request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer "))
        {
             token = authHeader.substring(7);
            phNumber = utilJwt.extractUserName(token);

            System.out.println("ph num : 2"+phNumber);
        }

        if(phNumber!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
            UserDetails userDetails = customUserDetails.loadUserByUsername(phNumber);

            if(utilJwt.validateToken(phNumber, userDetails,token))
            {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }
        filterChain.doFilter(request,response);

    }
}
