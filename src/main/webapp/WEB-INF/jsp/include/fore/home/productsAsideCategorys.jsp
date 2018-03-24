<!-- 模仿天猫整站ssm 教程 为how2j.cn 版权所有-->
<!-- 本教程仅用于学习使用，切勿用于非法用途，由此引起一切后果与本站无关-->
<!-- 供购买者学习，请勿私自传播，否则自行承担相关法律责任-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script>
$(function(){
	$("div.productsAsideCategorys div.row a").each(function(){
		var v = Math.round(Math.random() *6);
		if(v == 1)
			$(this).css("color","#87CEFA");
	});
});

</script>
<c:forEach items="${cs}" var="c">
	<div cid="${c.id}" class="productsAsideCategorys">
	 
		<c:forEach items="${c.productsByRow}" var="ps">
			<div class="row show1">
				<c:forEach items="${ps}" var="p">
					<c:if test="${!empty p.subTitle}">
						<a href="foreproduct?pid=${p.id}">
							<c:forEach items="${fn:split(p.subTitle, ' ')}" var="title" varStatus="st">
								<c:if test="${st.index==0}">
									${title}
								</c:if>
							</c:forEach>
						</a>
					</c:if>
				</c:forEach>
				<div class="seperator"></div>
			</div>		
		</c:forEach>
	</div>			
</c:forEach>
	
