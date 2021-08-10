package br.com.zupacademy.breno.mercadolivre.config.security;

import br.com.zupacademy.breno.mercadolivre.users.User;
import br.com.zupacademy.breno.mercadolivre.users.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository userRepository;

    public JwtAuthenticationFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = retrieveToken(httpServletRequest);
        boolean valid = tokenService.isTokenValid(token);
        if (valid) {
            authenticateClient(token);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void authenticateClient(String token) {
        Long userId = tokenService.getUserId(token);
        User user = userRepository.findById(userId).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String retrieveToken(HttpServletRequest request)  {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }

}
