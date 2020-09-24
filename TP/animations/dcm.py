import matplotlib.pyplot as plt
import numpy as np
import seaborn as sns; sns.set_theme(color_codes=True)


cant_files=10
empty_array = np.empty((216, 0), int)
eje_x = np.arange(0.0,216, 1)

for i in range(0, cant_files):
    with open("../resources/dcm_" + str(i) + ".txt") as data:
        # x[i].append(np.loadtxt(data, unpack=True))
        content = data.readlines()
        content = [x.strip() for x in content]
        # print(np.array([content]).transpose())
        empty_array = np.append(empty_array, np.array([content]).transpose(), axis=1)

# print(empty_array)
arr=np.array(empty_array).astype(np.float)
mean=np.nanmean(arr, axis = 1)
err=np.nanstd(arr,axis=1)
plt.ylabel('Z^2')
plt.xlabel('Tiempo [s]')
# plt.errorbar(eje_x, mean, err,  ls='none', marker='.',color='c',elinewidth=0.01,markeredgewidth=0.1)
# plt.errorbar(eje_x, mean, yerr=err, ls='none',fmt='-o', label='<z²>')
sns.regplot(x=eje_x,y=mean,data=mean,scatter_kws={"s": 3},x_jitter=.05)
# # plt.savefig('DCM_N130.png')
#
plt.show()
