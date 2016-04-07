package com.github.cstroe.sqs.model;

import java.time.LocalDateTime;

public interface Note extends HasIdentity {
    LocalDateTime getCreated();
    String getTitle();
    String getContent();
    Notebook getNotebook();
}