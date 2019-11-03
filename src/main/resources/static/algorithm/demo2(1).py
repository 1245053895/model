# -*- coding: utf-8 -*-
import time
import pandas as pd
from pyspark.sql import SparkSession

import matplotlib.pyplot as plt
from matplotlib.pyplot import savefig
from sklearn.externals import joblib
from pylab import *
mpl.rcParams['font.sans-serif'] = ['SimHei']   #使汉字正常显示在图片上

def Feature(data):
    #转换数据
    spark=SparkSession.builder.appName("DataFrame").getOrCreate()
    df = pd.read_csv(data,encoding='gbk')
    df_values = df.values.tolist()
    df_columns = list(df.columns)
    spark_df = spark.createDataFrame(df_values,df_columns)
    columns=spark_df.columns
    #分析
    from sklearn.ensemble import RandomForestRegressor
    y1 = spark_df['导向水平前点']  # 获取标签列
    rf1 = RandomForestRegressor()
    rf1.fit(spark_df, y1)
    print("随机森林——平均不纯度减少(导向水平前点)影响因素分析：")
    print("Features sorted by their score:")
    print(sorted(zip(map(lambda x: round(x, 4), rf1.feature_importances_), columns), reverse=True))
    result = sorted(zip(map(lambda x: round(x, 4), rf1.feature_importances_), columns), reverse=True)
    ##可视化
    defens2 = map(lambda x: round(x, 4), rf1.feature_importances_)
    TZ2 = dict(zip(columns, defens2))
    TZ2 = pd.DataFrame([TZ2])
    TZtq = TZ2.T
    TZtq.plot(kind='bar', figsize=(15, 10), color='y')
    plt.title('导向水平前点影响因素分析结果可视化', color='black')
    plt.show()
    return result

if __name__ == '__main__':
    start = time.clock()
    a = Feature('E:\\juejin_data.csv')
    end = time.clock()
    print("程序执行时间:", end - start)