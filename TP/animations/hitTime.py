import matplotlib.pyplot as plt
import seaborn as sb
import numpy as np

hitTime = np.loadtxt("../resources/hitTime_0.txt")
print(hitTime)
sb.distplot(hitTime, hist=True, kde=True,
            bins=50,
            color='darkblue',
            hist_kws={'edgecolor': 'black'},
            kde_kws={'linewidth': 2}).set(xlim=(0))
prom=(hitTime.mean())
print(prom)
plt.text(0,-0.5,"Promedio de colisiones "+str(round(prom,2)))
plt.xlabel('Tiempo entre colisión (s)')
plt.ylabel('Densidad')
plt.show()
plt.close()