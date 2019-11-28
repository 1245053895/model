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
        }]
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