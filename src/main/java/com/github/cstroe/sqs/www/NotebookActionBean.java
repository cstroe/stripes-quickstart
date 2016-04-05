package com.github.cstroe.sqs.www;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/app/notebook/{$event}")
public class NotebookActionBean extends BaseActionBean {
    @DefaultHandler
    @HandlesEvent("new")
    public Resolution newNotebook() {
        return new ForwardResolution("/notes/addNotebook.jsp");
    }

    public Resolution create() {
        return new ForwardResolution("/notes/addNotebook.jsp");
    }
}
