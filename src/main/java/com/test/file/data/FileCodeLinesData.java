package com.test.file.data;

import java.io.File;

public class FileCodeLinesData {
    
    private File reviewedFile;
    private long codeLines;
    
    public File getReviewedFile() {
        return reviewedFile;
    }
    
    public long getCodeLines() {
        return codeLines;
    }
    
    public FileCodeLinesData(File file, long codeLines) {
        this.reviewedFile = file;
        this.codeLines = codeLines;
    }
    
    @Override
    public String toString() {
        return String.format("%s : %d", reviewedFile.getName(), codeLines);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileCodeLinesData that = (FileCodeLinesData) o;
        return codeLines == that.codeLines &&
                reviewedFile.equals(that.reviewedFile);
    }
}
