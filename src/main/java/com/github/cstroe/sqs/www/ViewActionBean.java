package com.github.cstroe.sqs.www;

import com.github.cstroe.sqs.dao.NoteDao;
import com.github.cstroe.sqs.dao.NotebookDao;
import com.github.cstroe.sqs.model.Notebook;
import com.github.cstroe.sqs.repository.RepositoryFactory;
import com.github.cstroe.sqs.www.ext.NotebookTypeConverter;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

import java.util.List;
import java.util.Optional;

@UrlBinding("/app/view/{$event}/{id}")
public class ViewActionBean extends BaseActionBean {
    @Validate(required = true, on = "notebook")
    public long id;

    private List<NoteDao> notes;

    @DefaultHandler
    public Resolution all() {
        return new ForwardResolution("/notes/viewAllNotes.jsp");
    }

    public Resolution notebook() {
        Optional<NotebookDao> maybeNotebook = RepositoryFactory.notebook().findById(id);
        if(!maybeNotebook.isPresent()) {
            getContext().getValidationErrors().add("id", new SimpleError("Could not find any notebook using the given id"));
            return new ForwardResolution(ViewActionBean.class, "all");
        }

        notes = RepositoryFactory.note().findByNotebook(maybeNotebook.get());
        return new ForwardResolution("/notes/viewNotebook.jsp");
    }

    public List<NoteDao> getNotes() {
        return notes;
    }
}
