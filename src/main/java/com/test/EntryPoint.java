package com.test;

import java.io.IOException;

public class EntryPoint {
    
    public static void main(String[] args) throws IOException {
        if(validArgs(args)) {
            new LineCounterApp().process(args[0]);
        } else {
            System.out.println("Invalid input arguments. Expecting full path to file or folder.");
        }
    }
    
    private static boolean validArgs(String[] args) {
        return args.length == 1 && !args[0].isEmpty();
    }
}
