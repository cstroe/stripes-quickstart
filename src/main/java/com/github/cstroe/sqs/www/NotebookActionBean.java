package com.github.cstroe.sqs.www;

import com.github.cstroe.sqs.dao.NotebookDao;
import com.github.cstroe.sqs.repository.NotebookRepository;
import com.github.cstroe.sqs.repository.RepositoryFactory;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/app/notebook/{$event}")
public class NotebookActionBean extends BaseActionBean {
    private String notebookName;

    @DefaultHandler
    @HandlesEvent("new")
    public Resolution newNotebook() {
        return new ForwardResolution("/notes/addNotebook.jsp");
    }

    public Resolution create() {
        NotebookDao dao = new NotebookDao(0, getNotebookName());
        RepositoryFactory.notebook().create(dao);
        return new ForwardResolution("/notes/viewAllNotes.jsp");
    }

    public String getNotebookName() {
        return notebookName;
    }

    public void setNotebookName(String notebookName) {
        this.notebookName = notebookName;
    }
}
