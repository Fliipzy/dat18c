# 0 – 100+ a dice game 

## Exercise : 
Design and implement a program, which can play the 0 – 100+ dice game (rules
explained below) against another program (2 player version).
In each turn it receive the opponents Total Score (input), and then it plays the game
using a fair, random 6 sided dice 

The running program must show, what it
throws, the Running Sum and Total Score.
All code must be public to everyone.
Then we will make a little tournament ;-)

## Rules : 
One 6 sided dice.
The player begin each turn with 0 points in his or her Running Sum.
The player can throw the dice and add the resulting number to the Running Sum, as
long as no 1’s are the result. I.e. 2, 3, 4, 5 or 6 can be added.
If 1 is the result, the player turn is over AND the Running Sum is 0 (i.e. lost).
If the player scores (did not roll a 1) he / she decides either to Stop or Continue.
If Stop is chosen, the Running Sum will be added to the Total Score.
If Continue is chosen, then a new throw is done (see above) with a chance to gain
more or the risk to loose the running score.

When a player end the turn with a Total Score on 100 or more every other player get
one more chance to score.
The winner is the player with the highest score, which is not necessarily the first one
to pass 100.

## Note :
You do not have to stop just because you are on 100 or over.
Your strategy could very well depend upon your opponents score.