package com.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

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

	 @Bean
	 public TilesViewResolver tilesViewResolver() {
		 final TilesViewResolver tilesViewResolver = new TilesViewResolver();
		 tilesViewResolver.setViewClass(TilesView.class);
		 return tilesViewResolver;
	 }
}