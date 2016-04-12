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
    private NoteDao noteInDatabase;

    @DefaultHandler
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
        saveNoteInDatabase();
        return new RedirectResolution(NoteActionBean.class, "edit").addParameter("id", noteInDatabase.getId());
    }

    public Resolution saveAndClose() {
        saveNoteInDatabase();
        return new RedirectResolution(ViewActionBean.class, "notebook").addParameter("id", noteInDatabase.getNotebook().getId());
    }

    private void saveNoteInDatabase() {
        noteInDatabase.setTitle(note.getTitle());
        noteInDatabase.setContent(note.getContent());
        RepositoryFactory.note().save(noteInDatabase);
    }

    @ValidationMethod(on = "edit")
    public void lookupNote(ValidationErrors errors) {
        Optional<NoteDao> maybeNote = RepositoryFactory.note().findById(id);
        if(maybeNote.isPresent()) {
            note = maybeNote.get();
        } else {
            errors.add("id", new SimpleError("A note does not exist with the given id"));
        }
    }

    @ValidationMethod(on = "create")
    public void checkThatNotebookExists(ValidationErrors errors) {
        Optional<NotebookDao> maybeNotebook = RepositoryFactory.notebook().findById(note.getNotebook().getId());
        if(!maybeNotebook.isPresent()) {
            errors.add("note.notebook", new SimpleError("Invalid notebook selected"));
        }
    }

    @ValidationMethod(on = {"save", "saveAndClose"})
    public void checkThatNoteExists(ValidationErrors errors) {
        Optional<NoteDao> maybeNote = RepositoryFactory.note().findById(note.getId());
        if(!maybeNote.isPresent()) {
            errors.add("note", new SimpleError("Note could not be found"));
        } else {
            noteInDatabase = maybeNote.get();
        }
    }

    public NoteDao getNote() {
        return note;
    }

    public void setNote(NoteDao note) {
        this.note = note;
    }
}
