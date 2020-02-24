package com.example.help_b.config;

import com.example.help_b.security.JwtAuthenticationTokenFilter;
import com.example.help_b.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource(name = "userDetailsServiceImpl")
    private UserDetailsServiceImpl userDetailsService;
    @Resource(name = "bCryptPasswordEncoder")
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Resource(name = "jwtAuthenticationEntryPoint")
    AuthenticationEntryPoint authenticationEntryPoint;
    @Resource(name = "authenticationAccessDeniedHandler")
    AccessDeniedHandler accessDeniedHandler;
    @Resource(name = "jwtAuthenticationTokenFilter")
    JwtAuthenticationTokenFilter authenticationTokenFilter;
    @Resource(name = "loginSuccessHandler")
    AuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource(name = "loginFailureHandler")
    AuthenticationFailureHandler authenticationFailureHandler;
    @Resource(name = "logoutSuccessHandler")
    LogoutSuccessHandler logoutSuccessHandler;

    /**
     * 除非想重写整个认证流程，否则不应该写
     * @param auth
     * @throws Exception
     */
//    @Bean
//    public AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManagerBean();
//    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.formLogin().loginPage("/login").successForwardUrl("/").failureUrl("/login?error")
                .successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler)
                .and().logout().logoutSuccessUrl("/").logoutSuccessHandler(logoutSuccessHandler).permitAll();
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().authorizeRequests().antMatchers("/publish","/personal/**","/publish/**").hasAuthority("ROLE_USER")
//                .and().authorizeRequests().antMatchers("/","/page/**","/login","/register","/logout","/question/**","/callback").permitAll()
                .and().authorizeRequests().antMatchers("/publish","/personal/**","/publish/**").hasAuthority("ROLE_USER")
                .and().authorizeRequests().antMatchers("/**").permitAll()

                .anyRequest().authenticated();
        http.headers().cacheControl();
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
        web.ignoring().antMatchers(
                "swagger-ui.html",
                "**/swagger-ui.html",
                "/favicon.ico",
                "/**/*.css",
                "/**/*.js",
                "/**/*.png",
                "/**/*.gif",
                "/swagger-resources/**",
                "/v2/**",
                "/**/*.ttf"
        );
        web.ignoring().antMatchers("/v2/api-docs",
                "/swagger-resources/configuration/ui",
                "/swagger-resources",
                "/swagger-resources/configuration/security",
                "/swagger-ui.html"
        );

    }
}
