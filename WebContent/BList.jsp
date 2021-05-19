<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KM 지식거래소</title>
<link rel="short icon" href="img/icons/favicon.ico">
<link rel="stylesheet" type="text/css" href="css/css.css">
</head>
<body>
	<div class="left1">
		<img alt="logo" src="img/logo/logo.png" width=200px onclick="Home()"
			style='cursor: pointer'>
	</div>
	<div class="left2">
		<c:choose>
			<c:when test="${sessionScope.loginId eq 'admin'}">
				<table class="loginTable">
					<h4>관리자 모드로 접속 중</h4>
					<button onclick="location.href='memberList'" class="defaultButton">회원목록</button>
					<button onclick="location.href='BlistP'" class="defaultButton">신고게시물</button>
					<button onclick="location.href='memberLogout'" class="logoutButton">로그아웃</button>
				</table>
			</c:when>
			<c:when test="${sessionScope.loginId != null}">
				<table class="loginTable">
					<h4>${sessionScope.loginId}님 접속 중</h4>
					<button
						onclick="location.href='memberView?kmId=${sessionScope.loginId}'"
						class="defaultButton">나의 회원 정보</button>
					<button onclick="location.href='memberLogout'" class="logoutButton">로그아웃</button>
				</table>
			</c:when>
			<c:otherwise>
				<form action="memberLogin" method="POST">
					<table class="loginTable">
						<tr>
							<td colspan="2"><input type="text" name="kmId"
								placeholder="아이디"></td>
						</tr>
						<tr>
							<td colspan="2"><input type="password" name="kmPw"
								placeholder="비밀번호"></td>
						</tr>
						<tr>
							<td><input type="submit" value="로그인" style='cursor: pointer'
								class="defaultButton"></td>
							<td><button onclick="Join()" style='cursor: pointer'
									class="joinButton">회원가입</button>
							<td>
						</tr>
					</table>
				</form>
			</c:otherwise>
		</c:choose>
	</div>

	<div class="menu">
		<hr>
		<button class="menuButton" onclick="Write()" style='cursor: pointer'>게시글
			작성</button>
		<br>
		<button class="menuButton" onclick="BList()" style='cursor: pointer'>게시글
			보기</button>
	</div>
	<div class="bListBody">
		<div align="left">
			<img alt="logo" src="img/logo/bLogo.png" onclick="Home()"
				style="cursor: pointer">
		</div>
		<div>

			<table class="bList">

				<thead>
					<tr>
						<th style="width: 5%" class="bListTth">No.</th>
						<th style="width: 10%" class="bListTth">카테고리</th>
						<th style="width: 10%" class="bListTth">KM Point</th>
						<th style="width: 35%" class="bListTth">제목</th>
						<th style="width: 15%" class="bListTth">작성자</th>
						<th style="width: 15%" class="bListTth">작성일</th>
						<th style="width: 8%" class="bListTth">조회수</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="board" items="${bList}">
						<tr>
							<td class="bListTtd">${board.kmbNum}</td>
							<td class="bListTtd">${board.kmbCategory}</td>
							<td class="bListTtd">${board.kmbPoint}</td>
							<td class="bListTtd"><a
								href="boardView?kmbNum=${board.kmbNum}&page=${paging.page}"
								onclick="window.open(this.href,'_blank',
								'width=710px,height=900px,toolbars=no,scrollbars=no'); return false;">${board.kmbTitle}</a></td>
							<td class="bListTtd">${board.kmbId}</td>
							<td class="bListTtd">${board.kmbDate}</td>
							<td class="bListTtd">${board.kmbHits}</td>
						</tr>


						<input type="hidden" id="kmbId" value="${board.kmbId}">
						<input type="hidden" id="kmbNum" value="${board.kmbNum}">
						<input type="hidden" id="loginId" value="${sessionScope.loginId}">
					</c:forEach>
					<tr>
						<td colspan="1" style="text-align: left"><select
							name="sCategory" onchange="funCategory()">
								<option>카테고리</option>
								<option value="노하우">노하우</option>
								<option value="업계현황">업계현황</option>
								<option value="서식">서식</option>
						</select></td>
						<td colspan="6" style="text-align: right"><select
							name="limit" id="limit" onchange="funSel()">
								<option>게시글 갯수</option>
								<option value="1">1개씩</option>
								<option value="3">3개씩</option>
								<option value="5">5개씩</option>
								<option value="10">10개씩</option>
						</select></td>
					</tr>

					<tr>
						<td colspan="7">
							<!-- 페이징 처리 --> <!-- [이전] 에 대한 페이징 처리 --> <c:if
								test="${paging.page<=1}">[이전]&nbsp;</c:if> <c:if
								test="${paging.page>1}">
								<a href="boardList?page=${paging.page-1}&limit=${paging.limit}">[이전]</a>&nbsp;
								</c:if> <!-- 페이지 숫자에 대한 페이징 처리 --> <c:forEach
								begin="${paging.startPage}" end="${paging.endPage}" var="i"
								step="1">
								<c:choose>

									<c:when test="${i eq paging.page}">
											${i}
										</c:when>

									<c:otherwise>
										<a href="boardList?page=${i}&limit=${paging.limit}">${i}</a>
									</c:otherwise>
								</c:choose>
							</c:forEach> <!-- [다음] 에 대한 페이징 처리 --> <c:if
								test="${paging.page>=paging.maxPage}">[다음]&nbsp;</c:if> <c:if
								test="${paging.page<paging.maxPage}">
								<a href="boardList?page=${paging.page+1}&limit=${paging.limit}">[다음]</a>&nbsp;
								</c:if>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- 페이징처리 끝 -->
	</div>

</body>

<script>
	function funSel() {
		var limit = document.getElementById("limit").value;
		location.href = "boardList?limit=" + limit;
	}
	function funCategory(){
		var sCategory = document.getElementById("sCategory").value;
		location.href = "boardCategory?sCategory=" + sCategory;
	}
</script>
<script>
	/* index 페이지 이동 : Home()함수 */
	function Home() {
		location.href = "index.jsp";
	}
	/* 회원가입 페이지 이동 : Join()함수 */
	function Join() {
		window.open("JoinForm.jsp", '_blank',
				'width=700px,height=900px,toolbars=no,scrollbars=no');
	}

	// 글쓰기 페이지로 이동 : Write()함수
	function Write() {
		location.href = "WriteForm.jsp";
	}

	// 글목록 페이지로 이동 : Write()함수
	function BList() {
		location.href = "boardList";
	}
</script>
</html>