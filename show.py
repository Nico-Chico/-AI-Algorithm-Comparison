import numpy as np
import matplotlib.pyplot as plt
from matplotlib import cm
from mpl_toolkits.mplot3d import Axes3D

# Reading data
# Open solutions file and reading limits and mode
fp = open('solutions.txt', 'r')
minXlim = int(fp.readline()) - 2
maxXlim = int(fp.readline()) + 2
minYlim = int(fp.readline()) - 2
maxYlim = int(fp.readline()) + 2
mode = int(fp.readline())
particles= int(fp.readline())
timeSteps = int(fp.readline())
minZlim = -8
maxZlim = 8

#Setting figure and axis
fig = plt.figure()#fig = plt.figure(figsize=(16,12))
ax = fig.gca(projection='3d')

ax.set_xlim([minXlim,maxXlim])
ax.set_ylim([minYlim,maxYlim])
ax.set_zlim([minZlim,maxZlim])
 
 
#Set plot in interactive mode
plt.ion()
#Open the plot
plt.show()


# Setting titles
if mode == 0:
    title = "Using Hill Climbing"
elif mode == 1:
    title = "Using Random"
elif mode == 2:
    title = "Using Particle Swarm Optimization"
fig.suptitle(title, fontsize=14, fontweight='bold')
ax.text(0, 0, 0, "Central point", size=8, zorder=1, color='k')




# Function
X = np.arange(minXlim, maxXlim, 0.25)
Y = np.arange(minYlim, maxYlim, 0.25)
X, Y = np.meshgrid(X, Y)
Z = np.sin(np.sqrt(X**2 + Y**2))

# Plot the surface.
surf = ax.plot_surface(X, Y, Z, cmap=cm.coolwarm, linewidth=0, antialiased=True, alpha=.3)





if mode == 0 or mode == 1:

    print("Solutions in the file:")
    print("-----------------------")
    scatPrev = ax.scatter(0, 0, 0) # Para que el objeto exista y eliminarlo al empezar el bucle
    scatNow  = ax.scatter(0, 0, 0) # Para que el objeto exista y eliminarlo al empezar el bucle
    prevID = -1
    prev_coords = [99, 99, 99]
    color = np.random.rand(3,)
    transp = 0.5
    line = fp.readline()
    while line != "END\n":
        s = line.split(') ')
        ID = int(s[0])
        transp = 0.5
        if ID != prevID:
            scatSol = ax.scatter(prev_coords[0], prev_coords[1], prev_coords[2], s=40, c=color) # Guardo el anterior valor como solución
            color = np.random.rand(3,)
            transp = 0.0
        scatPrev.remove() # Clear the points on the screen
        scatNow.remove() # Clear the points on the screen
        s2 = s[1].split()
        t = int(s2[0])
        coords = [float(s2[1]), float(s2[2]), float(s2[3])]
        print(ID, coords)
        scatPrev = ax.scatter(prev_coords[0], prev_coords[1], prev_coords[2], s=40, c=color, alpha=transp) #Update the plot becouse we are in interactive mode
        scatNow = ax.scatter(coords[0], coords[1], coords[2], s=60, c=color) #Update the plot becouse we are in interactive mode
        prev_coords = coords
        prevID = ID
        plt.pause(0.5)
        line = fp.readline()
    print("End of the file")

elif mode == 2:

    # Inicialiting data matrix
    #particles, timeSteps = 10, 30;
    colors = np.random.rand(particles)
    data =  np.zeros((particles,timeSteps,3))


    # # Random points
    # particles = 4; ## Amount of particles
    # timeSteps = 10;
    # colors = ['b', 'r', 'g', 'y']
    # colors = np.random.rand(particles)
    # data = np.random.uniform(-10, 10, size=(particles, timeSteps, 3)) # (particle, timestep, coordinates)


    # READING DATA
    line = fp.readline()
    while line != "END\n":
        s = line.split(') ')
        ID = int(s[0])
        s2 = s[1].split()
        t = int(s2[0])
        coords = [float(s2[1]), float(s2[2]), float(s2[3])]
        data[ID][t][0] = coords[0]
        data[ID][t][1] = coords[1]
        data[ID][t][2] = coords[2]
        line = fp.readline()

    
    # Plotting
    scat = ax.scatter(0, 0, 0, s=0) #Update the plot becouse we are in interactive mode
    for t in list(range(timeSteps)):
        scat.remove()
        x = data[:,t,0]
        y = data[:,t,1]
        z = data[:,t,2]
        scat = ax.scatter(x, y, z, s=60, c=colors) #Update the plot becouse we are in interactive mode
        plt.pause(0.2)

# Ending
totalTime = str(0.4321)
optimalTime = str(0.1234)
ax.set_title("Total time: " + totalTime + " | Optimal time: " + optimalTime)
#ax.text(0, 0, 0, "Central point", size=4, zorder=1, color='k')

plt.show(block = True) # To keep the plot until you close it.

fp.close()

