<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	pageContext.setAttribute("br", "<br/>");
pageContext.setAttribute("cn", "\n");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KM 지식거래소</title>
<link rel="short icon" href="img/icons/favicon.ico">
<link rel="stylesheet" type="text/css" href="css/css.css">
</head>
<body>
	<div>
		<input type="hidden" id="kmbId" value="${board.kmbId}"> <input
			type="hidden" id="kmbNum" value="${board.kmbNum}"> <input
			type="hidden" id="loginId" value="${sessionScope.loginId}"> <input
			type="hidden" id="kmbPoint" value="${board.kmbPoint}">
		<table class="bView">
			<tr>
				<th class="bViewth2">No.</th>
				<td class="bViewtd">${board.kmbNum}</td>
				<th class="bViewth2">작성자</th>
				<td class="bViewtd">${board.kmbId}</td>
			</tr>
			<tr>
				<th class="bViewth2">카테고리</th>
				<td class="bViewtd">${board.kmbCategory}</td>
				<th class="bViewth2">KM Point</th>
				<td class="bViewtd">${board.kmbPoint}</td>
			</tr>
			<tr>
				<th class="bViewth2">작성일</th>
				<td class="bViewtd">${board.kmbDate}</td>
				<th class="bViewth2">조회수</th>
				<td class="bViewtd">${board.kmbHits}</td>
			</tr>
			<tr>
				<th colspan="1" class="bViewthCenter">제목</th>
				<td colspan="3" class="bViewtd">${board.kmbTitle}</td>
			</tr>
			<tr>
				<th colspan="4" class="bViewthCenter">내용</th>
			</tr>
			<tr>
				<td colspan="4">${fn:replace(board.kmbContents, cn, br)}</td>
			</tr>
			<tr>
				<c:choose>
					<c:when
						test="${board.kmbFile != null && sessionScope.loginId != null}">
						<th colspan="1" class="bViewth2">파일</th>
						<td colspan="3">${board.kmbFile}<br>
							<button onclick="fileDown()" style='cursor: pointer'>다운로드(KM
								Point차감)</button></td>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>

			</tr>
			<tr>
				<td class="tdBlank2"></td>
			</tr>
			<tr>

				<td colspan="4" style="text-align: center"><c:choose>
						<c:when
							test="${sessionScope.loginId != null && sessionScope.loginId != 'admin'}">
							<button
								onclick="location.href='boardModifyForm?kmbNum=${board.kmbNum}'"
								class="button">수정</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button onclick="boardDelete()" style='cursor: pointer'
								class="button">삭제</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button onclick="location.href='boardSue?kmbNum=${board.kmbNum}'"
								style='cursor: pointer' class="redButton">신고</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</c:when>
						<c:when test="${sessionScope.loginId eq 'admin'}">
							<button
								onclick="location.href='boardModifyForm?kmbNum=${board.kmbNum}'"
								class="button">수정</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button onclick="boardDelete()" style='cursor: pointer'
								class="button">삭제</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button
								onclick="location.href='boardRecover?kmbNum=${board.kmbNum}'"
								style='cursor: pointer' class="greenButton">철회</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
					<button class="button" onclick="window.close()">닫기</button></td>
			</tr>
		</table>
	</div>
</body>
<script>
	// 글 삭제
	function boardDelete() {
		var loginId = document.getElementById("loginId").value;
		var kmbId = document.getElementById("kmbId").value;
		var kmbNum = document.getElementById("kmbNum").value;

		console.log("loginId : " + loginId);
		console.log("kmbId : " + kmbId);

		if (loginId != kmbId && loginId != 'admin') {
			alert('본인이 작성한 글이 아닙니다!');
		} else {
			location.href = "boardDelete?kmbNum=" + kmbNum;
		}

	}
	function fileDown() {
		var loginId = document.getElementById("loginId").value;
		var kmbId = document.getElementById("kmbId").value;
		var kmbPoint = document.getElementById("kmbPoint").value;
		var downConfirm = confirm("다운로드시 KM Point가 차감됩니다.");
		if (downConfirm) {
			/* 구매자 KM Point 차감 */
			location.href = "fileDown?kmbId=" + loginId + "&kmbPoint="
					+ kmbPoint;
			alert('구매 완료');
			/* 작성자 KM Point 증가*/
			location.href = "fileDown2?kmbId=" + kmbId + "&kmbPoint="
					+ kmbPoint;
			alert('파일 다운을 시작합니다.');
			/* 파일 다운 실행 */
			location.href = 'fileUpload/${board.kmbFile}';
		} else {
			alert("다운로드가 취소되었습니다.");
		}
	}
</script>
</html>