package com.db.controller.action.qnaboard;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.controller.action.Action;
import com.qnaboard.dao.QnaBoardDAO;
import com.qnaboard.dto.QnaBoardVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class QnaBoardWrite implements Action{

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
        QnaBoardVO qbVo = new QnaBoardVO();
        qbVo.setUserid(userid);
        qbVo.setTitle(title);
        qbVo.setContent(content);
        qbVo.setImgurl(imgurl);
        QnaBoardDAO qbDao = QnaBoardDAO.getInstance();
        qbDao.insertQnaBoard(qbVo);
        new QnaBoardListAction().execute(request, response);
    }
}
