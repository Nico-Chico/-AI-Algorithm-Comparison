import os
 
os.chdir("exp1/") # Star the script on the experiments folder
 
 ## Create a list with all subdirectories
folders = []
for entry in os.scandir('./'): 
    if entry.is_dir():
        folders.append(entry.path)

for i in folders:
    print("EXPERIMENT " + i)
    os.chdir(i)
    totalTime=0.0
    pointTime=0.0
    solution=0.0
    N=20     ## Number of repetitions of the test
    print("Repeating experiment "+str(N)+" times -- Please wait") 
    for i in range(1, N):
        os.system("java -jar ../../../MaximizationProblem.jar input.txt >/dev/null")
        os.system("tail -n3 solutions.txt > values.txt")
        fp = open('values.txt', 'r')
        line = fp.readline().replace('\n', '') #Removing newline
        s = line.split(': ')
        aux = s[1]
        totalTime = totalTime + (float(aux) / N);
        
        line = fp.readline().replace('\n', '') #Removing newline
        s = line.split(': ')
        aux = s[1]
        pointTime = pointTime + (float(aux) / N);
        
        line = fp.readline().replace('\n', '') #Removing newline
        line = line.split(': ')
        sol = line[1].split()
        aux = float(sol[2])
        solution = solution + (float(aux) / N);
    
    os.system("echo "+ str(totalTime) +" >> results.txt")
    os.system("echo "+ str(pointTime) +" >> results.txt")
    os.system("echo "+ str(solution) +" >> results.txt")
    
    print("Measured results saves in results.txt file:")
    print("------------------------------")
    os.system("cat results.txt")
    print("")
    os.system("rm values.txt")
    os.chdir("../")





