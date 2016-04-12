package com.github.cstroe.sqs.www;

import com.github.cstroe.sqs.dao.NotebookDao;
import com.github.cstroe.sqs.hibernate.HibernateSessionUtil;
import com.github.cstroe.sqs.model.Notebook;
import com.github.cstroe.sqs.model.Note;
import com.github.cstroe.sqs.repository.RepositoryFactory;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class BaseActionBean implements ActionBean {
    private ActionBeanContext context;

    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    public ActionBeanContext getContext() {
        return context;
    }

    public List<Note> getRecentNotes() {
        return new ArrayList<>();
    }

    public void recordError() {
        // do some sort of error logging here
        throw new RuntimeException("Error was encountered");
    }

    public List<NotebookDao> getNotebooks() {
        return RepositoryFactory.notebook().findAll();
    }
}
