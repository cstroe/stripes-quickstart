<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<s:layout-definition>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="utf-8">
        <title>Basic Bootstrap Template</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css">
    </head>

    <body>
      <nav class="navbar navbar-default">
        <div class="container">
          <div class="navbar-header">
            <s:link beanclass="com.github.cstroe.sqs.www.NoteActionBean" class="navbar-brand">Notes</s:link>
          </div>
          <ul class="nav navbar-nav">
            <c:choose>
              <c:when test="${title == 'View Notes'}">
                <c:set var="allNotesClass" value="active"/>
              </c:when>
              <c:when test="${title == 'Add Notebook'}">
                <c:set var="addGroupClass" value="active"/>
              </c:when>
              <c:when test="${title == 'Create Note'}">
                <c:set var="addNoteClass" value="active"/>
              </c:when>

              <c:otherwise>
                ${actionBean.recordError()}
              </c:otherwise>
            </c:choose>
            <li class="${allNotesClass}">
              <s:link beanclass="com.github.cstroe.sqs.www.NoteActionBean">List</s:link>
            </li>
            <li class="${addGroupClass}">
              <s:link beanclass="com.github.cstroe.sqs.www.NoteActionBean" event="create">Add Group</s:link>
            </li>
            <li class="${addNoteClass}">
              <s:link beanclass="com.github.cstroe.sqs.www.NoteActionBean" event="create">Add Note</s:link>
            </li>
          </ul>
        </div>
      </nav>

      <div class="container">
        <div class="row">
          <div class="col-sm-12">
            <s:messages/>
          </div>
        </div>
      </div>

      <s:layout-component name="pageBody"/>

      <script src="/webjars/jquery/1.11.1/jquery.min.js"></script>
      <script src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
    </html>
</s:layout-definition>