package com.binga.binga.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


/* la classe OncePerRequestFilter est utilisée pour la gestion des filtres dans une application
         basée sur Spring. Elle fournit une abstraction utile pour
         créer des filtres de requête qui doivent être exécutés une fois par requête entrante.*/


@Component

     /*   L'annotation @Component est une annotation de base de Spring Framework qui indiqu
        à Spring que la classe annotée doit être automatiquement détectée et configurée en
                tant que composant dans l'application.*/
@RequiredArgsConstructor

/*L'annotation @RequiredArgsConstructor de Lombok génère automatiquement un constructeur pour la classe en
        utilisant tous les champs déclarés comme final ou @NonNull.*/

public class JwtAuthanticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal( @NonNull  HttpServletRequest request,
                                  @NonNull  HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
                                     throws ServletException, IOException {


        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null || jwtService.isTokenExpired(jwt)) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);

        }
        filterChain.doFilter(request, response);
    }
}
