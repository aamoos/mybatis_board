package com.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import com.board.common.Constants;

@Configuration
public class TilesConfig {
	
	 @Bean
	 public TilesConfigurer tilesConfigurer() {
		 final TilesConfigurer configurer = new TilesConfigurer();
		 configurer.setDefinitions(Constants.TILES_LAYOUT_XML_PATH);
		 configurer.setCheckRefresh(true);
		 return configurer;
	 }

	   /**
	    * UrlBased 뷰 리졸버.
	    *
	    * @return urlViewResolver
	    */
	   @Bean
	   public UrlBasedViewResolver urlViewResolver() {
	      UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
	      tilesViewResolver.setViewClass(TilesView.class);
	      return tilesViewResolver;
	   }
}