package com.github.cstroe.sqs.model;

import java.time.LocalDateTime;
import java.util.Optional;

public interface Note extends HasIdentity {
    String getAuthor();
    LocalDateTime getCreated();
    String getTitle();
    String getContent();
    Optional<Notebook> getNotebook();
}