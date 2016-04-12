<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<s:layout-render
    name="/layouts/main.jsp"
    title="Add Note"
    addNoteClass="active"
    recentNotes="${actionBean.recentNotes}">
  <s:layout-component name="pageBody">
    <div class="container">
      <s:form beanclass="com.github.cstroe.sqs.www.NoteActionBean"
              id="createNoteForm"
              class="form-horizontal">
        <div class="row">
          <div class="col-sm-8 col-sm-offset-2">
            <s:errors/>
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-sm-2" for="selectNotebook">Notebook:</label>
          <div class="col-sm-8">
            <s:select name="note.notebook" class="form-control" id="selectNotebook">
              <s:options-collection collection="${actionBean.notebooks}" label="name" value="id"/>
            </s:select>
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-sm-2" for="title">Title:</label>
          <div class="col-sm-8">
            <input type="text" name="note.title" class="form-control" id="title" placeholder="Enter note title"/>
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-8 col-sm-offset-2">
            <s:submit name="create" class="btn btn-primary pull-right" value="Create"/>
          </div>
        </div>
      </s:form>
    </div>
  </s:layout-component>
</s:layout-render>