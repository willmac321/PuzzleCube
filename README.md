# PuzzleCube
Creates a working representation of a 3x3 puzzle cube with a UI in Java.  Change colors of the cube by clicking the faces, there can only be a maximum of 9 of each color.

Dataset creation option on the UI will output a .csv file of specified name.

Input Max Moves\n
	is the amount of moves that will be randomly chosen and logged
	will also cease choosing moves if the cube is solved and log the result as true/false

Input Maximum Random Turns\n
	the amount of randomization (moves) to perform from the solved state

Maximum Iterations\n
	the amount of times the cube will be re-randomized and then have random turns applied

This is a work in progress currently, as the end result will be to generate a model to suggest moves to solve a cube.
Also, I might make the UI a little prettier...

![](/PuzzleUI.PNG)
