package com.github.cstroe.sqs.model;

import java.time.LocalDateTime;
import java.util.Optional;

public interface Note {
    long getId();
    String getAuthor();
    LocalDateTime getCreated();
    String getTitle();
    String getContent();
    Optional<Notebook> getNotebook();
}