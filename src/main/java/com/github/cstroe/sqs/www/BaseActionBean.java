package com.github.cstroe.sqs.www;

import com.github.cstroe.sqs.hibernate.NotebookDto;
import com.github.cstroe.sqs.model.Notebook;
import com.github.cstroe.sqs.model.Note;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BaseActionBean implements ActionBean {
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

    public List<Notebook> getGroups() {
        List<Notebook> fakeNotebooks = new LinkedList<>();
        fakeNotebooks.add(new NotebookDto(1, "Default"));
        fakeNotebooks.add(new NotebookDto(1, "My Project"));
        fakeNotebooks.add(new NotebookDto(1, "Random"));
        return fakeNotebooks;
    }
}
