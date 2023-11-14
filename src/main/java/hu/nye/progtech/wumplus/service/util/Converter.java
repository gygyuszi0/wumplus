package hu.nye.progtech.wumplus.service.util;

public class Converter {

    public static Integer LetterToInteger (Character letter){
        Integer result;

        int letterCode = letter.charValue();
        result = letterCode - 64;

        return result;
    }
}
