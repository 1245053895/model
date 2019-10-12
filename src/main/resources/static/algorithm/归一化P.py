from sklearn import preprocessing
from sklearn.preprocessing import MinMaxScaler
import numpy as np
import pandas as pd
import copy
data=pd.read_excel("C:\Users\Administrator\Desktop\dungou.xlsx")
columns=list(data.columns)
process_feature=[]
process_list = copy.copy(columns) 
for i in columns:   
    if 'S' in i or 'T' in i:
        process_list.remove(i)
data = data.loc[:,process_list]
data.isnull()
def outlierdeal(data):
    col=data.columns
    describ=data.describe()
    for item in col:
        series=data[item]
        dmean=describ.loc['mean',item]
        dstd=describ.loc['std',item]
        for i in range(len(series)):
            if series[i]>dmean+3*dstd:
                series[i]=series.quantile(0.5)
                return data
mms = MinMaxScaler((0,1))
mms.fit(data)
mydata=mms.transform(data)
data1=pd.DataFrame(mydata)

