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
    myChart.clear();
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
                },
                myBing: {//自定义按钮 danielinbiti,这里增加，selfbuttons可以随便取名字
                    show: true,//是否显示
                    title: '切换为饼图', //鼠标移动上去显示的文字
                    icon: 'image:///EchartsZlj/饼图.png', //图标
                    onclick: function () {//点击事件,这里的option1是chart的option信息
                        BingShiguEcharts(algorithmname,data); //这里可以加入自己的处理代码，切换不同的图形
                    }
                },
                myRose: {//自定义按钮 danielinbiti,这里增加，selfbuttons可以随便取名字
                    show: true,//是否显示
                    title: '切换为玫瑰图', //鼠标移动上去显示的文字
                    icon: 'image:///EchartsZlj/玫瑰图.png', //图标
                    onclick: function () {//点击事件,这里的option1是chart的option信息
                        RoseShiguEcharts(algorithmname,data); //这里可以加入自己的处理代码，切换不同的图形
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
    myChart.clear();
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
                },
                myBing: {//自定义按钮 danielinbiti,这里增加，selfbuttons可以随便取名字
                    show: true,//是否显示
                    title: '切换为饼图', //鼠标移动上去显示的文字
                    icon: 'image:///EchartsZlj/饼图.png', //图标
                    onclick: function () {//点击事件,这里的option1是chart的option信息
                        BingPingjiaEcharts(algorithmname,data); //这里可以加入自己的处理代码，切换不同的图形
                    }
                },
                myRose: {//自定义按钮 danielinbiti,这里增加，selfbuttons可以随便取名字
                    show: true,//是否显示
                    title: '切换为玫瑰图', //鼠标移动上去显示的文字
                    icon: 'image:///EchartsZlj/玫瑰图.png', //图标
                    onclick: function () {//点击事件,这里的option1是chart的option信息
                        RosePingjiaEcharts(algorithmname,data); //这里可以加入自己的处理代码，切换不同的图形
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
    myChart.clear();
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
                },
                myBing: {//自定义按钮 danielinbiti,这里增加，selfbuttons可以随便取名字
                    show: true,//是否显示
                    title: '切换为饼图', //鼠标移动上去显示的文字
                    icon: 'image:///EchartsZlj/饼图.png', //图标
                    onclick: function () {//点击事件,这里的option1是chart的option信息
                        BingXiangEcharts(algorithmname,data); //这里可以加入自己的处理代码，切换不同的图形
                    }
                },
                myRose: {//自定义按钮 danielinbiti,这里增加，selfbuttons可以随便取名字
                    show: true,//是否显示
                    title: '切换为玫瑰图', //鼠标移动上去显示的文字
                    icon: 'image:///EchartsZlj/玫瑰图.png', //图标
                    onclick: function () {//点击事件,这里的option1是chart的option信息
                        RoseXiangEcharts(algorithmname,data); //这里可以加入自己的处理代码，切换不同的图形
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

function BingShiguEcharts(algorithmname,data) {
    dataJson = JSON.parse(data);
    var Keys = [];
    var Values = [];
    var i = 0;
    for (var key in dataJson) {
        Keys[i] = key;
        Values[i] = dataJson[key];
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
        title: {
            text:'安全评价事故分类预测',
            // subtext:'环号：'+Ring+'    时间：'+time+'    里程：'+mileage,
            x:'center',
            y:'top',
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
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
                        AqpjShiguEcharts(algorithmname,data);//这里可以加入自己的处理代码，切换不同的图形
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
function BingPingjiaEcharts(algorithmname,data) {
    dataJson = JSON.parse(data);
    var Keys = [];
    var Values = [];
    var i = 0;
    for (var key in dataJson) {
        Keys[i] = key;
        Values[i] = dataJson[key];
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
        title: {
            text:'安全评价沉降等级预测\n\n'+Keys[1]+':'+Values[1],
            // subtext:Keys[1]+':'+Values[1],
            x:'center',
            y:'top',
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
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
                        AqpjPingjiaEcharts(algorithmname,data);//这里可以加入自己的处理代码，切换不同的图形
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
function BingXiangEcharts(algorithmname,data) {

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
        title: {
            text:'安全评价变形等级预测'+'     状态：'+status,
            // subtext:'环号：'+Ring+'    时间：'+time+'    里程：'+mileage,
            x:'center',
            y:'top',
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
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
                        AqpjXiangEcharts(algorithmname,data);//这里可以加入自己的处理代码，切换不同的图形
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

function RoseShiguEcharts(algorithmname,data) {
    dataJson = JSON.parse(data);
    var Keys = [];
    var Values = [];
    var i = 0;
    for (var key in dataJson) {
        Keys[i] = key;
        Values[i] = dataJson[key];
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
        title: {
            text:'安全评价事故分类预测',
            // subtext:'环号：'+Ring+'    时间：'+time+'    里程：'+mileage,
            x:'center',
            y:'top',
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            x : 'center',
            y : 'bottom',
            data:Keys
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel']
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'半径模式',
                type:'pie',
                radius : [20, 110],
                center : ['25%', '50%'],
                roseType : 'radius',
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                lableLine: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                data:ValuesArray
            },
            {
                name:'面积模式',
                type:'pie',
                radius : [30, 110],
                center : ['75%', '50%'],
                roseType : 'area',
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
                        AqpjShiguEcharts(algorithmname,data);//这里可以加入自己的处理代码，切换不同的图形
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
function RosePingjiaEcharts(algorithmname,data) {
    dataJson = JSON.parse(data);
    var Keys = [];
    var Values = [];
    var i = 0;
    for (var key in dataJson) {
        Keys[i] = key;
        Values[i] = dataJson[key];
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
        title: {
            text:'安全评价沉降等级预测\n\n'+Keys[1]+':'+Values[1],
            // subtext:Keys[1]+':'+Values[1],
            x:'center',
            y:'top',
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            x : 'center',
            y : 'bottom',
            data:Keys
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel']
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'半径模式',
                type:'pie',
                radius : [20, 110],
                center : ['25%', '50%'],
                roseType : 'radius',
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                lableLine: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                data:ValuesArray
            },
            {
                name:'面积模式',
                type:'pie',
                radius : [30, 110],
                center : ['75%', '50%'],
                roseType : 'area',
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
                        AqpjPingjiaEcharts(algorithmname,data);//这里可以加入自己的处理代码，切换不同的图形
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
function RoseXiangEcharts(algorithmname,data) {

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
        title: {
            text:'安全评价变形等级预测'+'     状态：'+status,
            // subtext:'环号：'+Ring+'    时间：'+time+'    里程：'+mileage,
            x:'center',
            y:'top'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            x : 'center',
            y : 'bottom',
            data:Keys
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel']
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'半径模式',
                type:'pie',
                radius : [20, 110],
                center : ['25%', '50%'],
                roseType : 'radius',
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                lableLine: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                data:ValuesArray
            },
            {
                name:'面积模式',
                type:'pie',
                radius : [30, 110],
                center : ['75%', '50%'],
                roseType : 'area',
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
                        AqpjXiangEcharts(algorithmname,data);//这里可以加入自己的处理代码，切换不同的图形
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