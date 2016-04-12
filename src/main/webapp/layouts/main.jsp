<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="define_javascript_library_versions.jsp"/>

<s:layout-definition>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="utf-8">
        <title>${title}</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/${bootstrap_version}/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="/webjars/simplemde-markdown-editor/${simplemde_version}/dist/simplemde.min.css">
    </head>

    <body>
      <jsp:include page="scripts.jsp"/>

      <nav class="navbar navbar-default">
        <div class="container">
          <ul class="nav navbar-nav">
            <li id="viewNotesNav" class="${viewAllNotesClass}">
              <s:link beanclass="com.github.cstroe.sqs.www.ViewActionBean" event="all">View Notes</s:link>
            </li>
            <li id="addNotebookNav" class="${addNotebookClass}">
              <s:link beanclass="com.github.cstroe.sqs.www.NotebookActionBean" event="new">Add Notebook</s:link>
            </li>
            <li id="addNoteNav" class="${addNoteClass}">
              <s:link beanclass="com.github.cstroe.sqs.www.NoteActionBean" event="new">Add Note</s:link>
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
    </body>
    </html>
</s:layout-definition>