package com.db.controller.action.freeboard;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.controller.action.Action;
import com.freeboard.dao.FreeBoardDAO;
import com.freeboard.dto.FreeBoardVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FreeBoardWrite implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        ServletContext context = request.getServletContext();
        HttpSession session = request.getSession();
        String path = context.getRealPath("images");
        String encType = "UTF-8";
        int sizeLimit = 20 * 1024 * 1024;
        MultipartRequest multi = new MultipartRequest(request, path, sizeLimit,
                encType, new DefaultFileRenamePolicy());
        String userid = multi.getParameter("userid");
        String title = multi.getParameter("title");
        String content = multi.getParameter("content");
        String imgurl = multi.getFilesystemName("imgurl");
        FreeBoardVO fbVo = new FreeBoardVO();
        fbVo.setUserid(userid);
        fbVo.setTitle(title);
        fbVo.setContent(content);
        fbVo.setImgurl(imgurl);
        FreeBoardDAO fbDao = FreeBoardDAO.getInstance();
        fbDao.insertFreeBoard(fbVo);
        new FreeBoardListAction().execute(request, response);
    }
}
