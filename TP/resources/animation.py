import matplotlib.animation as ani
import matplotlib.pyplot as plt
from matplotlib.ticker import (AutoMinorLocator, MultipleLocator)

#follow = 15
L = 6

times = [
    {
    't' : 0,
    'x' : [],
    'y' : [],
    'vx' : [],
    'vy' : []
    }
]
aux = {'t': 0, 'x' : [], 'y' : [], 'vx' : [], 'vy': []}

#Parsear el archivo
with open('./dynamic.txt') as f:
    next(f)
    for line in f:
        #-1 para ignorar el \n
        data = line[:-1].split('    ') #4 espacios
        if(len(data) == 1):
            if(len(aux['x']) != 0):
                times.append(aux)
                aux = {'t': 0, 'x' : [], 'y' : [], 'vx' : [], 'vy' : []}
            aux['t'] = data[0]
        else:
            aux['x'].append(float(data[0]))
            aux['y'].append(float(data[1]))
            aux['vx'].append(float(data[2]))
            aux['vy'].append(float(data[3]))
            #times.append(aux)
f.close()

#Saltear el primero que es constructor
times = times[1:]

print(str(len(times)) + " muestras tomadas")

#fig, ax = plt.subplots()
Writer = ani.writers['ffmpeg']
writer = Writer(fps=300, metadata=dict(artist='me'), bitrate=1080)
#frame = '0'
#ax.xaxis.set_major_locator(MultipleLocator(10))
#ax.yaxis.set_major_locator(MultipleLocator(10))

def draw_circles(i):
    radius = 0.7
    cir = plt.Circle((times[i]['x'][0], times[i]['y'][0]), radius, color='green', fill=False)
    plt.gcf().gca().add_artist(cir)
    for i in range(1, len(times[i]['x'])):
        radius = 0.2
        cir = plt.Circle((times[i]['x'][i], times[i]['y'][i]), radius, color='green', fill=False)
        plt.gcf().gca().add_artist(cir)

def init():
    ax.set_ylim(0, L)
    ax.set_xlim(0, L)
    ax.grid(linestyle='-', linewidth='0.5')
    del xdata[:]
    del ydata[:]
    line.set_data(xdata, ydata)
    return line,

fig, ax = plt.subplots()
line, = ax.plot([], [], '.', markersize=45)
lineBig, = ax.plot([], [], '.', color="red", markersize=165)
part, = ax.plot([], [], '.', color="red", markersize=6)
ax.grid()
xdata, ydata = [], []

def animate(i):
    bigX = times[i]['x'][0]
    bigY = times[i]['y'][0]
    x = times[i]['x'][1:]
    y = times[i]['y'][1:]
    #part.set_data(times[i]['x'][follow], times[i]['y'][follow])
    line.set_data(x, y)
    lineBig.set_data(bigX, bigY)
    #draw_circles(i)
    ax.set_title(str(i))
    return line,
    #frame = str(i)


animation = ani.FuncAnimation(fig, animate, frames=len(times)-1, interval=1, repeat=True, init_func=init)
#animation = ani.FuncAnimation(fig, animate, frames=300, interval=1, repeat=True, init_func=init)
plt.show()
#animation.save('poc.gif', writer='imagemagick', fps=60)
