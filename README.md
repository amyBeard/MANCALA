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
4. type ```java -cp build/classes/java/main ui.TextUI``` to run program
5. in a non-docker shell type ```java -jar build/libs/MancalaUI.jar``` to run GUI

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
Student ID: 1217070
Email: abeard02@uoguelph.ca

## Development History

Keep a log of what things you accomplish when.  You can use git's tagging feature to tag the versions or you can reference commits.



Fixing using PMD

Commit: 738e7c7d
Authored by: Amy Beard
5 minutes ago
Added functionality for Ayo

Commit: ad16eac3
Authored by: Amy Beard
1 hour ago
Updated build.gradle file to work with GUI

Commit: 7d36891a
Authored by: Amy Beard
1 hour ago
GUI works, working on loading old games and players

Commit: fab66d5b
Authored by: Amy Beard
1 hour ago
25 Nov, 2023 - 3 commits

Added different implementation to let the player take another turn

Commit: 5c851d2f
Authored by: Amy Beard
1 day ago
Removing docs folder

Commit: 9491d195
Authored by: Amy Beard
1 day ago
Added second rule set, will start on GUI

Commit: 39345a03
Authored by: Amy Beard
1 day ago
23 Nov, 2023 - 1 commit

Added serialization, saver class, and user profile class
Commit: 58fc72ce
Authored by: Amy Beard
3 days ago
22 Nov, 2023 - 3 commits

Finished part 1

Commit: 6446ad13
Authored by: Amy Beard
4 days ago
Board refactored

Commit: 973ff3f2
Authored by: Amy Beard
4 days ago
Working on refactoring still // fixing errors

Commit: 425dcbe8
Authored by: Amy Beard
4 days ago
21 Nov, 2023 - 2 commits

Refactoring Board

Commit: 891dbe65
Authored by: Amy Beard
5 days ago
Updated GameRules

Commit: c9825104
Authored by: Amy Beard
5 days ago
20 Nov, 2023 - 9 commits

Refactored MancalaGame to use single responsibility and added some files

Commit: 38456f7d
Authored by: Amy Beard
6 days ago
Removed build file

Commit: 44a59a46
Authored by: Amy Beard
6 days ago
Condensed function to one line

Commit: 8a60faf0
Authored by: Amy Beard
6 days ago
Updated gradle file to include gradle echo

Commit: c98a8680
Authored by: Amy Beard
6 days ago
Working through PMD results

Commit: 63f6c6b2
Authored by: Amy Beard
6 days ago
Adding extra files from A3 description

Commit: 3fd36b77
Authored by: Amy Beard
6 days ago
Adding new gradle file

Commit: 40e085bd
Authored by: Amy Beard
6 days ago
Adding files from A2

Commit: b06a0a47
Authored by: Amy Beard
6 days ago


## Acknowledgments