import matplotlib.pyplot as plt
import numpy as np
import seaborn as sns; sns.set_theme(color_codes=True)


cant_files=1
x=[]
eje_x = np.arange(0.0, 147, 1)

for i in range(0, cant_files):
    with open("../resources/dcm_" + str(i) + ".txt") as data:
        x.append(np.loadtxt(data, unpack=True))

# print(eje_x)

y=np.matrix(x)
# print(y)
mean=y.mean(axis=0).tolist()
# print(mean[0])
err=y.std(axis=0).tolist()
# print(err[0])

# plt.errorbar(eje_x, mean[0], err[0], linestyle='-', marker='.',color='c')
real_mean=mean[0]
sns.regplot(eje_x,real_mean)
plt.show()
