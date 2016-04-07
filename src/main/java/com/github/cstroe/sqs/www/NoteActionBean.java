package com.github.cstroe.sqs.www;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

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
}
