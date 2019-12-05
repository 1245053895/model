function NhyhEcharts(algorithmname,data) {
    if (algorithmname.indexOf("NhyhLoadPredict") != -1) {
        NhyhLoadPredictEcharts(algorithmname,data);
    }
    if (algorithmname.indexOf("NhyhEnergyOptimize") != -1) {
        NhyhEnergyEcharts(algorithmname,data);
    }
}

function NhyhLoadPredictEcharts(algorithmname,data) {
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
            text:'能耗优化冷负荷预测',
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
                myClock: {//自定义按钮 danielinbiti,这里增加，selfbuttons可以随便取名字
                    show: true,//是否显示
                    title: '切换为仪表图', //鼠标移动上去显示的文字
                    icon: 'image:///EchartsZlj/仪表图.png', //图标
                    onclick: function () {//点击事件,这里的option1是chart的option信息
                        ClockPredictEcharts(algorithmname,data); //这里可以加入自己的处理代码，切换不同的图形
                    }
                }
            }
        }
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}
function NhyhEnergyEcharts(algorithmname,data) {
    document.getElementById("container").setAttribute("style","");
    document.getElementById("createtable1").setAttribute("style","height: 11vh;overflow-y: auto;margin-top: 0vh");
    document.getElementById("createtable2").setAttribute("style","height: 11vh;overflow-y: auto;margin-top: 8vh");
    document.getElementById("createtable3").setAttribute("style","height: 11vh;overflow-y: auto;margin-top: 8vh");
    document.getElementById("createtable4").setAttribute("style","height: 11vh;overflow-y: auto;margin-top: 8vh");
    document.getElementById("createtable1").innerHTML = "";
    document.getElementById("createtable2").innerHTML = "";
    document.getElementById("createtable3").innerHTML = "";
    document.getElementById("createtable4").innerHTML = "";

    dataJson = eval("("+data+")"); //字符串转json
    datas = dataJson[0];

    // datas=dataJson["datas"];
    // 制冷需求
    var Qs = [];
    Qs = datas["制冷需求"];
        var Keys = [];
        var Values = [];
        var i = 0;
        for (var key in Qs) {
            Keys[i] = key;
            Values[i] = Qs[key];
            i++;
        }
    var rowCount = 1;//行
    var cellCount = Keys;//列

    var table = $("<table id='table1' border=\"1\" class=\"table table-border table-bordered table-hover table-bg table-sort\">");
    table.appendTo($("#createtable1"));
    var Dc_x=document.getElementById('table1').createCaption();
    Dc_x.innerHTML='制冷需求';
    var trHeader = $("<tr class=\"text-c\"></tr>");
    trHeader.appendTo(table);

    for (var j = 0; j < cellCount.length; j++) {
        var td = $("<td>" + cellCount[j] + "</td>");
        td.appendTo(trHeader);
    }

    for (var i = 0; i < rowCount; i++) {
        var tr = $("<tr class=\"text-c\"></tr>");
        tr.appendTo(table);
        for (var j = 0; j < cellCount.length; j++) {
                var val = Values[j];
            var td = $("<td>" + val + "</td>");
            td.appendTo(tr);
        }
    }
    $("#createtable1").append("</table>");

    var Qs = [];
    Qs = datas["送风机信息"];
    var Keys = [];
    var Values = [];
    var i = 0;
    for (var key in Qs) {
        Keys[i] = key;
        Values[i] = Qs[key];
        i++;
    }
    var rowCount = 1;//行
    var cellCount = Keys;//列

    var table = $("<table id='table2' border=\"1\" class=\"table table-border table-bordered table-hover table-bg table-sort\">");
    table.appendTo($("#createtable2"));
    var Dc_x=document.getElementById('table2').createCaption();
    Dc_x.innerHTML='送风机信息';
    var trHeader = $("<tr class=\"text-c\"></tr>");
    trHeader.appendTo(table);

    for (var j = 0; j < cellCount.length; j++) {
        var td = $("<td>" + cellCount[j] + "</td>");
        td.appendTo(trHeader);
    }

    for (var i = 0; i < rowCount; i++) {
        var tr = $("<tr class=\"text-c\"></tr>");
        tr.appendTo(table);
        for (var j = 0; j < cellCount.length; j++) {
            var val = Values[j];
            var td = $("<td>" + val + "</td>");
            td.appendTo(tr);
        }
    }
    $("#createtable2").append("</table>");

    var Qs = [];
    Qs = datas["排风机信息"];
    var Keys = [];
    var Values = [];
    var i = 0;
    for (var key in Qs) {
        Keys[i] = key;
        Values[i] = Qs[key];
        i++;
    }
    var rowCount = 1;//行
    var cellCount = Keys;//列

    var table = $("<table id='table3' border=\"1\" class=\"table table-border table-bordered table-hover table-bg table-sort\">");
    table.appendTo($("#createtable3"));
    var Dc_x=document.getElementById('table3').createCaption();
    Dc_x.innerHTML='排风机信息';
    var trHeader = $("<tr class=\"text-c\"></tr>");
    trHeader.appendTo(table);
    for (var j = 0; j < cellCount.length; j++) {
        var td = $("<td>" + cellCount[j] + "</td>");
        td.appendTo(trHeader);
    }

    for (var i = 0; i < rowCount; i++) {
        var tr = $("<tr class=\"text-c\"></tr>");
        tr.appendTo(table);
        for (var j = 0; j < cellCount.length; j++) {
            var val = Values[j];
            var td = $("<td>" + val + "</td>");
            td.appendTo(tr);
        }
    }
    $("#createtable3").append("</table>");

    var Qs = [];
    Qs = datas["A端阀门信息"];
    var Keys = [];
    var Values = [];
    var i = 0;
    for (var key in Qs) {
        Keys[i] = key;
        Values[i] = Qs[key];
        i++;
    }
    var rowCount = 1;//行
    var cellCount = Keys;//列

    var table = $("<table id='table4' border=\"1\" class=\"table table-border table-bordered table-hover table-bg table-sort\">");
    table.appendTo($("#createtable4"));
    var Dc_x=document.getElementById('table4').createCaption();
    Dc_x.innerHTML='A端阀门信息';
    var trHeader = $("<tr class=\"text-c\"></tr>");
    trHeader.appendTo(table);
    for (var j = 0; j < cellCount.length; j++) {
        var td = $("<td>" + cellCount[j] + "</td>");
        td.appendTo(trHeader);
    }

    for (var i = 0; i < rowCount; i++) {
        var tr = $("<tr class=\"text-c\"></tr>");
        tr.appendTo(table);
        for (var j = 0; j < cellCount.length; j++) {
            var val = Values[j];
            var td = $("<td>" + val + "</td>");
            td.appendTo(tr);
        }
    }
    $("#createtable4").append("</table>");
}

function ClockPredictEcharts(algorithmname,data) {
    dataJson = JSON.parse(data);
    var Keys = [];
    var Values = [];
    var i = 0;
    for (var key in dataJson) {
        Keys[i] = key;
        Values[i] = dataJson[key];
        i++;
    }
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    myChart.clear();
    var app = {};
    option = null;
    option = {
        title: {
            text:'能耗优化冷负荷预测',
            // subtext:'环号：'+Ring+'    时间：'+time+'    里程：'+mileage,
            x:'center',
            y:'top',
        },
        tooltip : {
            formatter: "{a} <br/>{c} {b}"
        },
        toolbox: {
            show: true,
            feature: {
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        series : [
            {
                name: '冷负荷预测',
                type: 'gauge',
                z: 3,
                min: 0,
                max: 800,
                splitNumber: 10,
                radius: '50%',
                axisLine: {            // 坐标轴线
                    lineStyle: {       // 属性lineStyle控制线条样式
                        width: 10
                    }
                },
                axisTick: {            // 坐标轴小标记
                    length: 15,        // 属性length控制线长
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: 'auto'
                    }
                },
                splitLine: {           // 分隔线
                    length: 20,         // 属性length控制线长
                    lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                        color: 'auto'
                    }
                },
                axisLabel: {
                    backgroundColor: 'auto',
                    borderRadius: 2,
                    color: '#eee',
                    padding: 3,
                    textShadowBlur: 2,
                    textShadowOffsetX: 1,
                    textShadowOffsetY: 1,
                    textShadowColor: '#222'
                },
                title : {
                    // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder',
                    fontSize: 20,
                    fontStyle: 'italic'
                },
                detail : {
                    // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    formatter: function (value) {
                        value = (value + '').split('.');
                        value.length < 2 && (value.push('00'));
                        return ('00' + value[0]).slice(-2)
                            + '.' + (value[1] + '00').slice(0, 2);
                    },
                    fontWeight: 'bolder',
                    borderRadius: 3,
                    backgroundColor: '#444',
                    borderColor: '#aaa',
                    shadowBlur: 5,
                    shadowColor: '#333',
                    shadowOffsetX: 0,
                    shadowOffsetY: 3,
                    borderWidth: 2,
                    textBorderColor: '#000',
                    textBorderWidth: 2,
                    textShadowBlur: 2,
                    textShadowColor: '#fff',
                    textShadowOffsetX: 0,
                    textShadowOffsetY: 0,
                    fontFamily: 'Arial',
                    width: 100,
                    color: '#eee',
                    rich: {}
                },
                data:[{value: Values[1]/1000, name: 'KW'}]
            },
            {
                name: '客流量',
                type: 'gauge',
                center: ['20%', '55%'],    // 默认全局居中
                radius: '35%',
                min:0,
                max:1000,
                endAngle:45,
                splitNumber:10,
                axisLine: {            // 坐标轴线
                    lineStyle: {       // 属性lineStyle控制线条样式
                        width: 8
                    }
                },
                axisTick: {            // 坐标轴小标记
                    length:12,        // 属性length控制线长
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: 'auto'
                    }
                },
                splitLine: {           // 分隔线
                    length:20,         // 属性length控制线长
                    lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                        color: 'auto'
                    }
                },
                pointer: {
                    width:5
                },
                title: {
                    offsetCenter: [0, '-30%'],       // x, y，单位px
                },
                detail: {
                    // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder'
                },
                data:[{value: Values[0], name: '人'}]
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
                        NhyhLoadPredictEcharts(algorithmname,data);//这里可以加入自己的处理代码，切换不同的图形
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