function NhyhEcharts(algorithmname,data) {
    if (algorithmname.indexOf("NhyhLoadPredict") != -1) {
        NhyhLoadPredictEcharts(algorithmname,data);
    }
}

function NhyhLoadPredictEcharts(algorithmname,data) {
    document.getElementById("createtable").setAttribute("style","");
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
        xAxis: {
            type: 'category',
            data: Keys
        },
        yAxis: {
            type: 'value',
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
        }]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}