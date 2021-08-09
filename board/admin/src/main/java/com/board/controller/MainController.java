package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.board.common.CoTopComponent;
import com.board.common.Constants;
import com.board.common.Url;
import com.board.common.Url.MAIN;
import com.board.dao.MainMapper;
import com.board.service.MainService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController extends CoTopComponent {

	@Autowired MainService mainService;
	@Autowired MainMapper mainMapper;
	
	//profile
	@Value("${spring.profile.active")
	private String profile;
	
	//메인화면
	@GetMapping(Url.MAIN.MAIN)
	public String main() {
		
		return Url.MAIN.MAIN_JSP;
	}
	
	//메인화면 리스트 ajax
	@GetMapping(Url.MAIN.MAIN_LIST_AJAX)
	public String mainListAjax(@RequestParam Map<String, Object> params
								,Pageable pageable
								,Model model
			) {
		
		model.addAttribute("boardList", mainService.selectBoardList(params, pageable, Integer.parseInt(params.get("size").toString())));
		model.addAttribute("resultDataTotal", mainMapper.selectBoardListCnt(params));
		
		return makePageDispatcherUrl(MAIN.MAIN_LIST_AJAX, MAIN._MAIN_AJAX_ROOT_PATH);
	}
	
	
	
	//글쓰기화면
	@GetMapping(Url.MAIN.MAIN_WRITE)
	public String write() {
		return Url.MAIN.MAIN_WRITE_JSP;
	}
	
	//글등록
	@ResponseBody
	@PostMapping(Url.MAIN.MAIN_WRITE)
	public Map<String, Object> writeSubmit(@RequestBody Map<String, Object> params) {
		log.info("params={}", params);
		mainMapper.insertBoard(params);
		
		return params;
	}
	
	//수정화면
	@GetMapping(Url.MAIN.MAIN_UPDATE+"/{boardIdx}")
	public String update(@PathVariable("boardIdx") String boardIdx, Model model) {
		log.info("boardIdx={}", boardIdx);
		model.addAttribute("boardInfo", mainMapper.selectBoard(boardIdx));
		model.addAttribute("boardIdx", boardIdx);
		
		int viewCount = (int) mainMapper.selectBoard(boardIdx).get("viewCount");
		
		log.info("viewCount={}", viewCount);
		
		Map<String, Object> params = new HashMap<String, Object>();
		viewCount++;
		params.put("boardIdx", boardIdx);
		params.put("viewCount", viewCount);
		
 		//조회수 업데이트
		mainMapper.updateViewCount(params);
		
		return Url.MAIN.MAIN_UPDATE_JSP;
	}
	
	//글수정
	@ResponseBody
	@PostMapping(Url.MAIN.MAIN_UPDATE)
	public Map<String, Object> updateSubmit(@RequestBody Map<String, Object> params) {
		log.info("params={}", params);
		mainMapper.updateBoard(params);
		return params;
	}
	
	//server health check
	@RequestMapping(value= { Constants.HEALTH_CHECK_URL }, produces=MediaType.TEXT_HTML_VALUE)
	   public void healthCheck( HttpServletRequest req, HttpServletResponse res ) throws IOException {

	      String ip = req.getHeader("X-FORWARDED-FOR");
	      if (ip == null) ip = req.getRemoteAddr();

	      PrintWriter pw = res.getWriter();
	      pw.write(" - Active Profile : " + profile + "\n");
	      pw.write(" - Client IP : " + ip);
	      pw.close();
	   }
	
}