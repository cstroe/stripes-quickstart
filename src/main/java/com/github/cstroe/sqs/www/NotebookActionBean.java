package com.github.cstroe.sqs.www;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

public class NotebookActionBean extends BaseActionBean {
    public Resolution create() {
        return new ForwardResolution("/notes/createNotebook.jsp");
    }
}
