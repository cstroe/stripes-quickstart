<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<s:layout-render
    name="/layouts/main.jsp"
    title="Create Notebook"
    addNotebookClass="active"
    recentNotes="${actionBean.recentNotes}">
  <s:layout-component name="pageBody">
    <div class="container">
    <s:form beanclass="com.github.cstroe.sqs.www.NotebookActionBean"
            id="createNotebookForm"
            class="form-horizontal">
      <div class="form-group">
        <label class="control-label col-sm-2">Name:</label>
        <div class="col-sm-8">
          <input type="text" class="form-control" id="name" placeholder="Enter notebook name"/>
        </div>
        <div class="col-sm-2">
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <s:submit name="submit" class="btn btn-primary pull-right" value="Add"/>
        </div>
        <div class="col-sm-2"></div>
      </div>
    </s:form>
    </div>
  </s:layout-component>
</s:layout-render>