package utils;

public class FileLineCounter {
    private Long currentLine = 0L;
    private Long positiveLoadLine = 0L;

    public void incrementCurrentLine() {
        ++currentLine;
    }
    public void incrementPositiveLoadLine() {
        ++positiveLoadLine;
    }

    public Long getCurrentLine() {
        return currentLine;
    }

    public Long getPositiveLoadLine() {
        return positiveLoadLine;
    }
}
