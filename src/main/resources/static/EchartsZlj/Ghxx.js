function GhxxEcharts(algorithmname,data) {
    if (algorithmname.indexOf("GhxxInterStationScore") != -1) {
        GhxxInterStationScoreEcharts(algorithmname,data);
    }
}

function GhxxInterStationScoreEcharts(algorithmname,data) {
    document.getElementById("createtable").setAttribute("style","height: 50vh;overflow-y: auto;margin-top: 8vh");
    document.getElementById("createtable1").setAttribute("style","height: 15vh;overflow-y: auto;margin-top: 8vh");
    document.getElementById("createtable2").setAttribute("style","height: 15vh;overflow-y: auto;margin-top: 8vh");
    // document.getElementById("createtable").innerHTML = "";
    document.getElementById("createtable1").innerHTML = "";
    document.getElementById("createtable2").innerHTML = "";
    document.getElementById("createtable3").setAttribute("style","");
    document.getElementById("createtable4").setAttribute("style","");
    dataJson = eval("("+data+")");
    var value = [dataJson["Score"]];

    // alert(Keys);
    // alert(Values);
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        title: {
            text:'规划选线得分（满分20分）',
            // subtext:'环号：'+Ring+'    时间：'+time+'    里程：'+mileage,
            x:'center',
            y:'top',
        },
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
            data: value,
            type: 'bar',
            barWidth : 100
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


    Qs = dataJson["Station"];
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
    Dc_x.innerHTML='车站得分（满分10分）';
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


    Qs = dataJson["Inter"];
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
    Dc_x.innerHTML='区间得分（满分10分）';
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
}