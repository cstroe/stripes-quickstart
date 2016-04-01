<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<s:layout-render
    name="/layouts/main.jsp"
    title="Create Note"
    recentNotes="${actionBean.recentNotes}">
  <s:layout-component name="pageBody">
    <div class="container">
    <s:form beanclass="com.github.cstroe.sqs.www.NoteActionBean"
            id="createNoteForm"
            class="form-horizontal">
      <div class="form-group">
        <label class="control-label col-sm-2">Title:</label>
        <div class="col-sm-8">
          <input type="text" class="form-control" id="title" placeholder="Enter title"/>
        </div>
        <div class="col-sm-2">
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-2"></label>
        <div class="col-sm-8">
          <input type="text" class="form-control" id="content" placeholder="Enter note content"/>
        </div>
        <div class="col-sm-2">
        </div>
      </div>
      <s:submit name="submit" class="btn btn-primary pull-right" value="Create"/>
    </s:form>
    </div>
  </s:layout-component>
</s:layout-render>