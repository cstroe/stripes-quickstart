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
        <div class="col-sm-3">
          <table class="table table-hover">
            <thead>
              <th>Notebook</th>
            </thead>
            <tbody id="notebooksList">
              <c:forEach items="${actionBean.notebooks}" var="notebook">
                <tr>
                  <td>${notebook.name}</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <div class="col-sm-9">
          <h3>Notes</h3>

        </div>
      </div>
    </div>
  </s:layout-component>
</s:layout-render>
