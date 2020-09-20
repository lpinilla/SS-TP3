import numpy as np
from matplotlib import pyplot as plt

filename1 = "../resources/bigParticlePath.txt"
filename="../resources/RandomStaticInput.txt"
with open(filename) as f:
   static=f.read().splitlines()
board_lenght=int(static[1])

X, Y = [], []
for line in open(filename1, 'r'):
    values = [float(s) for s in line.split()]
    X.append(values[0])
    Y.append(values[1])

plt.axis([0,board_lenght, 0, board_lenght])
plt.axis('equal') 
plt.plot(X, Y)

plt.show()