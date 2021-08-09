package com.board.common;

import java.io.Writer;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import lombok.Setter;

/**
 * 
 */
@Setter
public class Paginator extends SimpleTagSupport {
   
   // ******************************************************************
   // *                                           페이지 네비게이터 템플릿 (Debult)                                     *
   // ******************************************************************
    private String MainSB   ="";
    private String FistB   ="<li><a class='#$class' href='javascript:;' onclick='#$goPage(##);'>#$title</a></li>";
    private String PrevB   ="<li><a class='#$class' href='javascript:;' onclick='#$goPage(##);'>#$title</a></li>";
    private String CurB      ="<li><a class='#$class' href='javascript:;'>#$title</a></li>";
    private String OthB      ="<li><a class='#$class' href='javascript:;' onclick='#$goPage(##);'>#$title</a></li>";
    private String NextB   ="<li><a class='#$class' href='javascript:;' onclick='#$goPage(##);'>#$title</a><li>";
    private String LastB   ="<li><a class='#$class' href='javascript:;' onclick='#$goPage(##);'>#$title</a></li>";
    private String MainEB   ="";
    
   String goPageScript = "goPage";                     // 이동 이벤트 Script Function Name (해당 스크립트의 인자는 페이지 번호만 받아야함, 추가 검색조건은 함수내에서 처리할 것)   (설정은 JSTL 속성으로 입력)
    int curPage;                  // 현재 페이지                                 (설정은 JSTL 속성으로 입력)
    int totPages;                  // 전체 페이지                                 (설정은 JSTL 속성으로 입력)
    int blockSize = 10;               // 블록 크기                                    (설정은 JSTL 속성으로 입력)
    int skipSize = 1;               // 이전/다음 선택시 이동할 페이지 갯수            (설정은 JSTL 속성으로 입력)
    
    // Options
    boolean goFirstUse = false;                     // 처음 페이지 사용 여부      (설정은 JSTL 속성으로 입력)
    boolean goLastUse = false;                     // 마지막 페이지 사용 여부      (설정은 JSTL 속성으로 입력)
    
    // CSS Class
    private String paginatorBlockClass = "";         // paginator 전체 메인 블록   (설정은 JSTL 속성으로 입력)
    private String goFirstClass = "";                  // 처음 페이지               (설정은 JSTL 속성으로 입력)
    private String prevPageClass = "prev";               // 이전 페이지               (설정은 JSTL 속성으로 입력)
    private String curPageClass = "on";               // 현재 페이지               (설정은 JSTL 속성으로 입력)
    private String defaultPageClass = "";            // 다른 페이지               (설정은 JSTL 속성으로 입력)
    private String nextPageClass = "next";               // 다음 페이지                (설정은 JSTL 속성으로 입력)
    private String goLastClass = "";                  // 마지막 페이지               (설정은 JSTL 속성으로 입력)
    
    // Label
    private String goFirstLabel = "처음";         // 처음 페이지 타이틀            (설정은 JSTL 속성으로 입력)
    private String prevPageLabel = "이전";         // 이전 페이지 타이틀            (설정은 JSTL 속성으로 입력)
    private String nextPageLabel = "다음";         // 다음 페이지 타이틀            (설정은 JSTL 속성으로 입력)
    private String goLastLabel = "마지막";         // 마지막 페이지 타이틀         (설정은 JSTL 속성으로 입력)
    
    private Writer getWriter() {
        JspWriter out = getJspContext().getOut();
        return out;
    }
 
    @Override
    public void doTag() throws JspException {
        Writer out = getWriter();
        
        // Spring pageable의 page 번호가 0부터 시작하기 때문에 +1하고
        // 실제로 페이지 링크를 생성하는 부분에서 다시 -1 한다.
        curPage ++;
        
        boolean lastPage = curPage == totPages;
        int pgStart = Math.max(curPage - blockSize / 2, 1);
        int pgEnd = pgStart + blockSize;
        if (pgEnd > totPages + 1) {
            int diff = pgEnd - totPages;
            pgStart -= diff - 1;
            if (pgStart < 1)
                pgStart = 1;
            pgEnd = totPages + 1;
        }
        
        if(totPages > 0) {
            try {
                out.write(MainSB.replace("#$class", paginatorBlockClass));
                
                // 처음
                if (goFirstUse) out.write( replacePageStr(FistB, goFirstClass, goPageScript, 1, goFirstLabel) );
     
                // 이전
                if (curPage > 1){
                   int moveSize = curPage-skipSize;
                   if (moveSize < 1) moveSize = 1;
                   out.write( replacePageStr(PrevB, prevPageClass, goPageScript, moveSize, prevPageLabel) );
                }
     
                
                for (int i = pgStart; i < pgEnd; i++) {
                   // 현재 페이지
                    if (i == curPage) out.write( replacePageStr(CurB, curPageClass, goPageScript, curPage, String.valueOf(curPage)) );
                    // 기타 페이지
                    else out.write( replacePageStr(OthB, defaultPageClass, goPageScript, i, String.valueOf(i)) );
                }
                
     
                // 다음
                if (!lastPage){
                   int moveSize = curPage + skipSize;
                   if (moveSize > totPages) moveSize = totPages;
                   out.write( replacePageStr(NextB, nextPageClass, goPageScript, moveSize, nextPageLabel) );
                }
                
                // 마지막
                if (goLastUse) out.write( replacePageStr(LastB, goLastClass, goPageScript, totPages, goLastLabel) );
     
               out.write(MainEB);
     
            } catch (java.io.IOException ex) {
                throw new JspException("Error in Paginator tag", ex);
            }           
        }

    }

   private String replacePageStr(String srcStr, String classStr, String scriptStr, int pageNum, String labelStr) {
      pageNum --;
      return srcStr.replace("#$class", classStr).replace("#$goPage", scriptStr).replace("##", String.valueOf(pageNum)).replace("#$title", labelStr);
   }
   
   
}
