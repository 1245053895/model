function GhxxEcharts(algorithmname,data) {
    if (algorithmname.indexOf("GhxxInterStationScore") != -1) {
        GhxxInterStationScoreEcharts(algorithmname,data);
    }
}

function GhxxInterStationScoreEcharts(algorithmname,data) {
    var Values = data.split(",");
    var values = Values[1].split("]");
    var value = values[0];
    // alert(Keys);
    // alert(Values);
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        xAxis: {
            type: 'category',
            data: ["选线得分"],
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
            data: [value/20],
            type: 'bar',
            barWidth : 100
        }]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}