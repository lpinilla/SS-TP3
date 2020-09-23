import matplotlib.pyplot as plt
import seaborn as sb
import numpy as np

velocities_init = np.loadtxt("../resources/velocityT_0.txt")
print(velocities_init)
sb.distplot(velocities_init, hist=True, kde=True,
             bins=50,
             color='darkblue',
             hist_kws={'edgecolor': 'black'},
             kde_kws={'linewidth': 2}).set(xlim=(0))
plt.xlabel('Modulo de la velocidad (m/s)')
plt.ylabel('Densidad')
prom=velocities_init.mean()
plt.text(0,-2,"Promedio de velocidades "+str(round(prom,2)))
plt.show()
plt.close()