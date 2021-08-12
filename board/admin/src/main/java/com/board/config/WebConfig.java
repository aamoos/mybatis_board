package com.board.config;

import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.resource.PathResourceResolver;
import com.board.common.Constants;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

/**
 * The Class WebConfig.
 */
@Configuration
@EnableWebMvc
@EnableCaching
@ComponentScan
public class WebConfig implements WebMvcConfigurer {


   /**
    * 인터셉터 관리.
    *
    * @param registry the registry
    */
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
//      registry.addInterceptor(new LogInterceptor()); // 로그 인터셉터
      registry.addInterceptor(localeChangeInterceptor()); // 로케일 변경 인터셉터
      registry.addInterceptor(new AjaxInterceptor()).excludePathPatterns("/error", "/error/**","/viewer","/viewer/**", "/css/**", "/js/**", "/images/**");
   }

   /**
    * Resource 핸들러.
    *
    * @param registry the registry
    */
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler( Constants.STATIC_RESOURCES_URL_PATTERNS)
      .addResourceLocations(Constants.CLASSPATH_RESOURCE_LOCATIONS)
      .setCachePeriod(60*60*24*7)// 60*60*24*7 => 일주일
      .resourceChain(true)
      .addResolver(new PathResourceResolver());
   }
   
   /**
    * Adds the argument resolvers.
    *
    * @param argumentResolvers the argument resolvers
    */
   @Override
   public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setFallbackPageable(PageRequest.of(Constants.DEFAULT_PAGE_NUMBER, Constants.DEFAULT_PAGE_SIZE));
        argumentResolvers.add(resolver);
        WebMvcConfigurer.super.addArgumentResolvers(argumentResolvers);
   }
   
   /**
    * Active profile.
    *
    * @param profile the profile
    * @return the string
    */
   @Bean
   @Value("${spring.profiles.active}")
   public String activeProfile(String profile) {
	  System.out.println("profile : " + profile);
      return profile;
   }
   
   /**
    * Locale Resolver.
    *
    * @return the locale resolver
    */
   @Bean
   public LocaleResolver localeResolver() {
//      SessionLocaleResolver slr = new SessionLocaleResolver();
//      slr.setDefaultLocale(Locale.KOREAN);
      CookieLocaleResolver slr = new CookieLocaleResolver();
      slr.setDefaultLocale(Locale.KOREAN);
      slr.setCookieName(Constants.APP_LOCALE_COOKIE);
      return slr;
   }

   /**
    * Locale Change Interceptor.
    *
    * @return the locale change interceptor
    */
   @Bean
   public LocaleChangeInterceptor localeChangeInterceptor() {
      LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
      lci.setParamName("lang");
      return lci;
   }
   
   
    @Bean
    public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean() {
        FilterRegistrationBean<XssEscapeServletFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new XssEscapeServletFilter());
        filterRegistration.setOrder(1);
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

//   /**
//    * Message source.
//    *
//    * @return the message source
//    */
//   @Bean
//   public MessageSource messageSource() {
//      ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//      // WEB-INF 밑에 해당 폴더에서 properties를 찾는다.
//      messageSource.setBasename("message/messages");
//      messageSource.setDefaultEncoding("UTF-8");
//      return messageSource;
//   }

}