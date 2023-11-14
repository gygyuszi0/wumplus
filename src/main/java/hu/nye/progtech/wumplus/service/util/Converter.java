package hu.nye.progtech.wumplus.service.util;

/**
 * Különböző módon meghatározott elemeket konvertál.
 */
public class Converter {

    /**
     * Egy angol nagybetűről megmondja a sorszámát.
     *
     * @param letter Melyik betű?
     *
     * @return 1-től indexelve mi a kérdéses betű sorszáma?
     */
    public static Integer letterToInteger(Character letter) {
        Integer result;

        int letterCode = letter.charValue();
        result = letterCode - 64;

        return result;
    }
}
