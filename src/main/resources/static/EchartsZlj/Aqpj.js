function AqpjEcharts(algorithmname,data) {
    if (algorithmname.indexOf("shigu") != -1) {
        AqpjShiguEcharts(algorithmname,data);
    }
    if (algorithmname.indexOf("pingjia") != -1) {
        if (algorithmname.indexOf("hengxiang") != -1){
            AqpjXiangEcharts(algorithmname,data)
        }
        else if (algorithmname.indexOf("shuxiang") != -1) {
            AqpjXiangEcharts(algorithmname,data)
        }
        else {
            AqpjPingjiaEcharts(algorithmname,data);
        }
    }
}
function AqpjShiguEcharts(algorithmname,data){
    document.getElementById("createtable").setAttribute("style","");
    document.getElementById("createtable").innerHTML = "";
    dataJson = JSON.parse(data);
    var Keys = [];
    var Values = [];
    var i = 0;
    for (var key in dataJson) {
        Keys[i] = key;
        Values[i] = dataJson[key];
        i++;
    }
    // alert(Keys);
    // alert(Values);
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        title: {
            text:'安全评价事故分类预测',
            // subtext:'环号：'+Ring+'    时间：'+time+'    里程：'+mileage,
            x:'center',
            y:'top',
        },
        xAxis: {
            type: 'category',
            data: Keys
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter:'{value}'
            }
        },
        series: [{
            itemStyle : {
                normal : {
                    label: {
                        show: true,
                        position: 'top',
                        textStyle: {
                            color: 'black'
                        }
                    }
                },
            },
            data: Values,
            type: 'bar'
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
    window.addEventListener("resize", function () {
        myChart.resize();
        //myCharts是你的初始化echarts图表时取的名字
    });
}
function AqpjPingjiaEcharts(algorithmname,data){
    document.getElementById("createtable").setAttribute("style","");
    document.getElementById("createtable").innerHTML = "";
    dataJson = JSON.parse(data);
    var Keys = [];
    var Values = [];
    var i = 0;
    for (var key in dataJson) {
        Keys[i] = key;
        Values[i] = dataJson[key];
        i++;
    }
    // alert(Keys);
    // alert(Values);
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        title: {
            text:'安全评价沉降等级预测\n\n'+Keys[1]+':'+Values[1],
            // subtext:Keys[1]+':'+Values[1],
            x:'center',
            y:'top',
        },
        xAxis: {
            type: 'category',
            data: [Keys[0]]
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter:'{value}'
            }
        },
        series: [{
            itemStyle : {
                normal : {
                    label: {
                        show: true,
                        position: 'bottom',
                        textStyle: {
                            color: 'black'
                        }
                    }
                },
            },
            data: [Values[0]],
            type: 'bar'
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
    window.addEventListener("resize", function () {
        myChart.resize();
        //myCharts是你的初始化echarts图表时取的名字
    });
}
function AqpjXiangEcharts(algorithmname,data){
    document.getElementById("createtable").setAttribute("style","");
    document.getElementById("createtable").innerHTML = "";
    dataJsons = JSON.parse(data);
    dataJson = dataJsons["prediction"];
    var status = dataJsons["staus"];
    var Keys = [];
    var Values = [];
    var i = 0;
    for (var key in dataJson) {
        Keys[i] = key;
        Values[i] = dataJson[key];
        i++;
    }
    // alert(Keys);
    // alert(Values);
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        title: {
            text:'安全评价变形等级预测'+'     状态：'+status,
            // subtext:'环号：'+Ring+'    时间：'+time+'    里程：'+mileage,
            x:'center',
            y:'top',
        },
        xAxis: {
            type: 'category',
            data: Keys
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter:'{value}'
            }
        },
        series: [{
            itemStyle : {
                normal : {
                    label: {
                        show: true,
                        position: 'top',
                        textStyle: {
                            color: 'black'
                        }
                    }
                },
            },
            data: Values,
            type: 'bar'
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
    window.addEventListener("resize", function () {
        myChart.resize();
        //myCharts是你的初始化echarts图表时取的名字
    });
}