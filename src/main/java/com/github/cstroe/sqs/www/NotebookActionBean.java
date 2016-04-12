package com.github.cstroe.sqs.www;

import com.github.cstroe.sqs.dao.NotebookDao;
import com.github.cstroe.sqs.repository.NotebookRepository;
import com.github.cstroe.sqs.repository.RepositoryFactory;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import net.sourceforge.stripes.validation.ValidationState;

@UrlBinding("/app/notebook/{$event}")
public class NotebookActionBean extends BaseActionBean {
    @ValidateNestedProperties({
        @Validate(field="name", required = true, minlength = 1, on = "create")
    })
    public NotebookDao notebook = new NotebookDao();

    @DefaultHandler
    @HandlesEvent("new")
    public Resolution newNotebook() {
        return new ForwardResolution("/notes/addNotebook.jsp");
    }

    public Resolution create() {
        RepositoryFactory.notebook().save(notebook);
        return new RedirectResolution(ViewActionBean.class, "all");
    }

    @ValidationMethod(on = "create", when = ValidationState.NO_ERRORS)
    public void checkForDuplicateNotebookName(ValidationErrors errors) {
        if(RepositoryFactory.notebook().findByName(notebook.getName()).isPresent()) {
            errors.add("notebook.name", new SimpleError("A notebook already exists with that name"));
        }
    }
}
