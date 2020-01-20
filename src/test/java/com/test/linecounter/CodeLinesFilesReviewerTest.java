package com.test.linecounter;

import com.test.file.CodeLinesDataContainer;
import com.test.file.CodeLinesFilesReviewer;
import com.test.file.data.FileCodeLinesData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class CodeLinesFilesReviewerTest {
    
    public File getFile(String fileName) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
    }
    
    @Test
    public void testSuccessFolderReviewer() {
        CodeLinesFilesReviewer filesReviewer = new CodeLinesFilesReviewer(new JavaCodeLinesCounter());
    
        CodeLinesDataContainer dataContainer =
                filesReviewer.reviewFilesIn(getFile("linesreviewer").getPath());
    
        Assertions.assertFalse(dataContainer.isSingleJavaFile());
        Assertions.assertNull(dataContainer.getSingleJavaFile());
        
        FileCodeLinesData expectedRootCodeLinesData
                = new FileCodeLinesData(getFile("linesreviewer"), 30);
        Assertions.assertEquals(expectedRootCodeLinesData, dataContainer.getRootLinesData());
    
        testFilesInFirstFolder(dataContainer);
        testFilesInSecondFolder(dataContainer);
    }
    
    @Test
    public void testSuccessSingleFileReviewer(){
        CodeLinesFilesReviewer filesReviewer = new CodeLinesFilesReviewer(new JavaCodeLinesCounter());
        CodeLinesDataContainer dataContainer =
                filesReviewer.reviewFilesIn(getFile("linesreviewer/second/SecondInSecond.java").getPath());
    
        FileCodeLinesData expectedSingleFile = new FileCodeLinesData(
                getFile("linesreviewer/second/SecondInSecond.java"),
                9
        );
        Assertions.assertTrue(dataContainer.isSingleJavaFile());
        Assertions.assertEquals(expectedSingleFile, dataContainer.getSingleJavaFile());
    }
    
    @Test
    public void testFileDoesNotExist(){
        CodeLinesFilesReviewer filesReviewer = new CodeLinesFilesReviewer(new JavaCodeLinesCounter());
        CodeLinesDataContainer dataContainer =
                filesReviewer.reviewFilesIn("some/unexisting/path");
        Assertions.assertNull(dataContainer);
    }
    
    private void testFilesInFirstFolder(CodeLinesDataContainer dataContainer){
        List<FileCodeLinesData> codeLinesDataFirstActual =
                dataContainer.getCodeLinesData(getFile("linesreviewer/first"));
        Assertions.assertEquals(2, codeLinesDataFirstActual.size());
    
        FileCodeLinesData expectedFirstInFirst = new FileCodeLinesData(
                getFile("linesreviewer/first/FirstInFirst.java"),
                6
        );
        Assertions.assertEquals(expectedFirstInFirst, codeLinesDataFirstActual.get(0));
    
        FileCodeLinesData expectedSecondInFirst = new FileCodeLinesData(
                getFile("linesreviewer/first/SecondInFirst.java"),
                7
        );
        Assertions.assertEquals(expectedSecondInFirst, codeLinesDataFirstActual.get(1));
    }
    
    private void testFilesInSecondFolder(CodeLinesDataContainer dataContainer){
        List<FileCodeLinesData> codeLinesDataSecondActual =
                dataContainer.getCodeLinesData(getFile("linesreviewer/second"));
        Assertions.assertEquals(2, codeLinesDataSecondActual.size());
        
        FileCodeLinesData expectedFirstInFirst = new FileCodeLinesData(
                getFile("linesreviewer/second/FirstInSecond.java"),
                8
        );
        Assertions.assertEquals(expectedFirstInFirst, codeLinesDataSecondActual.get(0));
        
        FileCodeLinesData expectedSecondInFirst = new FileCodeLinesData(
                getFile("linesreviewer/second/SecondInSecond.java"),
                9
        );
        Assertions.assertEquals(expectedSecondInFirst, codeLinesDataSecondActual.get(1));
    }
}
