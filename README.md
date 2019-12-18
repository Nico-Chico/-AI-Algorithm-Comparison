# AI-Algorithm-Comparison
    This project pretend to be useful for the study of differents algorithms used in Artificial Inteligence
    
# Structure
    The project consists of two pieces of software:
    - main.java:
        The backend part writed in JAVA its were alorithims are implemented.
        We have 3 algorithms implemented right now: Random, Hill Climbing and PSO. You can choose between them changing "mode" parameters in the top of the main.java file.
        The program run the selected algorithm and write a file with the evolutions of the solutions.     
        Right now we only have one test function implemented on our programs that is: sin(sqrt(x² + y²)
        We will to include more test functions later

    - function.py and functionPSO.py:
        The frontend part.  The job of this file is to read the solutions file and show the evolution of the solutions graphically.
        Greedy and Random algoritms solutions must be showed with function.py but PSO solutions need to be showed by functionPSO.py.
        We will combine this files later to make the program simpler.

Actually PSO is not fully functional but But everything necessary to show the evolution of the particles is already implemented in functionPSO.py. It would be missing that java.main spits out the solutions of the PSO and that function.py read them correctly.


# Tasks
* [ ] Fix PSO on Java
* [ ] Fix main.java mode 0 bug
* [ ] Make parser on python for PSO solutions file
* [ ] Implements other test functions
* [ ] Registrar los tiempos que tarda cada algoritmo. Y compararlos
* [ ] Mostrar solución real, distancia entre estas y las soluciones.
* [ ] Hacer una GUI donde incrustar el gráfico y donde podamos modificar los parámetros y lanzar los algoritmos. (Límites, Función, etc...) (Para esto se puede usar Tkinter creo.)
* [ ] Final Presentation


