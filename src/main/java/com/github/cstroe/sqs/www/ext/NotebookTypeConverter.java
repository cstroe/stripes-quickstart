package com.github.cstroe.sqs.www.ext;

import com.github.cstroe.sqs.dao.NotebookDao;
import com.github.cstroe.sqs.model.Notebook;
import com.github.cstroe.sqs.repository.RepositoryFactory;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.TypeConverter;
import net.sourceforge.stripes.validation.ValidationError;

import java.util.Collection;
import java.util.Locale;
import java.util.Optional;

public class NotebookTypeConverter implements TypeConverter<Notebook> {

    @Override
    public void setLocale(Locale locale) {}

    @Override
    public Notebook convert(String input, Class<? extends Notebook> targetType, Collection<ValidationError> errors) {
        long id;
        try {
            id = Long.parseLong(input);
        } catch (NumberFormatException ignored) {
            errors.add(new SimpleError("Could not parse notebook id"));
            return null;
        }

        Optional<NotebookDao> maybeNotebook = RepositoryFactory.notebook().findById(id);
        if(!maybeNotebook.isPresent()) {
            errors.add(new SimpleError("Could not find notebook with the given id"));
            return null;
        }

        return maybeNotebook.get();
    }
}
