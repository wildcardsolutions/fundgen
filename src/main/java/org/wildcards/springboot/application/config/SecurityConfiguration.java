package org.wildcards.springboot.application.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;
import org.wildcards.springboot.infrastructure.security.filter.ResourceAccessFilter;
import org.wildcards.springboot.infrastructure.security.filter.UserAuthenticationFilter;
import org.wildcards.springboot.infrastructure.security.service.UserAuthenticationProvider;

/**
 * 
 * @author jojo
 *
 */
@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//@Order(0)
//@Order(1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationProvider userAuthenticationProvider;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	/**
	 * 
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
				.antMatchers("/resources/**");
	}
	
	/**
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http
			.httpBasic()
		.and()
			.logout()
		.and()
			.authorizeRequests()
				.antMatchers("/").permitAll()
			.anyRequest().authenticated();
		
//		http
//			.csrf().disable();
//		
//		http.addFilterBefore(new ResourceAccessFilter(), LogoutFilter.class);
		
//		http
//        	.authorizeRequests().anyRequest().authenticated();
//		
//		http
//	        .formLogin().failureUrl("/login?error")
//	        .defaultSuccessUrl("/")
//	        .loginPage("/login")
//	        .permitAll()
//	        .and()
//	        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
//	        .permitAll();
	

//        http.authorizeRequests()
//            .antMatchers("/", "/default", "/resources/**", "/login")
//            .permitAll()
//            .anyRequest()
//            .authenticated();
//
//		 http.formLogin()
//        	.usernameParameter("username")
//        	.passwordParameter("password")
//            .loginPage("/login")
//            .failureUrl("/login?error")
//            .defaultSuccessUrl("/default")
//            .permitAll();
		 
//            .and()
//        .logout()
//        	.logoutUrl("/logout")
//        	.logoutSuccessUrl("/")
//            .permitAll();
//        
//		http.addFilterBefore(new UserAuthenticationFilter(), BasicAuthenticationFilter.class);
//		
//		http.authorizeRequests()
//        	.antMatchers("/").permitAll()
//        	.antMatchers("").authenticated();
//			.antMatchers("").authenticated();
//		
//		http
//        	.formLogin()
//        	.failureUrl("/login?error")
//        	.defaultSuccessUrl("/default")
//        	.loginPage("/login")
//        	.permitAll()
//        	.and()
//        	.logout()
//        	.logoutUrl("/logout")
//            .logoutSuccessUrl("/")
//            .permitAll();
		
//		http.httpBasic().and().authorizeRequests()
//				.antMatchers("/index.html", "/").permitAll().anyRequest()
//				.authenticated().and().csrf()
//				.csrfTokenRepository(csrfTokenRepository()).and()
//				.addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
	}
	
	
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(userAuthenticationProvider).eraseCredentials(false);
//    	//auth.userDetailsService(userDetailsService);
//    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService);
    }
    
    
	
	
	private Filter csrfHeaderFilter() {
		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(
					HttpServletRequest request, 
					HttpServletResponse response, 
					FilterChain filterChain) throws ServletException, IOException {
				
				CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
				if (csrf != null) {
					Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
					String token = csrf.getToken();
					if (cookie == null || token != null && !token.equals(cookie.getValue())) {
						cookie = new Cookie("XSRF-TOKEN", token);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
				}
				
				filterChain.doFilter(request, response);
			}

		};
	}
	
	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

}