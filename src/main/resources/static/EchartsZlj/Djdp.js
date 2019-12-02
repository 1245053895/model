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
    option = {
        title: {
            text:'刀具刀盘健康评估',
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