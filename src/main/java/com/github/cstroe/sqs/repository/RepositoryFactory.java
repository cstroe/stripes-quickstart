package com.github.cstroe.sqs.repository;

public class RepositoryFactory {
    public static NotebookRepository notebook() {
        return new NotebookRepository();
    }
}
