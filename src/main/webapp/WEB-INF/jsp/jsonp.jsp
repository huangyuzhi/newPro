<%@ page language="java" isELIgnored="false" session="false"
    pageEncoding="UTF-8" contentType="text/javascript;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:out value="${param.callback }"></c:out>(<c:out value="${requestScope.data }" escapeXml="false"></c:out>)
