function TzgsEcharts(algorithmname,data) {
    if (algorithmname.indexOf("rf") != -1) {
        TzgsRfEcharts(algorithmname,data);
    }
    else {
        TzgsCzEcharts(algorithmname,data);
    }
}
function TzgsRfEcharts(algorithmname,data){
    document.getElementById("createtable").setAttribute("style","");
    document.getElementById("createtable").innerHTML = "";
    dataJsons = JSON.parse(data);
    dataJson = dataJsons["prediction"];
    var Keys = [];
    var Values = [];
    var i = 0;
    for (var key in dataJson) {
        Keys[i] = key;
        Values[i] = dataJson[key]/10000;
        i++;
    }
    // alert(Keys);
    // alert(Values);
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    myChart.clear();
    var app = {};
    option = null;
    option = {
        title: {
            text: '投资估算人防预测',
            // subtext:'环号：'+Ring+'    时间：'+time+'    里程：'+mileage,
            x: 'center',
            y: 'top',
        },
        xAxis: {
            type: 'category',
            data: Keys
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value}(万元)'
            }
        },
        series: [{
            itemStyle: {
                normal: {
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
                    excludeComponents: ['toolbox'],
                    pixelRatio: 2
                },
                magicType: {//动态类型切换
                    type: ['bar', 'line']
                },
                myBing: {//自定义按钮 danielinbiti,这里增加，selfbuttons可以随便取名字
                        show: true,//是否显示
                        title: '切换为饼图', //鼠标移动上去显示的文字
                        icon: 'image:///EchartsZlj/饼图.png', //图标
                        onclick: function () {//点击事件,这里的option1是chart的option信息
                            BingRfEcharts(algorithmname,data); //这里可以加入自己的处理代码，切换不同的图形
                        }
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

function TzgsCzEcharts(algorithmname,data){
    document.getElementById("createtable").setAttribute("style","");
    document.getElementById("createtable").innerHTML = "";
    dataJsons = JSON.parse(data);
    dataJson = dataJsons["prediction"];
    var Keys = [];
    var Values = [];
    var i = 0;
    for (var key in dataJson) {
        Keys[i] = key;
        Values[i] = dataJson[key]/1000;
        i++;
    }
    // alert(Keys);
    // alert(Values);
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    myChart.clear();
    var app = {};
    option = null;
    option = {
        title: {
            text:'投资估算车站预测',
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
                formatter:'{value}(千万元)'
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
                },
                myBing: {//自定义按钮 danielinbiti,这里增加，selfbuttons可以随便取名字
                    show: true,//是否显示
                    title: '切换为饼图', //鼠标移动上去显示的文字
                    icon: 'image:///EchartsZlj/饼图.png', //图标
                    onclick: function () {//点击事件,这里的option1是chart的option信息
                        BingCzEcharts(algorithmname,data); //这里可以加入自己的处理代码，切换不同的图形
                    }
                }
            }
        }
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}

function BingRfEcharts(algorithmname,data) {
    dataJsons = JSON.parse(data);
    dataJson = dataJsons["prediction"];
    var Keys = [];
    var Values = [];
    var i = 0;
    for (var key in dataJson) {
        Keys[i] = key;
        Values[i] = dataJson[key]/10000;
        i++;
    }
    var ValuesArray = [];
    for (var j = 0 ; j < Keys.length ;j ++) {
        var JsonValues = {};
        JsonValues['value'] = Values[j];
        JsonValues['name'] = Keys[j];
        ValuesArray.push(JsonValues);
    }
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    myChart.clear();
    var app = {};
    option = null;
    option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} 万元 ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:Keys
        },
        series: [
            {
                name:'访问来源',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:ValuesArray
            }
        ],
        toolbox: {
                show: true,
                itemSize: 20,
                itemGap: 30,
                right: 50,
                feature: {
                    myBack: {//自定义按钮 danielinbiti,这里增加，selfbuttons可以随便取名字
                        show: true,//是否显示
                        title: '还原', //鼠标移动上去显示的文字
                        icon: 'image:///EchartsZlj/刷新.png', //图标
                        onclick: function () {//点击事件,这里的option1是chart的option信息
                            TzgsRfEcharts(algorithmname,data);//这里可以加入自己的处理代码，切换不同的图形
                        }
                    },
                    saveAsImage: {
                        excludeComponents: ['toolbox'],
                        pixelRatio: 2
                    }

                }
        }
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}

function BingCzEcharts(algorithmname,data) {
    dataJsons = JSON.parse(data);
    dataJson = dataJsons["prediction"];
    var Keys = [];
    var Values = [];
    var i = 0;
    for (var key in dataJson) {
        Keys[i] = key;
        Values[i] = dataJson[key]/1000;
        i++;
    }
    var ValuesArray = [];
    for (var j = 0 ; j < Keys.length ;j ++) {
        var JsonValues = {};
        JsonValues['value'] = Values[j];
        JsonValues['name'] = Keys[j];
        ValuesArray.push(JsonValues);
    }
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    myChart.clear();
    var app = {};
    option = null;
    option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} 千万元 ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:Keys
        },
        series: [
            {
                name:'访问来源',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:ValuesArray
            }
        ],
        toolbox: {
            show: true,
            itemSize: 20,
            itemGap: 30,
            right: 50,
            feature: {
                myBack: {//自定义按钮 danielinbiti,这里增加，selfbuttons可以随便取名字
                    show: true,//是否显示
                    title: '还原', //鼠标移动上去显示的文字
                    icon: 'image:///EchartsZlj/刷新.png', //图标
                    onclick: function () {//点击事件,这里的option1是chart的option信息
                        TzgsCzEcharts(algorithmname,data);//这里可以加入自己的处理代码，切换不同的图形
                    }
                },
                saveAsImage: {
                    excludeComponents: ['toolbox'],
                    pixelRatio: 2
                }

            }
        }
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}
