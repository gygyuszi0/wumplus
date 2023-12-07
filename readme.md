# Döntések indoklása

- Performer-osztályban és Main osztályban hívódik konstruktort,\
    a sudoku-repo mintájára, spring hiányában
  - Main osztályban az induló információk inicializálására, 
  - Performer osztályban a módosítások rögzítésére
- Feltételezi a program az adatbázis létezését biztonsági okokból.

# A játékról röviden 

1. Create new map:\
    Nincs implementálva, a feladat szerint elegendő a szerkesztés \
    és a fileból olvasás közül az egyik.\
    Ezt a menüpontot választva a logot ír a játék, majd visszatér a főmenübe.
2. Read from file:\
    Beolvassa a mapet a resource-mappából, logol, majd visszatér a főmenübe.
3. Load from database:\
    Ha létezik ilyen játékos visszatér a játék mentett változatával.
    Ha nem hibát logol és visszatér a főmenübe.
    Itt egyszerre olvas adatbázisból és json-ből is,  
4. Save to database:\
    A feladatban elő volt írva ennek a menüpontnak a létezése,\ 
    ezért a pause parancs nem ment automatikusan, egyébként nem lenne funkciója ennek a pontnak.\
    Menti a játék állapotát, amely visszatér a menübe.
5. Play:\
    Ha nincs beolvasva egyetlen map sem, hibát logol majd visszatér a menübe.\
    Ha van elkezdődik a játék, lehetséges parancsok:
    - step : lép a játékos menet irányába (HUD-on írja)
    - turn L : balra fordul
    - turn R : jobbra fordul
    - loot : megpróbálja felvenni a játékos előtti mezőről a goldot,\
          a feladatban nem volt egyértelműen meghatározva,\
          hogy melyik mezőről vegye fel, így ez tűnt logikusnak.
    - pause : kilép a menübe, megőrzi a játék-állást, itt menthető adatbázisba
    - give up : kilép a menübe, visszatér a játék kezdeti állására.