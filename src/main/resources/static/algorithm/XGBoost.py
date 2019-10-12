# -*- coding: utf-8 -*-
"""
Created on Thu Sep  5 09:35:25 2019

@author: Administrator
"""
import pandas as pd
import xgboost as xgb
from sklearn.model_selection import train_test_split
from sklearn.externals import joblib
import json
def read_json(json_file):
#    f = 
    with open(json_file,'r',encoding='utf-8-sig') as f:
        file_data=json.loads(f.read())
    return file_data
filename = 'XGBregressor(1).json'
data=read_json(filename)
data_dict=data["params"]
max_depth=data_dict["max_depth"]
learning_rate=data_dict["learning_rate"]
n_estimator=data_dict["n_estimator"]
outpath=data_dict["output"]
dataPath=data_dict["input"]
def model(max_depth, learning_rate, n_estimator ):
    model = xgb.XGBRegressor(max_depth, learning_rate, n_estimator)
    return model
def Train_0huan(dataPath):
    try:
        df = pd.read_excel(dataPath)
        print("------------------")
        print("数据读取成功")
        print("------------------")
        try:
            item =['刀盘扭矩 (%)', '刀盘扭矩 (KN.M)', '刀盘转速 (rpm)', '内圈温度 (℃)', '外圈温度 (℃)',
               '上土压 (Mpa)', '右土压 (Mpa)', '下土压 (Mpa)', '左土压 (Mpa)', '回转角（前筒） (deg)',
               '俯仰角（前筒） (deg)', '总推力 (KN)', '总油压 (Mpa)', '推进速度 (mm/Min)',
               '千斤顶行程（上） (mm)', '千斤顶行程（右） (mm)', '千斤顶行程（下） (mm)', '千斤顶行程（左） (mm)',
               '千斤顶速度（上） (mm/Min)', '千斤顶速度（右） (mm/Min)', '千斤顶速度（左） (mm/Min)',
               '千斤顶推力（上） (KN)', '千斤顶推力（右） (KN)', '千斤顶推力（下） (KN)', '千斤顶推力（左） (KN)',
               '螺旋机扭矩 (KN.M)', '螺旋皮带机转速 (rpm)', '螺旋皮带机压力 (Mpa)', '前闸门开度 (%)',
               '铰接油压 (Mpa)', '注浆累计（环） (m3)', '铰接水平偏差 (mm)', '铰接垂直偏差 (mm)',
               '切口水平偏差 (mm)', '切口垂直偏差 (mm)', '盾尾水平偏差 (mm)', '盾尾垂直偏差 (mm)',
               '盾尾间隙 上 (mm)', '盾尾间隙 下 (mm)', '盾尾间隙 左 (mm)', '盾尾间隙 右 (mm)','0米处']
            df1=df[item]
            train_x,test_x = train_test_split(df1,test_size = 0.2,random_state = 0)
            train_y = train_x['0米处']
            train_x = train_x.drop('0米处',1)
            try:     
                model1=model(max_depth, learning_rate, n_estimator)
                model1.fit(train_x, train_y)
                joblib.dump(model1,'%s/model_0huan1.pkl')
                print('模型训练成功')
            except:
                print('第三方模块包加载异常，模型训练失败')
        except:
            print('数据格式错误,请检查数据中是否含有%s'%(item))
    except:
        print('数据读取失败,请检查文件名是否正确')
def predict_0huan(dataPath,path_model):
    print("------------------")
    print("读取距轴线0米距开挖面0环处的地面沉降预测模型")
    print("------------------")
    try:
        model= joblib.load(path_model)
        print("模型读取成功")
        print("------------------")
        print("读取数据")
        print("------------------")
        try:        
            df = pd.read_excel(dataPath)
            print("数据读取成功")
            print("------------------")
            item =['刀盘扭矩 (%)', '刀盘扭矩 (KN.M)', '刀盘转速 (rpm)', '内圈温度 (℃)', '外圈温度 (℃)',
           '上土压 (Mpa)', '右土压 (Mpa)', '下土压 (Mpa)', '左土压 (Mpa)', '回转角（前筒） (deg)',
           '俯仰角（前筒） (deg)', '总推力 (KN)', '总油压 (Mpa)', '推进速度 (mm/Min)',
           '千斤顶行程（上） (mm)', '千斤顶行程（右） (mm)', '千斤顶行程（下） (mm)', '千斤顶行程（左） (mm)',
           '千斤顶速度（上） (mm/Min)', '千斤顶速度（右） (mm/Min)', '千斤顶速度（左） (mm/Min)',
           '千斤顶推力（上） (KN)', '千斤顶推力（右） (KN)', '千斤顶推力（下） (KN)', '千斤顶推力（左） (KN)',
           '螺旋机扭矩 (KN.M)', '螺旋皮带机转速 (rpm)', '螺旋皮带机压力 (Mpa)', '前闸门开度 (%)',
           '铰接油压 (Mpa)', '注浆累计（环） (m3)', '铰接水平偏差 (mm)', '铰接垂直偏差 (mm)',
           '切口水平偏差 (mm)', '切口垂直偏差 (mm)', '盾尾水平偏差 (mm)', '盾尾垂直偏差 (mm)',
           '盾尾间隙 上 (mm)', '盾尾间隙 下 (mm)', '盾尾间隙 左 (mm)', '盾尾间隙 右 (mm)']
            try:
                df1=df[item]
                X =df1
                print("开始预测")
                try:
                    y_pred_0huan = model.predict(X)
                    print("------------------")
                    print('距轴线0米距开挖面0环处的地面沉降量预测值为：')
                    print(y_pred_0huan)
                    y_pred_0huan1 =pd.DataFrame(y_pred_0huan)
                    y_pred_0huan1.to_csv('outpath')
                    
                    return  y_pred_0huan
                except:
                    print('预测失败,请检查模型是否为距轴线0米距开挖面0环处的地面沉降预测模型')
            except:
                print('数据格式错误,请检查数据中是否含有%s'%(item))
            
        except:
                print('数据读取失败,请检查文件名是否正确')
        
    except:
        print('模型读取失败，请检查文件名是否正确')


