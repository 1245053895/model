function DjdpEcharts(algorithmname,data) {
    if (algorithmname.indexOf("SOM") != -1) {
        DjdpSomEcharts(algorithmname,data);
    }
    if (algorithmname.indexOf("Lstm") != -1) {
        DjdpLstmEcharts(algorithmname,data);
    }
}
function DjdpSomEcharts(algorithmname,data) {
    document.getElementById("createtable").setAttribute("style","");
    document.getElementById("createtable").innerHTML = "";
    var dataArray = eval("("+data+")");
    var Values = [];
    var Keys = [];
    for (var i = 0 ; i < dataArray.length ; i++){
        var datas = dataArray[i];
            Values[i] = datas[0];
            Keys[i] = datas[1];
    }
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    myChart.clear();
    // option = {
    //     title: {
    //         text:'刀具刀盘健康评估',
    //         // subtext:'环号：'+Ring+'    时间：'+time+'    里程：'+mileage,
    //         x:'center',
    //         y:'top',
    //     },
    //     xAxis: {
    //         type: 'category',
    //         data: Keys
    //     },
    //     yAxis: {
    //         type: 'value'
    //     },
    //     series: [{
    //         data: Values,
    //         type: 'line',
    //         smooth: true
    //     }],
    //     toolbox: {
    //         show: true,
    //         itemSize: 20,
    //         itemGap: 30,
    //         right: 50,
    //         feature: {
    //             restore: { //重置
    //                 show: true
    //             },
    //             saveAsImage: {
    //                 excludeComponents :['toolbox'],
    //                 pixelRatio: 2
    //             },
    //             magicType: {//动态类型切换
    //                 type: ['bar', 'line']
    //             }
    //         }
    //     }
    // };
    option = {
        title: {
            x:'left',
            y:'top',
            text: '刀具健康指标',
            subtext:'当VB值位于0-0.4时，刀具处于健康状态，VB值位于0.4-0.6时，刀具处于轻度磨损状态，VB值位于0.6-0.8时，刀具处于中度磨损状态，VB值大于0.8时，刀具处于严重磨损状态，建议开仓检查',
        },
        tooltip: {
            trigger: 'axis'
        },
        xAxis: {
            data: Keys
        },
        yAxis: {
            splitLine: {
                show: false
            }
        },
        toolbox: {
            left: 'center',
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {},
                saveAsImage: {}
            }
        },
        dataZoom: [{
            startValue: Keys[0]
        }, {
            type: 'inside'
        }],
        visualMap: {
            top: 10,
            right: 10,
            precision:1,//设置小数精度，默认0没有小数
            pieces: [{
                gt: 0,
                lte: 0.4,
                color: '#096'
            }, {
                gt: 0.4,
                lte: 0.6,
                color: '#ffde33'
            }, {
                gt: 0.6,
                lte: 0.8,
                color: '#ff9933'
            }, {
                gt: 0.8,
                color: '#7e0023'
            }],
            outOfRange: {
                color: '#999'
            }
        },
        series: {
            name: '',
            type: 'line',
            data: Values,
            markLine: {
                silent: true,
                data: [{
                    yAxis: 0.4
                }, {
                    yAxis: 0.6
                }, {
                    yAxis: 0.8
                }]
            }
        }
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}
function DjdpLstmEcharts(algorithmname,data) {
    document.getElementById("createtable").setAttribute("style","");
    document.getElementById("createtable").innerHTML = "";
    var dataArray = eval("("+data+")");
    var datas = dataArray["data"];
    var Values = datas;
    var Keys = [];
    for (var i = 0 ; i < datas.length ; i++){
        Keys[i] = 230;
    }
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    myChart.clear();
    option = {
        title: {
            text:'刀具刀盘磨损预测',
            // subtext:'环号：'+Ring+'    时间：'+time+'    里程：'+mileage,
            x:'center',
            y:'top',
        },
        xAxis: {
            type: 'category',
            data: Keys
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: Values,
            type: 'line',
            smooth: true
        }],
        toolbox: {
            show: true,
            itemSize: 20,
            itemGap: 30,
            right: 50,
            feature: {
                restore: { //重置
                    show: true
                },
                saveAsImage: {
                    excludeComponents :['toolbox'],
                    pixelRatio: 2
                },
                magicType: {//动态类型切换
                    type: ['bar', 'line']
                }
            }
        }
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}
