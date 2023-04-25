package com.db.controller;

import com.db.controller.action.Action;
import com.db.controller.action.admin.AddAuctionAction;
import com.db.controller.action.admin.AdminAuctionFormAction1;
import com.db.controller.action.admin.AdminAuctionPageAction;
import com.db.controller.action.admin.AdminBrandDeleteAction;
import com.db.controller.action.admin.AdminBrandListAction;
import com.db.controller.action.admin.AdminBrandWriteAction;
import com.db.controller.action.admin.AdminBrandWriteFormAction;
import com.db.controller.action.admin.AdminPageAction;
import com.db.controller.action.admin.AuctionBrandListAction;
import com.db.controller.action.admin.AuctionBrandProductDetailAction;
import com.db.controller.action.admin.BoardManagement;
import com.db.controller.action.admin.FreeBoardDetailPageAction;
import com.db.controller.action.admin.FreeBoardManagementDeleteAction;
import com.db.controller.action.admin.FreeBoardManagementPageAction;
import com.db.controller.action.admin.ProductDeleteAction;
import com.db.controller.action.admin.ProductEditAction;
import com.db.controller.action.admin.ProductEditCompleteAction;
import com.db.controller.action.admin.ProductListAction;
import com.db.controller.action.admin.ProductManagementPageAction;
import com.db.controller.action.admin.ProductViewAction;
import com.db.controller.action.admin.SalesManagementPageAction;
import com.db.controller.action.admin.UpdateUserAction;
import com.db.controller.action.admin.UserEditCompleteAction;
import com.db.controller.action.admin.UserManagementDeleteAction;
import com.db.controller.action.admin.UserManagementEditPageAction;
import com.db.controller.action.admin.UserManagementPageAction;
import com.db.controller.action.admin.qnaBoardManagementDelete;
import com.db.controller.action.admin.qnaBoardManagementPageAction;
import com.db.controller.action.auction.AuctionBuyPageAction;
import com.db.controller.action.auction.AuctionCompleteAction;
import com.db.controller.action.auction.AuctionDealAction;
import com.db.controller.action.auction.AuctionDetailAction;
import com.db.controller.action.auction.AuctionViewAction;
import com.db.controller.action.categorieslist.brandBcollectionList;
import com.db.controller.action.categorieslist.brandBottomList;
import com.db.controller.action.categorieslist.brandList;
import com.db.controller.action.categorieslist.brandProductDetail;
import com.db.controller.action.categorieslist.brandTopList;
import com.db.controller.action.freeboard.FreeBoardDelete;
import com.db.controller.action.freeboard.FreeBoardListAction;
import com.db.controller.action.freeboard.FreeBoardUpdate;
import com.db.controller.action.freeboard.FreeBoardUpdateAction;
import com.db.controller.action.freeboard.FreeBoardView;
import com.db.controller.action.freeboard.FreeBoardWrite;
import com.db.controller.action.freeboard.FreeBoardWriteForm;
import com.db.controller.action.product.AddCartAction;
import com.db.controller.action.product.CartListAction;
import com.db.controller.action.product.CheckoutAction;
import com.db.controller.action.product.DeleteCartAction;
import com.db.controller.action.product.HotDeal;
import com.db.controller.action.product.HotDealBuyPage;
import com.db.controller.action.product.HotDealOrderInsert;
import com.db.controller.action.product.HotDealOrderList;
import com.db.controller.action.product.OrderInsertAction;
import com.db.controller.action.product.OrderListAction;
import com.db.controller.action.product.ProductWriteAction;
import com.db.controller.action.product.ProductWriteFormAction;
import com.db.controller.action.product.UserCart;
import com.db.controller.action.purchased.PurchasedDetailAction;
import com.db.controller.action.purchased.PurchasedListAction;
import com.db.controller.action.qnaboard.QnaBoardDelete;
import com.db.controller.action.qnaboard.QnaBoardDetailPageAction;
import com.db.controller.action.qnaboard.QnaBoardListAction;
import com.db.controller.action.qnaboard.QnaBoardManagementDeleteAction;
import com.db.controller.action.qnaboard.QnaBoardManagementPageAction;
import com.db.controller.action.qnaboard.QnaBoardUpdate;
import com.db.controller.action.qnaboard.QnaBoardUpdateAction;
import com.db.controller.action.qnaboard.QnaBoardView;
import com.db.controller.action.qnaboard.QnaBoardWrite;
import com.db.controller.action.qnaboard.QnaBoardWriteForm;
import com.db.controller.action.search.searchBrandProductDetail;
import com.db.controller.action.search.searchProduct;
import com.db.controller.action.shopuser.DeleteUserAction;
import com.db.controller.action.shopuser.IdCheckFormAction;
import com.db.controller.action.shopuser.JoinAction;
import com.db.controller.action.shopuser.JoinFormAction;
import com.db.controller.action.shopuser.MyPageAction;
import com.db.controller.action.shopuser.MyWriteAction;
import com.db.controller.action.shopuser.ShopUserLoginAction;
import com.db.controller.action.shopuser.ShopUserLoginForm;
import com.db.controller.action.shopuser.ShopUserLogout;
import com.db.controller.action.shopuser.UserInfoAction;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();

	private ActionFactory() {
		super();
	}

	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		Action action = null;
		System.out.println("ActionFactory :" + command);

		/* 자유게시판 */
		if (command.equals("free_board_list")) {
			action = new FreeBoardListAction(); // 자유 게시판 리스트
		} else if (command.equals("free_board_write_form")) {
			action = new FreeBoardWriteForm(); // 자유 게시판 작성 폼으로 이동
		} else if (command.equals("free_board_write")) {
			action = new FreeBoardWrite(); // 자유 게시판 작성 폼에서 write 메소드 실행
		} else if (command.equals("free_board_view")) {
			action = new FreeBoardView(); // 자유 게시판 상세보기
		} else if (command.equals("free_board_update")) {
			action = new FreeBoardUpdate(); // 자유 게시판 수정
		} else if (command.equals("freeboard_delete")) {
			action = new FreeBoardDelete(); // 자유 게시판 삭제

			/* Q&A 게시판 */

		} else if (command.equals("qna_board_list")) {
			action = new QnaBoardListAction(); // Qna 게시판 리스트
		} else if (command.equals("qna_board_write_form")) {
			action = new QnaBoardWriteForm(); // Qna 게시판 작성 폼으로 이동
		} else if (command.equals("qna_board_write")) {
			action = new QnaBoardWrite(); // Qna 게시판 작성 폼에서 write 메소드 실행
		} else if (command.equals("qna_board_view")) {
			action = new QnaBoardView(); // Qna 게시판 상세보기
		} else if (command.equals("qnaboard_update_form")) {
			action = new QnaBoardUpdateAction(); // Qna 게시판 수정 폼이동
		} else if (command.equals("qna_board_update")) {
			action = new QnaBoardUpdate(); // Qna 게시판 수정
		} else if (command.equals("qnaboard_delete")) {
			action = new QnaBoardDelete(); // Qna 게시판 삭제
		} else if (command.equals("qna_board_management")) { // Qna 게시판 관리(리스트)페이지
			action = new QnaBoardManagementPageAction();
		} else if (command.equals("qna_board_detail")) { // Qna 게시글 클릭시 디테일 페이지
			action = new QnaBoardDetailPageAction();
		} else if (command.equals("qna_board_management_delete")) { // Qna 게시판 삭제기능
			action = new QnaBoardManagementDeleteAction();

			/* 유저 페이지 */

		} else if (command.equals("shopuser_loin")) {
			action = new ShopUserLoginForm(); // 세션에 로그인 유무 확인
		} else if (command.equals("shopuser_login_form")) {
			action = new ShopUserLoginAction(); // 샵유저 로그인 폼
		} else if (command.equals("shopuser_logout")) {
			action = new ShopUserLogout(); // 샵유저 로그아웃
		} else if (command.equals("freeboard_update_form")) {
			action = new FreeBoardUpdateAction(); // 자유 게시판 수정 폼이동
		} else if (command.equals("user_join_form")) {
			action = new JoinFormAction(); // 회원가입 폼 이동
		} else if (command.equals("join")) {
			action = new JoinAction(); // 회원가입
		} else if (command.equals("id_check_form")) {
			action = new IdCheckFormAction(); // id중복체크
		} else if (command.equals("my_page")) {
			action = new MyPageAction(); // 마이페이지
		} else if (command.equals("update_user")) {
			action = new UpdateUserAction(); // 마이페이지 정보 수정
		} else if (command.equals("delete_user")) {
			action = new DeleteUserAction(); // 마이페이지 회원 탈퇴
		} else if (command.equals("user_info")) {
			action = new UserInfoAction(); // 회원 정보 조회
		} else if (command.equals("My_Write")) {
			action = new MyWriteAction();// 내가쓴글 목록 4.17

			/* 관리자 */

		} else if (command.equals("admin_page")) { // 관리자 메인 페이지
			action = new AdminPageAction();
		} else if (command.equals("product_management")) { // 제품 관리(리스트)페이지(*서비스 준비중*)
			action = new ProductManagementPageAction();
		} else if (command.equals("user_management")) { // 유저 관리(리스트)페이지
			action = new UserManagementPageAction();
		} else if (command.equals("free_board_management")) { // 게시판 관리(리스트)페이지
			action = new FreeBoardManagementPageAction();
		} else if (command.equals("free_board_detail")) { // 게시글 클릭시 디테일 페이지
			action = new FreeBoardDetailPageAction();
		} else if (command.equals("free_board_management_delete")) { // 게시판 삭제기능
			action = new FreeBoardManagementDeleteAction();
		} else if (command.equals("sales_management")) { // 총매출 관리(*서비스 준비중*)
			action = new SalesManagementPageAction();
		} else if (command.equals("user_management_edit")) { // 유저 수정창 액션
			action = new UserManagementEditPageAction();
		} else if (command.equals("user_edit_complete")) { // 유저 수정완료후 창 닫아주는 액션
			action = new UserEditCompleteAction();
		} else if (command.equals("user_management_delete")) { // 유저 삭제 액션
			action = new UserManagementDeleteAction();
		} else if (command.equals("product_management")) { // 제품 관리페이지 *2023-04-13
			action = new ProductManagementPageAction();
		} else if (command.equals("admin_brand_list")) {
			action = new AdminBrandListAction(); // 브랜드 리스트 관리 *추가: 2023-04-13
		} else if (command.equals("brand_delete")) {
			action = new AdminBrandDeleteAction(); // 브랜드 삭제 *추가: 2023-04-13
		} else if (command.equals("product_edit")) {
			action = new ProductEditAction(); // 제품 편집 *추가: 2023-04-13
		} else if (command.equals("product_edit_complete")) {
			action = new ProductEditCompleteAction(); // 제품 편집완료 액션 *추가: 2023-04-13
		} else if (command.equals("product_delete")) {
			action = new ProductDeleteAction(); // 제품 삭제 액션 *추가: 2023-04-14
		} else if (command.equals("admin_brand_write_form")) {
			action = new AdminBrandWriteFormAction(); // 브랜드 추가 페이지 *추가: 2023-04-14 17:19
		} else if (command.equals("admin_brand_write")) {
			action = new AdminBrandWriteAction(); // 브랜드 추가 페이지 *추가: 2023-04-14 17:19
		} else if (command.equals("board_management")) {
			action = new BoardManagement(); // 통합 게시판 관리 4.17
		} else if (command.equals("qna_board_management")) {
			action = new qnaBoardManagementPageAction(); // QnA 게시판 관리 페이지
		} else if (command.equals("qna_board_management_delete")) {
			action = new qnaBoardManagementDelete(); // qna 게시판 삭제
		
			/* 관리자 옥션 페이지 */
		} else if (command.equals("auction")) { // 경매 관리 2023-04-17 이름 수정됨
			action = new AdminAuctionPageAction();
		} else if (command.equals("admin_auction_form1")) {
			action = new AdminAuctionFormAction1(); // 옥션 관리 *추가 2023-04-17
		} else if (command.equals("AUCTION_BRAND_LIST")) {
			action = new AuctionBrandListAction(); // 옥션 관리 *추가 2023-04-18
		} else if (command.equals("auction_brand_Product_Detail")) {
			action = new AuctionBrandProductDetailAction(); // 옥션 관리 *추가 2023-04-17
		} else if (command.equals("add_auction")) {
			action = new AddAuctionAction(); // 옥션 등록 *추가 2023-04-18

			/* 옥션 페이지 */
		} else if (command.equals("auction_view")) {
			action = new AuctionViewAction(); // 옥션 view *추가 2023-04-18
		} else if (command.equals("auction_detail")) {
			action = new AuctionDetailAction(); // 옥션 detail *추가 2023-04-19
		} else if (command.equals("auction_deal")) {
			action = new AuctionDealAction(); // 옥션 입찰 *추가 2023-04-19
		} else if (command.equals("auction_complete")) {
			action = new AuctionCompleteAction(); // 옥션 입찰 *추가 2023-04-19
		} else if (command.equals("Auction_Buy_Page")) {
			action = new AuctionBuyPageAction(); // 옥션 계산 *추가 2023-04-20

			/* 상품 페이지 */

		} else if (command.equals("product_list")) {
			action = new ProductListAction();// 상품목록
		} else if (command.equals("cart")) {
			action = new CartListAction(); // 장바구니
		} else if (command.equals("product_write_form")) { // 상품작성폼
			action = new ProductWriteFormAction();
		} else if (command.equals("product_write")) { // 상품작성 (작성은 됨...)
			action = new ProductWriteAction();
		} else if (command.equals("product_View")) { // 상품상세
			action = new ProductViewAction();
		} else if (command.equals("add_cart")) { // 상품을 장바구니에 넣기
			action = new AddCartAction();
		} else if (command.equals("delete_cart")) { // 장바구니에서 상품 삭제
			action = new DeleteCartAction();
		} else if (command.equals("user_cart")) { // 오른쪽 상단 카트
			action = new UserCart();
		} else if (command.equals("Check_out")) {// 결제페이지 /4.18
			action = new CheckoutAction();
		} else if (command.equals("order_insert")) { // 주문넣기
			action = new OrderInsertAction();
		} else if (command.equals("order_list")) { // 주문리스트
			action = new OrderListAction();
		} else if (command.equals("user_purchased_list")) {
			action = new PurchasedListAction(); // 주문 내역 리스트
		} else if (command.equals("user_purchased_detail")) {
			action = new PurchasedDetailAction(); // 주문 상세 정보


			/* 상품 카테고리 별 이동 */
		} else if (command.equals("BRAND_TOP_LIST")) { // 상의 리스트 4.14
			action = new brandTopList();
		} else if (command.equals("BRAND_Bottom_LIST")) { // 하의 리스트
			action = new brandBottomList();
		} else if (command.equals("BRAND_Bcollection_LIST")) {// 잡화 리스트
			action = new brandBcollectionList();
		} else if (command.equals("BRAND_LIST")) { // 브랜드 상품 리스트 4.14
			action = new brandList();
		} else if (command.equals("brand_Product_Detail")) {// num값으로 으로 상품 검색
			action = new brandProductDetail();

			/* 상품 검색 */

		} else if (command.equals("search_product")) {// 헤더 상품 검색창
			action = new searchProduct();
		} else if (command.equals("search_brand_Product_Detail")) { //디테일 페이지창
			action = new searchBrandProductDetail();

			/* 핫딜 */

		} else if (command.equals("Hot_Deal")) { // 핫딜 페이지 4.18
			action = new HotDeal();
		} else if (command.equals("HotDeal_Buy_Page")) { // 핫딜 구매 페이지
			action = new HotDealBuyPage();
		} else if (command.equals("hotdeal_order_insert")) {// 핫딜 결제정보 전송 4.19
			action = new HotDealOrderInsert();
		} else if (command.equals("hotdeal_order_list")) { // 핫딜 결제완료 폼이동
			action = new HotDealOrderList();
		}

		return action;

	}

}
