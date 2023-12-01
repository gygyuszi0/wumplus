# Döntések indoklása

- Performer-osztályban és Main osztályban hívódik konstruktort,\
    a sudoku-repo mintájára, spring hiányában
  - Main osztályban az induló információk inicializálására, 
  - Performer osztályban a módosítások rögzítésére
- Feltételezi a program az adatbázis létezését biztonsági okokból.
- Menü opciók:
  - File olvasás : 
    - reader, parser és validator osztályok függnek file-tól.\
        így main osztályban nincs inicializálva, használat közben\
        ha sérül a file a többi funkció működhet és a játék játszható.
- Tesztek:
  - Harmadik videó tanulsága alapján a ui és az io nincs tesztelve
  - Konzultáció tanulsága alapján az exception osztályok sincsenek