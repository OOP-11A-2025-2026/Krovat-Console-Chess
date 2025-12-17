# Krovat-Console-Chess
The third project of team Krovat for OOP class

## (BG)

### 1. Описание
Krovat-Console-Chess е конзолен шах, който поддържа:
- Стандартни правила на шаха
- Проверка за шах, шахмат и пат
- Рокада, ан пасан и промоция на пешка
- Запазване и зареждане на игра
- Връщане един ход назад (Undo)
- Предлагане на реми и предаване


### 2. Инструкции за използване - инсталация и стартиране
Изисквания:
- Java 25
- Конзола/терминал или IDE (IntelliJ IDEA, Eclipse и др.)

Стартиране от конзола в директорията, където се намира проекта:\
javac *.java\
java Main

Стартиране от IDE:\
1. Отворете проекта в IDE
2. Намерете класа Main
3. Стартирайте метода main()
4. Избор за зареждане на дъска от PGN нотация или начало на нова
5. Главен цикъл - Избор на ход (Move, Save, Draw, Resign)
6. Move - Задайте валиден ход (SAN / алгебрична нотация)
7. Save - Дъската се запазва в PGN формат
8. Draw - Предлага на опонента Реми
9. Resign - Предавате се, опонентът печели служебно
10. Докато не се стигне до Mate, Stalemate, Draw или Resign - изпълнение на главния цикъл
11. Бели фигури (главни букви) - P (Pawn), R (Rook), N (Knight), B (Bishop), Q (Queen), K (King)
12. Черни фигури (малки букви) - p (Pawn), r (Rook), n (Knight), b (Bishop), q (Queen), k (King)

### 3. Алгоритъм
State diagram:
![img.png](img.png)


### 4. Обяснение на кода


### 5. Екип. Разпределение на работата
- Иванета - Board instructions, constructors, toString, getKingCoordinates, checkCheck, checkCollision, saveUndoState, undoMove, makeMove and isLegalMove methods, Piece, Coordinates, Queen, copy method and copy constructors in all pieces, InvalidMove, README, Game interpretMove
- Виктор - Knight, King, Board checkMate, checkStalemate and hasValidMoves, README, presentation
- Самуил - Pawn, Board promotion, checkEnPassant, enPassant, getPiece, resetAllEnPassantEligibility and checkPin, Game interpretMove (first version)
- Игнат - Rook, Bishop, Board checkCastle and castle