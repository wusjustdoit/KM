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
	<div>
		<c:choose>
			<c:when test="${sessionScope.loginId eq modify.kmbId}">

				<form action="boardModify" method="POST"
					enctype="multipart/form-data">
					<table class="bModify">
						<tr>
						<tr>
							<th>작성자</th>
							<td>${modify.kmbId}<input type="hidden" name="kmbNum"
								value="${modify.kmbNum}"></td>
						</tr>
						<tr>
							<th>카테고리</th>
							<td><select name="kmbCategory">
									<optgroup label="카테고리">
										<option value="노하우">노하우</option>
										<option value="업계현황">업계현황</option>
										<option value="서식">서식</option>
									</optgroup>
							</select></td>

							<th>KM Point</th>
							<td><select name="kmbPoint">
									<optgroup label="KM Point">
										<option value="100">100</option>
										<option value="500">500</option>
										<option value="1000">1000</option>
										<option value="5000">5000</option>
										<option value="10000">10000</option></td>
						</tr>
						<tr>
							<th>제목</th>
							<td colspan="3"><input type="text" class="form-control" name="kmbTitle"
								placeholder="${modify.kmbTitle}" style="width:620px;"></td>
						</tr>

						<tr>
							<th>내용</th>
							<td colspan="3"><textarea cols="94" rows="30" name="kmbContents"
									placeholder="${modify.kmbContents}"></textarea></td>
						</tr>

						<tr>
							<th>파일</th>
							<td><input type="file" name="kmbFile"></td>
						</tr>

						<tr>
							<td colspan="4"><input type="submit" value="등록" class="blueButton"></td>
						</tr>
					</table>
				</form>

			</c:when>

			<c:otherwise>
				<h1>작성자의 글이 아닙니다!</h1>
				<button onclick="window.history.back()">뒤로 가기</button>

			</c:otherwise>
		</c:choose>
	</div>

</body>
</html>