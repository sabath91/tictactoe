### Main non-functional ones


| Name          | Date           | Comments  |
| ------------- |:-------------:| -----:|
| It must work (it it doesn’t, it’s disqualified): if I cannot play, it doesn't work | TBS | No comments |
| 60% unit test code coverage | TBS |  No comments |
| Functions in accordance with functional requirements | TBS |  No comments |
| Code quality – non-OO code is tolerated in little amounts | TBS |  No comments |
| **Project mantra** followed (Git, Maven, test cases, etc.) | TBS |  No comments |
| Console UI, no graphics (though see extra reqs) | TBS |  No comments |
| Hot-seat game, no network (though see extra reqs) | TBS |  No comments |
| Both players are human (though see extra reqs) | TBS |  No comments |
| only Java API (JDK8, SE) and TestNG | TBS |  No comments |


### About the game itself

Functional requirements.

| Name          | Date           | Comments  |
| ------------- |:-------------:| -----:|
| It is "best of three", though I can quit mid-way through | TBS | No comments |
| Characters: O (naught) and X (cross) | TBS | No comments |
| Players have names and scores | TBS | No comments |
| Winner has better score. Draw is possible | TBS | No comments |
| Interactive: it should accept players instructions about each move | TBS| No comments| 
| Interactive: it should ask who begins | TBS| No comments| 
| Interactive: it informs about session result, who’s turn it is now and the like | TBS| No comments| 
| Match gives points: win 3, draw 1, loss 0. 3 matches == game | TBS | No comments |
| Game works with square or rectangular board | TBS | No comments |
| Player wins, if he has unbroken line of his characters, in a row, in a column or diagonally | TBS | No comments |
| Winning is announced in a message: Wygrywa O. O: 1 X: 0 (numbers are current points) | TBS | No comments |
| Game is configurable: Board dimensions: 3x3, 4x4, 99x101, etc. (user provides) | TBS | No comments |
| Game is configurable: Winning condition has variable number of characters: 3, 4, 5, etc. (user provides) | TBS | No comments |
| Game is configurable: Game messages should have configurable target: console (System.out) or logs (for the sake of this exercise it’s OK to make it System.err), or external printer | TBS | No comments |
| Game is configurable: before game starts it asks who goes first, O or X | TBS | No comments |
| We are bi-lingual: Polish and English are fine. In future we want to add more languages: messages are to be read from a file for chosen language. Choosing the language depends on configuration variable | TBS | No comments |
