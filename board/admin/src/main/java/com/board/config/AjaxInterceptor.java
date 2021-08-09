package com.board.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class AjaxInterceptor.
 * @author Think-Tree Inc.
 * @version 1.0.0
 */
@Component
@Slf4j
public class AjaxInterceptor implements HandlerInterceptor {

   /* (non-Javadoc)
    * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
    */
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
         throws Exception {
      boolean result = true;
      //tmptmp
//      if(false) {
         try {
            SecurityContext sc = SecurityContextHolder.getContext();
            Authentication auth = sc.getAuthentication();
            if(auth == null || auth.getPrincipal() == null || "anonymousUser".equals(auth.getPrincipal())) {
               //Ajax 콜인지 아닌지 판단
               if(isAjaxRequest(request)){
                  response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                  return false;
               }
            }
         } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage());
            return false;
         }
//      }
      return result;
   }

   /**
    * Checks if is ajax request.
    *
    * @param req the req
    * @return true, if is ajax request
    */
   private boolean isAjaxRequest(HttpServletRequest req) {
      /*
      // 사용자 인증이 필요 없는 URL 체크
      String requestUri = RequestUtil.getRequestURI(req);
      if(requestUri.startsWith("/auth/")) {
         return false;
      }
      */
      String ajaxHeader = "AJAX";
      return req.getHeader(ajaxHeader) != null && req.getHeader(ajaxHeader).equals(Boolean.TRUE.toString());
   }

   /* (non-Javadoc)
    * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
    */
   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
         ModelAndView modelAndView) throws Exception {
   }

   /* (non-Javadoc)
    * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
    */
   @Override
   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
         throws Exception {
      // TODO Auto-generated method stub
   }
}