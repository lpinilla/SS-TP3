import matplotlib.pyplot as plt
import seaborn as sb
import numpy as np

velocities_init = np.loadtxt("../resources/velocity_2.txt")

# norm = np.linalg.norm(velocities_init)
# normal_array = velocities_init/norm
sb.distplot(velocities_init,hist=True, kde=False,
             bins=50,
             color='darkblue',
             hist_kws={'edgecolor': 'black','weights': np.ones(len(velocities_init))/len(velocities_init)},
             kde_kws={'linewidth': 2}).set(xlim=(0))
plt.xlabel('Modulo de la velocidad (m/s)')
plt.ylabel('Probabilidad')
prom=velocities_init.mean()
plt.text(0,-2,"Promedio de velocidades "+str(round(prom,2))+"m/s")
# plt.savefig('VeloDistributionN60.png')

plt.show()
plt.close()

