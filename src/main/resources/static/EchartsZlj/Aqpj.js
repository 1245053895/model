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
        }]
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
            data: Values
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
            data: Keys,
            type: 'bar'
        }]
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
    dataJson = JSON.parse(data);
    var Keys = [];
    var Values = [];
    var Keys1 = [];
    var Values1 = [];
    var i = 0;
    for (var key in dataJson) {
        Keys1[i] = key;
        Values1[i] = dataJson[key];
        i++;
    }
    for (var j = 0, k = 1 ;k < i; k++,j++) {
        Keys[j] = Keys1[k];
        Values[j] = Values1[k];
    }
    // alert(Keys);
    // alert(Values);
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        title:{
            text:Keys1[0]+":"+Values1[0],
            left:'center'
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
        }]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
    window.addEventListener("resize", function () {
        myChart.resize();
        //myCharts是你的初始化echarts图表时取的名字
    });
}