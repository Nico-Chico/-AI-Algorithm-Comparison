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
    - `java -jar MaximizationProblem.jar` It ask you for inputs.
    - `java -jar MaximizationProblem.jar inputs.txt` You can write inputs on a file and pass it as argument in the MaximizationProblem.jar call.
        
 2. Later you run show.py to show this solutions.

## Experiments:
   - [Testing experiments results](https://docs.google.com/document/d/13m4MrGQ6cvUipA4QvsIz_t6LTdsBxd5h8Y-B0tr3q1Q/edit?usp=sharing)
   - [Presentation](https://drive.google.com/file/d/1dmmDgCqEDRjhk4BC24o4qt20BamftTze/view?usp=sharing)

   We make a script to reapeat each experiments 50 times. Its auto-experiments.py on experiments/ folder

## Tasks
* [ ] Implements other test functions (No time)
