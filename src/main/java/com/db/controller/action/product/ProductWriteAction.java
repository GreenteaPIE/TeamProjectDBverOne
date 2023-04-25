package com.db.controller.action.product;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.controller.action.Action;
import com.db.controller.action.admin.ProductListAction;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.product.dao.ProductDAO;
import com.product.dto.ProductVO;

public class ProductWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		String path = context.getRealPath("images");
		String encType = "UTF-8";
		int sizeLimit = 20 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());

		String pGender = multi.getParameter("pGender");
		String bName = multi.getParameter("bName");
		int kind = Integer.parseInt(multi.getParameter("kind"));
		String pName = multi.getParameter("pName");
		String imgUrl = multi.getFilesystemName("imgUrl");
		String pSize = multi.getParameter("pSize");
		int price = Integer.parseInt(multi.getParameter("price"));
		int balance = Integer.parseInt(multi.getParameter("balance"));
		String explain = multi.getParameter("explain");

		ProductVO pVo = new ProductVO();
		pVo.setpGender(Integer.parseInt(pGender));
		pVo.setbName(bName);
		pVo.setKind(kind);
		pVo.setpName(pName);
		pVo.setImgUrl(imgUrl);
		pVo.setpSize(pSize);
		pVo.setPrice(price);
		pVo.setBalance(balance);
		pVo.setExplain(explain);

		ProductDAO pDao = ProductDAO.getInstance();
		pDao.insertProduct(pVo);

		System.out.println("pVo: " + pVo);

		new ProductListAction().execute(request, response);

	}

}
