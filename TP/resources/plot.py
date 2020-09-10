import matplotlib.pyplot as plt
from matplotlib.ticker import (AutoMinorLocator, MultipleLocator)

x = []
y = []
#particle_id = 15
#radius = 1.0
#M = 13
L = 6

with open('RandomDynamicInput.txt') as f:
    next(f)
    for line in f:
        data = line[0:-1].split('   ')
        x.append(float(data[0]))
        y.append(float(data[1]))
f.close()

fig, ax = plt.subplots()
plt.plot(x[0], y[0], '.', color='red', markersize=7, label='remaining')
plt.plot(x[1:], y[1:], '.', color='blue', markersize=2, label='remaining')

#Círculos
for i in range(0, len(x)):
    radius = 0.2
    if i == 0:
        radius = 0.7
    cir = plt.Circle((x[i], y[i]), radius, color='green', fill=False)
    plt.gcf().gca().add_artist(cir)


#circleCut = 5 - y[particle_id]
#cir = plt.Circle((x[particle_id], -circleCut), radius, color='green', fill=False)
#plt.gcf().gca().add_artist(cir)
plt.xlabel('x')
plt.ylabel('y')
plt.title('testing')
plt.legend()

#cambiando el tamaño de la grilla la grilla
ax.set_xlim(0, L)
ax.set_ylim(0, L)
ax.grid(linestyle='-', linewidth='0.5')
#ax.xaxis.set_major_locator(MultipleLocator(particle_id))
#ax.yaxis.set_major_locator(MultipleLocator(particle_id))
plt.show()
#plt.savefig('vecinos_de_0.png')
