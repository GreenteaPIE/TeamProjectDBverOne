package com.db.controller.action.freeboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.controller.action.Action;
import com.freeboard.dao.FreeBoardDAO;
import com.freeboard.dto.FreeBoardVO;




public class FreeBoardListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/freeboard/freeBoardList.jsp";
		FreeBoardDAO fbDao = FreeBoardDAO.getInstance();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		//List<FreeBoardVO> freeboardList = qbDao.selectAllFreeBoards();
		
				//---------------------------------------------page start
						int page = 1; //처음에는 무조건 1페이지가 실행되도록
						int limit = 10; //한 페이지에 보이는 최대 게시글 개수
						
						//값이 넘어오면 null이 아니고, 값을 선택한 적이 없으면 null → 여기 정해둔 대로 page값이 1.
						if(request.getParameter("page") != null) {
							page = Integer.parseInt(request.getParameter("page"));
						}
							
						//페이징을 위해 전체 레코드(글) 개수를 조회해줄 메소드를 DAO에서 가져와 사용할 것
						int listcount = fbDao.getListCount();
						
						//getBoardList: 현재 선택된 번호를 기준으로 10개의 레코드를 조회하는 메소드
						List<FreeBoardVO> boardList = fbDao.getBoardList(page, limit);
						
						int maxpage = (listcount %  limit) != 0 ? (listcount/limit) + 1 : (listcount/limit);
					      int startpage = ((int)((double)page/limit + 0.9)-1)*limit+1;
					      int endpage = startpage + limit -1;

						
						if(endpage > maxpage) {                                                                            
						    endpage = maxpage;                                                                               
						}    
								
						//---------------------------------------------page end-----
						
						//게시판정보를 VO객체의 리스트로 저장
						//List<BoardVO> boardList = bDao.selectAllBoards(); 여기서는 대신 bDao.getBoardList사용!
						//요청에 boardList속성으로 담아 보냄
						request.setAttribute("boardList", boardList);
						request.setAttribute("maxpage", maxpage);
						request.setAttribute("startpage", startpage);
						request.setAttribute("endpage", endpage);
						request.setAttribute("listcount", listcount);
						request.setAttribute("page", page);
						
						//freeBoardList.jsp로 요청 전달
				request.setAttribute("userid", userid);
			//	request.setAttribute("freeboardList", freeboardList);
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
				
			}
		}