import matplotlib.pyplot as plt
import seaborn as sb
import numpy as np


cant_files=4
x=[]
eje_x = np.arange(0.0, 30, 1)

for i in range(0, cant_files):
    with open("../resources/hitFreq_" + str(i) + ".txt") as data:
        x.append(np.loadtxt(data, unpack=True))

# print(eje_x)

y=np.matrix(x)
print(y)
mean=y.mean(axis=0).tolist()
print(mean[0])
err=y.std(axis=0).tolist()
print(err[0])

plt.errorbar(eje_x, mean[0], err[0], ls='none',marker='.',color='c')

# fig, ax = plt.subplots()
# ax.bar(eje_x, mean[0], yerr=err[0], align='center', alpha=0.5, ecolor='black', capsize=10)

plt.show()
