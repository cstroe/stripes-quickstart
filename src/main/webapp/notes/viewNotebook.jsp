<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<s:layout-render
    name="/layouts/main.jsp"
    title="View Notes"
    viewAllNotesClass="active"
    recentNotes="${actionBean.recentNotes}">
  <s:layout-component name="pageBody">
	<div class="container">
      <div class="row">
        <div class="col-sm-8 col-sm-offset-2">
          <s:errors/>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-3">
          <table class="table table-hover">
            <thead>
              <th>Notebook</th>
            </thead>
            <tbody id="notebooksList">
              <s:url var="url" beanclass="com.github.cstroe.sqs.www.ViewActionBean" event="all"/>
              <c:forEach items="${actionBean.notebooks}" var="notebook">
                <td>
                  <s:link beanclass="com.github.cstroe.sqs.www.ViewActionBean" event="notebook">
                    <s:param name="id" value="${notebook.id}"/>
                    <s:param name="_sourcePage" value="${url}"/>
                    ${notebook.name}
                  </s:link>
                </td>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <div class="col-sm-9">
          <c:forEach items="${actionBean.notes}" var="note">
            <p>
              ${note.title}
            </p>
          </c:forEach>
        </div>
      </div>
    </div>
  </s:layout-component>
</s:layout-render>
