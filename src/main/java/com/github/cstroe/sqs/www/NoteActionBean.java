package com.github.cstroe.sqs.www;

import com.github.cstroe.sqs.dao.NotebookDto;
import com.github.cstroe.sqs.dao.NoteDto;
import com.github.cstroe.sqs.model.Note;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import java.time.LocalDateTime;

public class NoteActionBean extends BaseActionBean {
    private long noteId;

    @DefaultHandler
    public Resolution view() {
        return new ForwardResolution("/notes/viewAllNotes.jsp");
    }

    public Resolution create() {
        return new ForwardResolution("/notes/createNote.jsp");
    }

    public Note getNote() {
        return new NoteDto(1, "A User", LocalDateTime.now(), "Some Note", "Blah\nBlah\nBlah", new NotebookDto(1, "Ungrouped"));
    }

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }
}
