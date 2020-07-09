package service;

import utils.FileLineCounter;

public interface FileService<T> {
    void execute(T fileLines, FileLineCounter counter);
}
