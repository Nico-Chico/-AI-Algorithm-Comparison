# AI-Algorithm-Comparison
This project pretend to be useful for the study of differents algorithms used in Artificial Inteligence
    
## Structure
The project consists of two pieces of software:
*  `MaximizationProblem.jar`:
        The backend part writed in JAVA its were alorithims are implemented.
        We have 3 algorithms implemented right now: Random, Hill Climbing and PSO. You can choose between them changing "mode" parameters in prompt.
        The program run the selected algorithm and write a file with the evolutions of the solutions.     
        Right now we only have one test function implemented on our programs that is: sin(sqrt(x² + y²)
        We will to include more test functions later

*  `show.py`:
        The frontend part.  The job of this file is to read the solutions file and show the evolution of the solutions graphically.

## Dependencies
 1. You need python3
 2. We use "matplotlib" library in his last stable version. To install it without problems: `python3 -m pip install -U matplotlib`


## How to run the program?
 1. First you need run MaximizationProblem.jar for generate the solutions file.
 2. Later you run show.py to show this solutions.

## Tasks

**· On JAVA**
* [ ] For All: LastBest (LastBestGlobal on PSO)
* [ ] For All: OptimalTime (Con IdealBestGlobal * 0.1%(Umbral))
* [ ] For All: TotalTime
* [ ] Implements other test functions 



**· On Python**
* [ ] Fix last timestep PSO
* [ ] Show best solution on random
* [ ] Show time, best found, ideal best 
* [ ] Implements other test functions
* [ ] Mostrar solución real, distancia entre estas y las soluciones.
* [ ] GUI(Dudo que de tiempo)




**· FINAL PRESENTATION**
