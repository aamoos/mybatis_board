package com.board.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.board.dao.FileMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("fileService")
public class FileService {

	@Autowired Environment env;
	@Autowired FileService fileService;
	@Autowired FileMapper fileMapper;
	
	private final String UPLOAD_FILE_PATH = "common.public_upload_local_path";
	
	/** 파일 업로드 */
	public Map<String, Object> uploadFile(HttpServletRequest request, List<MultipartFile> multipartFile) throws IOException{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		//파일 시퀀스 리스트
		List<String> fileIds = new ArrayList<String>();
	
		result.put("result", "FAIL");
		
		String _filePath = String.valueOf(request.getParameter("filePath")).equals("null") ? env.getProperty(UPLOAD_FILE_PATH) : env.getProperty(UPLOAD_FILE_PATH)+String.valueOf(request.getParameter("filePath")+"/");
		
		try {
			// 파일이 있을때 탄다.
			if(multipartFile.size() > 0 && !multipartFile.get(0).getOriginalFilename().equals("")) {
				
				for(MultipartFile file : multipartFile) {
					
					String originalFileName = file.getOriginalFilename();	//오리지날 파일명
					String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
					String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
					
					File targetFile = new File(_filePath + savedFileName);	
					
					//파일 저장후 db insert
					result.put("pyscPath", _filePath);
					result.put("pyscNm", savedFileName);
					result.put("origNm", originalFileName);
					result.put("fileExt", extension);
					result.put("contentType", file.getContentType());
					result.put("fileSize", file.getSize());
					
					//파일 insert
					fileService.insertFile(result);
					log.info("fileId={}", result.get("fileId"));
					
					//배열에 담기
					fileIds.add(String.valueOf(result.get("fileId")));
					
					try {
						InputStream fileStream = file.getInputStream();
						FileUtils.copyInputStreamToFile(fileStream, targetFile); //파일 저장
						
					} catch (Exception e) {
						//파일삭제
						FileUtils.deleteQuietly(targetFile);	//저장된 현재 파일 삭제
						e.printStackTrace();
						result.put("result", "FAIL");
						break;
					}
				}
				
				result.put("fileIdxs", fileIds.toString());
				result.put("result", "OK");
				
			}
			// 파일 아무것도 첨부 안했을때 탄다.(게시판일때, 업로드 없이 글을 등록하는경우)
			else {
				result.put("result", "OK");
			}
		}catch(Exception e){
			e.printStackTrace();
			result.put("result", "FAIL");
		}
		
		return result;
	}
	
	/** 파일 저장 db */
	@Transactional
	public int insertFile(Map<String, Object> params) {
		return fileMapper.insertFile(params);
	}
	
}
