# AI-Algorithm-Comparison
This project pretend to be useful for the study of differents algorithms used in Artificial Inteligence
    
## Structure
The project consists of two pieces of software:
*  `MaximizationProblem.jar`:
        The backend part writed in JAVA its were alorithims are implemented.
        We have 3 algorithms implemented right now: Random, Hill Climbing and PSO. You can choose between them changing "mode" parameters in prompt.
        The program run the selected algorithm and write a file with the evolutions of the solutions.     
        Right now we only have one test function implemented on our programs that is: sin(sqrt(x² + y²)

*  `show.py`:
        The frontend part.  The job of this file is to read the solutions file and show the evolution of the solutions graphically.

## Dependencies
 1. You need python3
 2. We use "matplotlib" library in his last stable version. To install it without problems: `python3 -m pip install -U matplotlib`


## How to run the program?
 1. First you need run MaximizationProblem.jar for generate the solutions file.
        'java -jar MaximizationProblem.jar' It ask you for inputs.
    - You can write inputs on a file and pass it as argument in the MaximizationProblem.jar call.
        'java -jar MaximizationProblem.jar inputs.txt'

 2. Later you run show.py to show this solutions.

## Tasks
* [ ] Implements other test functions
* [ ] FINAL PRESENTATION
