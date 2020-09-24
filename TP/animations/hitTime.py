import matplotlib.pyplot as plt
import seaborn as sb
import numpy as np

hitTime = np.loadtxt("../resources/hitTime_3.txt")
print(hitTime)
sb.distplot(hitTime, hist=True, kde=False,
            bins=50,
            color='darkblue',
            hist_kws={'edgecolor': 'black','weights': np.ones(len(hitTime))/len(hitTime)},
            kde_kws={'linewidth': 2}).set(xlim=(0))
prom=(hitTime.mean())
print(prom)
plt.title("promedio de tiempo entre colisiones "+str(round(prom,5)))
plt.xlabel('Tiempo entre colisioones [s]')
plt.ylabel('Probabilidad')
plt.savefig('hitTimeN60.png')

plt.show()
plt.close()