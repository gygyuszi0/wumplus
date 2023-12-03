package hu.nye.progtech.wumplus.service.util;

import hu.nye.progtech.wumplus.model.MapVO;

/**
 * Elemeket számoló osztály.
 */
public class NumberOfElement {
    /**
     * Privát constructor.
     */
    private NumberOfElement() {
    }

    /**
     * Elemekest számoló osztály.
     *
     * @param mapVO Melyik mapen keresi
     *
     * @param element Melyik elemet
     *
     * @return Darabszám
     */
    public static Integer count(MapVO mapVO, Character element) {
        Integer countOfElement = 0;
        Integer rowNumber = mapVO.getNumberOfRows();
        Integer colNumber = mapVO.getNumberOfColumns();
        char[][] map = mapVO.getMap();

        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < colNumber; j++) {
                if (map[i][j] == element) {
                    countOfElement++;
                }
            }
        }

        return countOfElement;
    }
}
