package com.test.file;

import com.test.file.data.FileCodeLinesData;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CodeLinesDataContainer {
    
    private boolean isSingleJavaFile;
    private Map<File, List<FileCodeLinesData>> codeLinesDataContainer = new HashMap<>();
    private FileCodeLinesData rootLinesData;
    
    public CodeLinesDataContainer(boolean isSingleJavaFile) {
        this.isSingleJavaFile = isSingleJavaFile;
    }
    
    public void addEntity(File parent, List<FileCodeLinesData> codeLinesData) {
        codeLinesDataContainer.put(parent, codeLinesData);
    }
    
    public void addRoot(FileCodeLinesData rootLinesData) {
        this.rootLinesData = rootLinesData;
    }
    
    public FileCodeLinesData getRootLinesData() {
        return rootLinesData;
    }
    
    public boolean isSingleJavaFile() {
        return isSingleJavaFile;
    }
    
    public FileCodeLinesData getSingleJavaFile(){
        File parentFile = codeLinesDataContainer.keySet().stream().findFirst().orElse(null);
        return isSingleJavaFile ? codeLinesDataContainer.get(parentFile).get(0) : null;
    }
    
    public List<FileCodeLinesData> getCodeLinesData(File inputFile) {
        return codeLinesDataContainer.getOrDefault(inputFile, Collections.emptyList());
    }
}
