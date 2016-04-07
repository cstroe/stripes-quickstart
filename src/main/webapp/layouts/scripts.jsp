<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="jsversions" var="bundle"/>
<fmt:message key="jquery_version" var="jquery_version" bundle="${bundle}"/>
<fmt:message key="bootstrap_version" var="bootstrap_version" bundle="${bundle}"/>
<script src="/webjars/jquery/${jquery_version}/jquery.min.js"></script>
<script src="/webjars/bootstrap/${bootstrap_version}/js/bootstrap.min.js"></script>