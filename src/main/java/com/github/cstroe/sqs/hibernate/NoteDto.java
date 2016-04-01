package com.github.cstroe.sqs.hibernate;

import com.github.cstroe.sqs.model.Notebook;
import com.github.cstroe.sqs.model.Note;
import com.google.common.base.Preconditions;

import java.time.LocalDateTime;
import java.util.Optional;

public class NoteDto implements Note {
    private long id;
    private String author;
    private LocalDateTime created;
    private String title;
    private String content;
    private Notebook notebook;

    private NoteDto() {}

    public NoteDto(long id, String author, LocalDateTime created, String title, String content, Notebook notebook) {
        Preconditions.checkArgument(id >= 0);
        Preconditions.checkNotNull(author);
        Preconditions.checkNotNull(created);
        Preconditions.checkNotNull(title);
        Preconditions.checkNotNull(content);
        this.id = id;
        this.author = author;
        this.created = created;
        this.title = title;
        this.content = content;
        this.notebook = notebook;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Optional<Notebook> getNotebook() {
        return Optional.ofNullable(notebook);
    }

    public void setNotebook(Notebook notebook) {
        this.notebook = notebook;
    }
}
