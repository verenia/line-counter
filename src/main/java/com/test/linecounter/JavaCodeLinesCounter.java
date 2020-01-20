package com.test.linecounter;

import java.util.Arrays;
import java.util.regex.Pattern;

public class JavaCodeLinesCounter {
    
    private static final Pattern EMPTY_LINE_REGEX = Pattern.compile("^\\s*$");
    private static final Pattern ONE_LINE_COMMENT_REGEX = Pattern.compile("^\\s*//.*");
    private static final Pattern MULTILINE_COMMENT_REGEX =
            Pattern.compile("(\"(?:\\\\[^\"]|\\\\\"|.)*?\")|(/\\*(?:.|\\R)*?\\*/)");
    
    public long countLinesInCode(String fileContentStr) {
        return Arrays.stream(removeBlockComments(fileContentStr).split(System.lineSeparator()))
                .filter(this::notEmptyLine)
                .filter(this::notOneLineCommentLine)
                .count();
    }
    
    private String removeBlockComments(String fileContentStr) {
        return MULTILINE_COMMENT_REGEX.matcher(fileContentStr).replaceAll("$1");
    }
    
    private boolean notEmptyLine(String line) {
        return !EMPTY_LINE_REGEX.matcher(line).matches();
    }
    
    private boolean notOneLineCommentLine(String line) {
        return !ONE_LINE_COMMENT_REGEX.matcher(line).matches();
    }
    
}
