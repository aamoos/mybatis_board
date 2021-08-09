package com.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.board.common.Constants;
import com.board.common.Url;

@Configuration
@EnableWebSecurity
@ComponentScan(value = Constants.APP_DEFAULT_PACKAGE_NAME)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   @Autowired private LoginSuccessHandler loginSuccessHandler;
   @Autowired private LoginFailureHandler loginFailureHandler;
//   @Autowired private CustomerUserDetailsService customUserDetailsService;

   /**
    * Configure.
    *
    * @param auth the auth
    * @throws Exception the exception
    */
   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//      auth.userDetailsService(customUserDetailsService);

      auth.authenticationProvider(new AdminAuthenticationProvider());
      super.configure(auth);
   }

   /**
    * http 요청 검사.
    *
    * @param http the http
    * @throws Exception the exception
    */
   @Override
   protected void configure(HttpSecurity http) throws Exception {

      http
      // replay 어택을 막기 위한 csrf 토큰의 생성을 비활성화(disabled) 처리
      .csrf().disable()
      // pdf viewer 에서 'X-Frame-Options' to 'DENY' 대응
      .headers().frameOptions().disable().and()
      // 요청에 대한 권한 매핑
      .authorizeRequests()
         .antMatchers( "/auth/**" ).permitAll()                // 패스워드찾기,회원가입
         .antMatchers( "/" ).permitAll()
         .antMatchers( "/**/ajax/**" ).permitAll()
         .antMatchers( "/board/**" ).permitAll()
         .antMatchers( "/resource/**/images/**" ).permitAll()   // image
         .anyRequest().authenticated()                     // 모든 요청에 대해 권한 확인이 필요
//         .anyRequest().permitAll()
      .and()
      // 로그인 화면 설정
      .formLogin()
         .permitAll()
         .loginPage( Url.AUTH.LOGIN )
         .loginProcessingUrl( Url.AUTH.LOGIN_PROC )
         .successHandler( loginSuccessHandler )
         .failureHandler( loginFailureHandler )
         .usernameParameter( USERNAME_PARAM )
         .passwordParameter( PASSWORD_PARAM )
      .and()
      .logout()
         .logoutUrl( Url.AUTH.LOGOUT_PROC )
         .invalidateHttpSession(true)
         .deleteCookies("JSESSIONID")
      .and()
      // 세션 관리
      .sessionManagement()
         .maximumSessions(200) /* session 허용 갯수 */
         .expiredUrl(Url.AUTH.LOGIN) /* session 만료시 이동 페이지*/
         .sessionRegistry(sessionRegistry())                     // 세션을 목록에 담아둠
         .maxSessionsPreventsLogin(true) /* 동일한 사용자 로그인시 x, false 일 경우 기존 사용자 */
   ;
   }

   /**
    * web요청 검사.
    *
    * @param web the web
    * @throws Exception the exception
    */
   @Override
   public void configure(WebSecurity web) throws Exception {
      // Security Debug
//      web.debug(true);

      web
         .ignoring()
            // static 리소스 경로는 webSecurity 검사 제외
            .antMatchers( Constants.STATIC_RESOURCES_URL_PATTERNS )
            .antMatchers( Constants.HEALTH_CHECK_URL )
            .antMatchers(HttpMethod.GET, "/exception/**")
      ;
      super.configure(web);
   }

   /**
    * PasswordEncoder.
    *
    * @return the password encoder
    */
   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   public SessionRegistry sessionRegistry() {
      return new SpringSecuritySessionRegistImpl();
   }

   /**
    * AuthenticationProvider
    * <br>관리자의 계정정보를 통해 로그인 인증을 처리합니다.
    *
    * @return the authentication provider
    * @see kr.mediaflow.fdwm.config.DatabaseConfig
    */
//   @Bean
//   public AuthenticationProvider daoAuthenticationProvider() {
//      DaoAuthenticationProvider impl = new DaoAuthenticationProvider();
//      impl.setUserDetailsService(customUserDetailsService);
//      impl.setPasswordEncoder(new BCryptPasswordEncoder());
//      impl.setHideUserNotFoundExceptions(false);
//      return impl;
//   }

   /**  관리자 아이디 파라미터 이름 : {@value #USERNAME_PARAM}. */
   public static final String USERNAME_PARAM = "un";

   /**  관리자 비밀번호 파라미터 이름 : {@value #PASSWORD_PARAK}. */
   public static final String PASSWORD_PARAM = "up";
}