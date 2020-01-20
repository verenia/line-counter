package com.test;

import com.test.file.CodeLinesDataContainer;
import com.test.file.CodeLinesFilesReviewer;
import com.test.linecounter.JavaCodeLinesCounter;
import com.test.out.CodeDataPrettyPrinter;

public class LineCounterApp {
    
    private CodeLinesFilesReviewer filesReviewer;
    private CodeDataPrettyPrinter prettyPrinter;
    
    public LineCounterApp() {
        this.filesReviewer = new CodeLinesFilesReviewer(
                new JavaCodeLinesCounter()
        );
        this.prettyPrinter = new CodeDataPrettyPrinter();
    }
    
    public void process(String inputFilePathStr) {
        CodeLinesDataContainer reviewedCodeData = filesReviewer.reviewFilesIn(inputFilePathStr);
        prettyPrinter.print(reviewedCodeData);
    }
}
