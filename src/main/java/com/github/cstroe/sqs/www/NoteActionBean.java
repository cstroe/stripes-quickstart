package com.github.cstroe.sqs.www;

import com.github.cstroe.sqs.dao.NotebookDao;
import com.github.cstroe.sqs.dao.NoteDao;
import com.github.cstroe.sqs.model.Note;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import java.time.LocalDateTime;

@UrlBinding("/app/note/{$event}")
public class NoteActionBean extends BaseActionBean {
    public String title;

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

    public Note getNote() {
        return new NoteDao(1, "A User", LocalDateTime.now(), "Some Note", "Blah\nBlah\nBlah", new NotebookDao(1, "Ungrouped"));
    }
}
