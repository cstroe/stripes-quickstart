package com.github.cstroe.sqs.www;

import com.github.cstroe.sqs.dao.NoteDao;
import com.github.cstroe.sqs.dao.NotebookDao;
import com.github.cstroe.sqs.repository.RepositoryFactory;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import net.sourceforge.stripes.validation.ValidationState;

import java.util.Optional;

@UrlBinding("/app/note/{$event}/{id}")
public class NoteActionBean extends BaseActionBean {
    @Validate(required = true, minlength = 1, on = {"create"})
    public String title;

    @Validate(required = true, minvalue = 0, on = {"edit"})
    public long id;

    @ValidateNestedProperties({
        @Validate(field = "title", required = true, minlength = 1, on = "create"),
        @Validate(field = "notebook", required = true, on = "create")
    })
    public NoteDao note;

    @DefaultHandler
    public Resolution viewAll() {
        return new ForwardResolution("/notes/viewAllNotes.jsp");
    }

    @HandlesEvent("new")
    public Resolution newNote() {
        return new ForwardResolution("/notes/addNote.jsp");
    }

    public Resolution create() {
        return new ForwardResolution("/notes/addNote.jsp");
    }

    public Resolution edit() {
        return new ForwardResolution("/notes/editNote.jsp");
    }

    @ValidationMethod(on = "edit", when = ValidationState.NO_ERRORS)
    public void checkThatNoteExists(ValidationErrors errors) {
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
}
