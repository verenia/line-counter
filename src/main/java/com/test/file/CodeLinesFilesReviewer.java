package com.test.file;

import com.test.file.data.FileCodeLinesData;
import com.test.linecounter.JavaCodeLinesCounter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CodeLinesFilesReviewer {
    
    private CodeLinesDataContainer codeLinesDataContainer;
    private JavaCodeLinesCounter codeLinesCounter;
    
    public CodeLinesFilesReviewer(
            JavaCodeLinesCounter codeLinesCounter) {
        this.codeLinesCounter = codeLinesCounter;
    }
    
    public CodeLinesDataContainer reviewFilesIn(String pathStr) {
        File inputFile = new File(pathStr);
        if (!inputFile.exists()) {
            System.out.println("Input file " + inputFile.getName() + " does not exist.");
            return null;
        }
        
        codeLinesDataContainer = new CodeLinesDataContainer(isJavaFile(inputFile));
        codeLinesDataContainer.addRoot(
                reviewFile(inputFile)
        );
        
        return codeLinesDataContainer;
    }
    
    private FileCodeLinesData reviewFile(File inputFile) {
        if (inputFile.isDirectory()) {
            return processDirectory(inputFile);
        } else if (isJavaFile(inputFile)) {
            return processSingleFile(inputFile);
        }
        
        return null;
    }
    
    private FileCodeLinesData processDirectory(File inputFile) {
        List<FileCodeLinesData> linesData = new ArrayList<>();
        for (File innerFile : inputFile.listFiles()) {
            linesData.add(reviewFile(innerFile));
        }
        codeLinesDataContainer.addEntity(inputFile, linesData);
        
        long totalLinesCount = linesData.stream()
                .map(FileCodeLinesData::getCodeLines)
                .reduce((leftLines, rightLines) -> leftLines + rightLines)
                .orElse(0L);
        return new FileCodeLinesData(inputFile, totalLinesCount);
    }
    
    private FileCodeLinesData processSingleFile(File javaFile) {
        long codeLines = codeLinesCounter.countLinesInCode(readFileContent(javaFile));
        FileCodeLinesData codeLinesData = new FileCodeLinesData(javaFile, codeLines);
        codeLinesDataContainer.addEntity(javaFile.getParentFile(), Collections.singletonList(codeLinesData));
        return codeLinesData;
    }
    
    private String readFileContent(File javaFile) {
        try {
            return new String(Files.readAllBytes(javaFile.toPath()), UTF_8);
        } catch (IOException e) {
            System.out.format("Failed to load contents of %s. Exception: %s", javaFile.getName(), e.getMessage());
            return "";
        }
    }
    
    private boolean isJavaFile(File startFile) {
        return startFile.getName().endsWith(".java");
    }
    
}
