# ASSIGNMENT 3-- Mancala
## Description



## Getting Started

### Dependencies

* Describe any prerequisites, libraries, OS version, etc., needed before installing and running your program.
* Necessary software:
    - scioer
    - docker container
    - java
    - another container that can run graphics


### Executing program

* How to build and run the program
1. clone program from git repository
2. enter GP4 directory
3. type ```gradle build``` to build program
4. type ```java -cp build/classes/java/main ui.TextUI``` to run text-based program
5. in a non-docker shell type ```java -jar 'pwd'/MancalaUI.jar``` to run GUI

* Expected output:

Would you like to play Ayo or Kalah (Ayo/Kalah): Kalah
Current Board State:

Player 1:
Pit  1: 4
Pit  2: 4
Pit  3: 4
Pit  4: 4
Pit  5: 4
Pit  6: 4
(Store: 0 stones)

Player 2:
Pit  7: 4
Pit  8: 4
Pit  9: 4
Pit 10: 4
Pit 11: 4
Pit 12: 4
(Store: 0 stones)


Player1 Turn. Enter Pit Number: 3
Current Board State:

Player 1:
Pit  1: 4
Pit  2: 4
Pit  3: 0
Pit  4: 5
Pit  5: 5
Pit  6: 5
(Store: 1 stones)

Player 2:
Pit  7: 4
Pit  8: 4
Pit  9: 4
Pit 10: 4
Pit 11: 4
Pit 12: 4
(Store: 0 stones)


Player1 Turn. Enter Pit Number: 1
Current Board State:

Player 1:
Pit  1: 0
Pit  2: 5
Pit  3: 1
Pit  4: 6
Pit  5: 6
Pit  6: 5
(Store: 1 stones)

Player 2:
Pit  7: 4
Pit  8: 4
Pit  9: 4
Pit 10: 4
Pit 11: 4
Pit 12: 4
(Store: 0 stones)


Player2 Turn. Enter Pit Number: 8
Current Board State:

Player 1:
Pit  1: 0
Pit  2: 5
Pit  3: 1
Pit  4: 6
Pit  5: 6
Pit  6: 5
(Store: 1 stones)

Player 2:
Pit  7: 4
Pit  8: 0
Pit  9: 5
Pit 10: 5
Pit 11: 5
Pit 12: 5
(Store: 0 stones)


Player1 Turn. Enter Pit Number: 
...

Player1 Turn. Enter Pit Number: ^Coer-gp4$ java -cp build/classes/java/main ui.TextUI
Would you like to play Ayo or Kalah (Ayo/Kalah): Ayo
Current Board State:

Player 1:
Pit  1: 4
Pit  2: 4
Pit  3: 4
Pit  4: 4
Pit  5: 4
Pit  6: 4
(Store: 0 stones)

Player 2:
Pit  7: 4
Pit  8: 4
Pit  9: 4
Pit 10: 4
Pit 11: 4
Pit 12: 4
(Store: 0 stones)


Player1 Turn. Enter Pit Number: 3
Current Board State:

Player 1:
Pit  1: 4
Pit  2: 4
Pit  3: 0
Pit  4: 5
Pit  5: 5
Pit  6: 0
(Store: 6 stones)

Player 2:
Pit  7: 0
Pit  8: 4
Pit  9: 4
Pit 10: 4
Pit 11: 4
Pit 12: 4
(Store: 0 stones)


Player2 Turn. Enter Pit Number: 12
Current Board State:

Player 1:
Pit  1: 5
Pit  2: 5
Pit  3: 1
Pit  4: 1
Pit  5: 6
Pit  6: 1
(Store: 6 stones)

Player 2:
Pit  7: 1
Pit  8: 1
Pit  9: 5
Pit 10: 5
Pit 11: 1
Pit 12: 1
(Store: 2 stones)


Player1 Turn. Enter Pit Number: 

(WHEN RUNNING THE JAR FILE, OUTPUT SHOULD REFLECT THIS)

## Limitations

Cannot properly load old games and players. However, does save the files

## Author Information

Your name and contact information including your email address
Name: Amy Beard
Email: amybeard66@icloud.com

