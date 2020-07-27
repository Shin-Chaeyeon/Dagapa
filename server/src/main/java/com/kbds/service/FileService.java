package com.kbds.service;

import java.io.IOException;

public interface FileService<T> {
    public void fileSave(T vo) throws IOException;
}