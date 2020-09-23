import numpy as np
from matplotlib import pyplot as plt

filename1 = "../resources/bigParticlePath.txt"
filename2 = "../resources/bigParticlePathV0.03M2.0.txt"
filename3 = "../resources/bigParticlePath.txt"

filename="../resources/RandomStaticInput.txt"
with open(filename) as f:
   static=f.read().splitlines()
print(static)
board_lenght=int(static[1])

X1, Y1 = [], []
for line in open(filename1, 'r'):
    values = [float(s) for s in line.split()]
    X1.append(values[0])
    Y1.append(values[1])
plt.plot(X1, Y1,color='red')

X2, Y2 = [], []
for line in open(filename2, 'r'):
    values = [float(s) for s in line.split()]
    X2.append(values[0])
    Y2.append(values[1])
plt.plot(X2, Y2,color='green')

X3, Y3 = [], []
for line in open(filename3, 'r'):
    values = [float(s) for s in line.split()]
    X3.append(values[0])
    Y3.append(values[1])
plt.plot(X3, Y3,color='blue')

plt.axis([0,board_lenght, 0, board_lenght])
plt.xlabel('Posicion X [m]')
plt.ylabel('Posicion Y [m]')
plt.axis('equal')
plt.legend(['N=40   L=3', 'N=100 L=5','N=400 L=10'])

plt.show()