import numpy as np
import matplotlib.pyplot as plt
from matplotlib import cm
from mpl_toolkits.mplot3d import Axes3D



#Set plot in interactive mode
plt.ion()

# Open solutions file and reading limits and mode
fp = open('solutions.txt', 'r')
minXlim = int(fp.readline())
maxXlim = int(fp.readline())
minYlim = int(fp.readline())
maxYlim = int(fp.readline())
mode = fp.readline()
minZlim = 12
maxZlim = 12
### Manual axes
minXlim = -14
maxXlim = 14
minYlim = -14
maxYlim = 14
minZlim = -8
maxZlim = 8


#Setting figure and axis
fig = plt.figure(figsize=(20,16))
ax = fig.gca(projection='3d')

ax.set_xlim([minXlim,maxXlim])
ax.set_ylim([minYlim,maxYlim])
ax.set_zlim([minZlim,maxZlim])

#Open the plot
plt.show()


### MODE X - Reading rest of the file until find END
#Plotting GREEDY solutions on the file

# Function
X = np.arange(minXlim, maxXlim, 0.25)
Y = np.arange(minYlim, maxYlim, 0.25)
X, Y = np.meshgrid(X, Y)
Z = np.sin(np.sqrt(X**2 + Y**2))

# Plot the surface.
surf = ax.plot_surface(X, Y, Z, cmap=cm.coolwarm, linewidth=0, antialiased=True, alpha=.3)

##

##
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
    coords = [float(n) for n in s[1].split()]
    print(ID, coords)
    scatPrev = ax.scatter(prev_coords[0], prev_coords[1], prev_coords[2], s=40, c=color, alpha=transp) #Update the plot becouse we are in interactive mode
    scatNow = ax.scatter(coords[0], coords[1], coords[2], s=70, c=color) #Update the plot becouse we are in interactive mode
    prev_coords = coords
    prevID = ID
    plt.pause(0.5)
    line = fp.readline()
print("End of the file")
plt.show(block = True) # To keep the plot until you close it.
fp.close()




########## OLD CODE ############

# ## MODE 3 - Plotting file solutions

# # Function
# X = np.arange(minXlim, maxXlim, 0.25)
# Y = np.arange(minYlim, maxYlim, 0.25)
# X, Y = np.meshgrid(X, Y)
# Z = np.sin(np.sqrt(X**2 + Y**2))

# # Plot the surface.
# surf = ax.plot_surface(X, Y, Z, cmap=cm.coolwarm, linewidth=0, antialiased=True, alpha=.3)

# filepath = 'solutions.txt'
# print("Solutions in the file:")
# print("-----------------------")
# scat1=ax.scatter(0, 0, 0) # Para que el objeto exista y eliminarlo al empezar el bucle
# scat2=ax.scatter(0, 0, 0) # Para que el objeto exista y eliminarlo al empezar el bucle
# with open(filepath) as fp:
#     line = fp.readline()
#     prev_coords = [float(n) for n in line.split()]
#     while line:
#         scat1.remove() # Clear the points on the screen
#         scat2.remove() # Clear the points on the screen
#         coords = [float(n) for n in line.split()]
#         print(coords)
#         scat1 = ax.scatter(prev_coords[0], prev_coords[1], prev_coords[2], s=40, c="g", alpha=.5) #Update the plot becouse we are in interactive mode
#         scat2 = ax.scatter(coords[0], coords[1], coords[2], s=70, c="g") #Update the plot becouse we are in interactive mode
#         plt.pause(0.6)
#         prev_coords = coords
#         line = fp.readline()
# print("End of the file")
# plt.show(block = True) # To keep the plot until you close it.




 ## MODE 1 - Reading the file and showing points
# filepath = 'solutions.txt'
# print("Coords in the file:")
# print("-----------------------")
# with open(filepath) as fp:
#     line = fp.readline()
#     while line:
#         coords = [int(n) for n in line.split()]
#         print(coords)
#         scat = ax.scatter(coords[0], coords[1], coords[2], s=60, c="g") #Update the plot becouse we are in interactive mode
#         plt.pause(0.4)
#         scat.remove() # Clear the points on the screen
#         line = fp.readline()
# print("End of the file")
# plt.show(block = True) # To keep the plot until you close it.





# ## MODE 2 - Plotting random solutions

# # Function
# X = np.arange(-12, 12, 0.25)
# Y = np.arange(-12, 12, 0.25)
# X, Y = np.meshgrid(X, Y)
# Z = np.sin(np.sqrt(X**2 + Y**2))

# # Plot the surface.
# surf = ax.plot_surface(X, Y, Z, cmap=cm.coolwarm, linewidth=0, antialiased=True, alpha=.3)

# # Random solution
# x = np.random.uniform(low = -10, high = 10.0, size=5)
# y = np.random.uniform(low = -10, high = 10.0, size=5)
# z = np.sin(np.sqrt(x**2 + y**2))
# print(x)
# print(y)
# print(z)

# # Plotting
# ax.scatter(x, y, z, s=80, c="g")
# plt.show(block = True)


# ## MODE 3 - Reading the file and showing points
# filepath = 'solutions.txt'
# print("Solutions in the file:")
# print("-----------------------")
# prev_coords = [0, 0, 0] 
# with open(filepath) as fp:
#     line = fp.readline()
#     while line:
#         coords = [float(n) for n in line.split()]
#         print(coords)
#         scat1 = ax.scatter(prev_coords[0], prev_coords[1], prev_coords[2], s=40, c="g", alpha=.5) #Update the plot becouse we are in interactive mode
#         scat2 = ax.scatter(coords[0], coords[1], coords[2], s=70, c="g") #Update the plot becouse we are in interactive mode
#         plt.pause(0.6)
#         
#         scat1.remove() # Clear the points on the screen
#         scat2.remove() # Clear the points on the screen
#         prev_coords = coords
#         line = fp.readline()
# print("End of the file")
# plt.show(block = True) # To keep the plot until you close it.






