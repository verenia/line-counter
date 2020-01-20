public class InputForTest {
    
    /*
    some block comment
     */ // line comment after block
    // comment 1
    /*
        some block
    * */ /*
        another block
    */
    public void/*inline block*/ justSomeMethod() {
        someStatement.call("in string false comment /*");/*
            block comment after statement
        */
        // comment2
    }
}
