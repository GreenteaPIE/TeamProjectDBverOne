<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../header.jsp"%>
<script type="text/javascript" src="script/member.js"></script>
<div class="frame user-frm">
	<article class="card-body" style="max-width: 700px; margin: auto;">
		<!-- 회원가입 form태그 시작 -->
		<form id="join" action="DBServlet?command=join" method="post" name="formm">
			<div class="form-group input-group fg-x700">
				<div class="input-group-prepend" style="height: 38px ">
					<span class="input-group-text"> <i class="fa fa-user"></i>
					</span>
				</div>
				<input name="name" class="form-control" placeholder="이름 입력" type="text" required />

				<div class="btn-group" date-toggle="buttons">
					<label class="btn  active" style="background-color: #D9D9D9;"> <input type="radio" name="gender" autocomplete="off" value="1" checked>남자
					</label> <label class="btn " style="color: #737272; background-color: #D9D9D9;"> <input type="radio" name="gender" autocomplete="off" value="2" checked>여자
					</label>

				</div>
			</div>
			<!-- form-group// -->

			<div class="form-group input-group fg-x700">
				<div class="input-group-prepend">
					<span class="input-group-text"> <i class="fa fa-user"></i>
					</span>
				</div>
				<input name="userid" class="form-control" placeholder="Id 입력" type="text" size="12" required />
				<input name="reid" class="form-control" placeholder="Id 입력" type="hidden" size="12" required />

				<input type="button" value="중복 체크" class="dup" onclick="idcheck()">
				<br>
			</div>
			<!-- form-group// -->
			<div class="form-group input-group fg-x700">
				<div class="input-group-prepend">
					<span class="input-group-text"> <i class="fa fa-envelope"></i>
					</span>
				</div>
				<input name="email" class="form-control" placeholder="Email 입력" type="email" required />
			</div>
			<!-- form-group// -->
			<div class="form-group input-group fg-x700">
				<div class="input-group-prepend">
					<span class="input-group-text"> <i class="fa fa-phone"></i>
					</span>
				</div>
				<input name="phone" class="form-control" placeholder="휴대폰번호 입력('-' 미포함)" type="text" required />
			</div>
			<!-- form-group// -->
			<div class="form-group input-group fg-x700">
				<div class="input-group-prepend">
					<span class="input-group-text"> <i class="fa fa-building"></i>
					</span>
				</div>


				<input type="text" id="zipcode" class="form-control" name="zipcode" placeholder="우편번호" readonly="readonly" />


				<br />
				<div class="form-group input-group fg-x700">

					<input type="text" id="address1" class="form-control" name="address1" placeholder="주소" readonly="readonly" />
				</div>
				<br />
				<input type="text" id="address2" class="form-control" name="address2" placeholder="상세주소" />
				<br /> <a href="javascript:void(0);" onclick="popupZipSearch();return false;" class="rbutton xsmall white">우편번호찾기</a> <br>
			</div>
			<!-- form-group end.// -->
			<div class="form-group input-group fg-x700">
				<div class="input-group-prepend">
					<span class="input-group-text"> <i class="fa fa-lock"></i>
					</span>
				</div>
				<input name="pass" class="form-control" placeholder="비밀번호 입력" type="password" required>
				<input name="passCheck" class="form-control" placeholder="비밀번호 확인" type="password" required>
			</div>
			<!-- form-group// -->
			<div class="fg-x700 form-group">
				<button type="submit" class="btn btn-primary btn-block" onclick="go_save()">회원 가입</button>
			</div>
			<!-- form-group// -->
			<p class="text-center">
				카카오 / 네이버를 통한 로그인도 가능합니다. <a href="user?cmd=loginForm">로그인</a>
			</p>
		</form>
	</article>
</div>
<!-- card.// -->
<!--container end.//-->

<br>
<br>



<%@ include file="../footer.jsp"%>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	function popupZipSearch() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 각 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var fullAddr = ''; // 최종 주소 변수
						var extraAddr = ''; // 조합형 주소 변수

						//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
						if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
							fullAddr = data.roadAddress;
						} else { // 사용자가 지번 주소를 선택했을 경우(J)
							fullAddr = data.jibunAddress;
						}

						// 사용자가 선택한 주소가 도로명 타입일때 조합한다.
						if (data.userSelectedType === 'R') {
							//법정동명이 있을 경우 추가한다.
							if (data.bname !== '') {
								extraAddr += data.bname;
							}
							// 건물명이 있을 경우 추가한다.
							if (data.buildingName !== '') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
							fullAddr += (extraAddr !== '' ? ' (' + extraAddr
									+ ')' : '');
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('zipcode').value = data.zonecode;
						document.getElementById("address1").value = fullAddr;
						// 커서를 상세주소 필드로 이동한다.
						document.getElementById("address2").focus();
					}

				}).open();
	}
</script>