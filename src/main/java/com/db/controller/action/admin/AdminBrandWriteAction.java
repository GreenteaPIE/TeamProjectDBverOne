package com.db.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brand.dao.BrandDAO;
import com.brand.dto.BrandVO;
import com.db.controller.action.Action;

public class AdminBrandWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String bName = request.getParameter("bName");
		String imgUrl = request.getParameter("imgUrl");
		BrandVO bVo = new BrandVO();
		bVo.setbName(bName);
		bVo.setImgUrl(imgUrl);
		BrandDAO bDao = BrandDAO.getInstance();
		bDao.brandWrite(bVo);
		
		new AdminBrandListAction().execute(request, response);
	}

}
