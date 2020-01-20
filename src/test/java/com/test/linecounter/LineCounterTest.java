package com.test.linecounter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

public class LineCounterTest {
    
    public String getFileContent(String fileName) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
        try {
            return new String(Files.readAllBytes(file.toPath()), UTF_8);
        } catch (IOException e) {
            System.out.println("Failed to load file " + fileName);
            return "";
        }
    }
    
    @Test
    public void testAllIsCommentInputLineCounter() {
        long result = new JavaCodeLinesCounter().countLinesInCode(getFileContent("AllCommentInputForLinesTest.java"));
        Assertions.assertEquals(0, result);
    }
    
    @Test
    public void testEmptyInputLineCounter() {
        long result = new JavaCodeLinesCounter().countLinesInCode(getFileContent("EmptyInputForLinesTest.java"));
        Assertions.assertEquals(0, result);
    }
    
    @Test
    public void testEmptyLinesLineCounter() {
        long result = new JavaCodeLinesCounter().countLinesInCode(getFileContent("EmptyLinesInputForLinesTest.java"));
        Assertions.assertEquals(5, result);
    }
    
    @Test
    public void testSingleLineCommentLineCounter() {
        long result = new JavaCodeLinesCounter().countLinesInCode(getFileContent("SingleLineCommentInputForLinesTest.java"));
        Assertions.assertEquals(5, result);
    }
    
    @Test
    public void testBlockCommentLineCounter() {
        long result = new JavaCodeLinesCounter().countLinesInCode(getFileContent("BlockCommentInputForLinesTest.java"));
        Assertions.assertEquals(5, result);
    }
}
