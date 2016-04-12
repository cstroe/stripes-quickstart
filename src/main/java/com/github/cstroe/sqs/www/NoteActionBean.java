package com.github.cstroe.sqs.www;

import com.github.cstroe.sqs.dao.NoteDao;
import com.github.cstroe.sqs.dao.NotebookDao;
import com.github.cstroe.sqs.repository.RepositoryFactory;
import com.github.cstroe.sqs.www.ext.NotebookTypeConverter;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.validation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@UrlBinding("/app/note/{$event}/{id}")
public class NoteActionBean extends BaseActionBean {
    @Validate(required = true, minvalue = 0, on = "edit")
    public long id;

    @ValidateNestedProperties({
            @Validate(field = "id", required = true, minvalue = 0, on = "save"),
            @Validate(field = "title", required = true, minlength = 1, on = {"create", "save"}),
            @Validate(field = "content", required = true, on = "save"),
            @Validate(field = "notebook", required = true, on = "create", converter = NotebookTypeConverter.class)
    })
    private NoteDao note;

    @DefaultHandler
    public Resolution viewAll() {
        return new ForwardResolution("/notes/viewAllNotes.jsp");
    }

    @HandlesEvent("new")
    public Resolution newNote() {
        return new ForwardResolution("/notes/addNote.jsp");
    }

    public Resolution create() {
        note.setCreated(LocalDateTime.now());
        RepositoryFactory.note().save(note);
        return new RedirectResolution(NoteActionBean.class, "edit").addParameter("id", note.getId());
    }

    public Resolution edit() {
        return new ForwardResolution("/notes/editNote.jsp");
    }

    public Resolution save() {
        Optional<NoteDao> maybeNote = RepositoryFactory.note().findById(note.getId());
        if(!maybeNote.isPresent()) {
            getContext().getValidationErrors().add("note", new SimpleError("Note could not be found"));
            return getContext().getSourcePageResolution();
        }

        NoteDao noteDao = maybeNote.get();
        noteDao.setTitle(note.getTitle());
        noteDao.setContent(note.getContent());
        RepositoryFactory.note().save(noteDao);
        return new RedirectResolution(NoteActionBean.class, "edit").addParameter("id", noteDao.getId());
    }

    @ValidationMethod(on = "edit", when = ValidationState.NO_ERRORS)
    public void lookupNote(ValidationErrors errors) {
        Optional<NoteDao> maybeNote = RepositoryFactory.note().findById(id);
        if(maybeNote.isPresent()) {
            note = maybeNote.get();
        } else {
            errors.add("id", new SimpleError("A note does not exist with the given id"));
        }
    }

    @ValidationMethod(on = "create", when = ValidationState.NO_ERRORS)
    public void checkThatNotebookExists(ValidationErrors errors) {
        Optional<NotebookDao> maybeNotebook = RepositoryFactory.notebook().findById(note.getNotebook().getId());
        if(!maybeNotebook.isPresent()) {
            errors.add("note.notebook", new SimpleError("Invalid notebook selected"));
        }
    }

    public NoteDao getNote() {
        return note;
    }

    public void setNote(NoteDao note) {
        this.note = note;
    }
}
