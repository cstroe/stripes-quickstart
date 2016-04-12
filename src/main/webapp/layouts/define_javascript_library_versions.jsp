<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="jsversions" var="bundle"/>
<fmt:message key="jquery_version" var="jquery_version" bundle="${bundle}" scope="request"/>
<fmt:message key="bootstrap_version" var="bootstrap_version" bundle="${bundle}" scope="request"/>
<fmt:message key="simplemde_version" var="simplemde_version" bundle="${bundle}" scope="request"/>