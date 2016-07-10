# -*- coding: utf-8 -*-
"""
Created on Sun Jul 10 09:43:27 2016

@author: mbesancon
"""

import numpy as np
import matplotlib.pyplot as plt
from scipy.stats import gaussian_kde

def convert_string(s):
    return (int(s.split(" ")[0]),float(s.split(" ")[1]))

with open("resultRank","r") as f0:
    res_raw = np.array(list(map(lambda s: convert_string(s), f0.read().split("\n"))))

ranks = res_raw[np.argsort(res_raw[:, 1])][::-1]

xr = np.arange(1,ranks.shape[0]+1)
yr = ranks[:,1]

plt.loglog(xr,yr,"+")
plt.grid()
plt.title("Email ranks")
plt.xlabel("Log-rank")
plt.savefig("rank_dist.pdf")
plt.show()

coeff_lin = np.polyfit(np.log10(xr),np.log10(yr),deg=1)
# array([-0.38916235, -3.05044658])
# logy = a*logx + b
# y = exp(b) * log(x)

