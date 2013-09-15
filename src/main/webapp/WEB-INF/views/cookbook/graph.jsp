<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>{
  "nodes": [<c:forEach items="${nodes}" var="node" varStatus="status">
    { "name": "${node.name}",
      "group": "${node.group}"}<c:if test="${not status.last}">,</c:if>
  </c:forEach>],
  "links": [<c:forEach items="${links}" var="link" varStatus="status">
    { "source": ${link.source},
      "target": ${link.target},
      "value": ${link.value}}<c:if test="${not status.last}">,</c:if>
  </c:forEach>]
}
