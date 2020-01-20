package com.test.out;

import com.test.file.CodeLinesDataContainer;
import com.test.file.data.FileCodeLinesData;

import java.util.Objects;

public class CodeDataPrettyPrinter {
    
    public void print(CodeLinesDataContainer dataForPrint) {
        if (Objects.nonNull(dataForPrint)) {
            if (dataForPrint.isSingleJavaFile()) {
                System.out.println(dataForPrint.getSingleJavaFile());
            } else {
                prettyPrint(dataForPrint.getRootLinesData(), dataForPrint, 2);
            }
        } else {
            System.out.println("Unable to print code lines data. Input file is null.");
        }
    }
    
    private void prettyPrint(FileCodeLinesData currentLinesData, CodeLinesDataContainer allLinesData, int indent) {
        System.out.println(currentLinesData.toString());
        allLinesData.getCodeLinesData(currentLinesData.getReviewedFile())
                .forEach(codeLinesData -> printList(codeLinesData, allLinesData, indent));
    }
    
    private void printList(FileCodeLinesData codeLinesData, CodeLinesDataContainer allLinesData, int indent) {
        indent(indent);
        if (codeLinesData.getReviewedFile().isDirectory()) {
            prettyPrint(codeLinesData, allLinesData, indent + 2);
        } else {
            System.out.println(codeLinesData.toString());
        }
    }
    
    private void indent(int amount) {
        for (int i = 0; i < amount; i++) {
            System.out.print(' ');
        }
    }
    
}
