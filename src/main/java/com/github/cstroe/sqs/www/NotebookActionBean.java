package com.github.cstroe.sqs.www;

import com.github.cstroe.sqs.dao.NotebookDao;
import com.github.cstroe.sqs.repository.NotebookRepository;
import com.github.cstroe.sqs.repository.RepositoryFactory;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/app/notebook/{$event}")
public class NotebookActionBean extends BaseActionBean {
    private NotebookDao notebook = new NotebookDao();

    @DefaultHandler
    @HandlesEvent("new")
    public Resolution newNotebook() {
        return new ForwardResolution("/notes/addNotebook.jsp");
    }

    public Resolution create() {
        RepositoryFactory.notebook().save(notebook);
        return new RedirectResolution(NotebookActionBean.class);
    }

    public NotebookDao getNotebook() {
        return notebook;
    }

    public void setNotebook(NotebookDao notebook) {
        this.notebook = notebook;
    }
}
