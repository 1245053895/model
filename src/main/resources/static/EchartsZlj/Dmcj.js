 function DmcjEcharts(algorithmname,data) {
    if (algorithmname.indexOf("predict") != -1) {
        DmcjPredictEcharts(algorithmname,data);
    }
    if (algorithmname.indexOf("warning") != -1) {
        CreateDmcjWarningTable(algorithmname,data);
    }
     if (algorithmname.indexOf("rules") != -1) {
         CreateDmcjRulesTable(algorithmname,data);
     }
     if (algorithmname.indexOf("Suggest") != -1) {
         CreateDmcjSuggestTable(algorithmname,data);
     }
}
 function sortNumber(a,b){//升序
     return a - b
 }
function DmcjPredictEcharts(algorithmname,data) {
    document.getElementById("createtable").setAttribute("style","");
    document.getElementById("createtable").innerHTML = "";
     dataJson = eval("("+data+")");
    var Values = [];
    var Keys = [];
    var Ring = dataJson["Ring"][0];
    var time = dataJson["time"][0];
    var mileage = dataJson["mileage"][0];
    var i = 0;
    for (var key in dataJson) {
        var num = parseInt(key);
        if (!isNaN(num))
        {
          Keys[i] = num;
          i++;
        }
    }
    Keys.sort(sortNumber);
    for (var j = 0; j < Keys.length; j++ ){
        Values[j] = dataJson[Keys[j]][0];
    }

    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    option = {
        title: {
            text:'地面沉降预测',
            subtext:'环号：'+Ring+'    时间：'+time+'    里程：'+mileage,
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
            type: 'line',
            smooth: true
        }]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}
function CreateDmcjWarningTable(algorithmname,data) {
    document.getElementById("container").setAttribute("style","");
    document.getElementById("createtable").innerHTML = "";
     dataJson = JSON.parse(data);

    var Keys = [];
    var Values = [];
    var i = 0;
    for (var key in dataJson) {
        var num = parseInt(key);
        if (!isNaN(num))
        {
            Keys[i] = num;
            i++;
        }
    }
    Keys.sort(sortNumber);
    for (var m = 0; m < Keys.length; m++ ){
        Values[m] = dataJson[Keys[m]];
    }

    var rowCount = Keys.length;//行
    var cellCount = ["相对环号","环号","时间","里程","地面沉降量预警"];//列

    var table = $("<table border=\"1\" class=\"table table-border table-bordered table-hover table-bg table-sort\">");
    table.appendTo($("#createtable"));

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
            if (j == 0) {
                var val = Keys[i];
                var td = $("<td>" + val + "</td>");
                td.appendTo(tr);
            }
            else {
                var value1 = [];
                 value1 = Values[i];
                     var value2 = [];
                     value2 = value1[0];
                     if (value2 != undefined) {
                         var val = value2[j - 1];
                         var td = $("<td>" + val + "</td>");
                         td.appendTo(tr);
                     }
                     else {
                         var td = $("<td>" + "" + "</td>");
                         td.appendTo(tr);
                     }
            }
        }
    }
    $("#createtable").append("</table>");
}
function CreateDmcjRulesTable(algorithmname,data) {
     document.getElementById("container").setAttribute("style","");
     document.getElementById("createtable").innerHTML = "";
    var dataArray = eval("("+data+")");
     var Values = dataArray;
     var rowCount = dataArray.length;//行
     var cellCount = ["关联规则","支持度","置信度"];//列

     var table = $("<table border=\"1\" class=\"table table-border table-bordered table-hover table-bg table-sort\">");
     table.appendTo($("#createtable"));

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
             var Value = [];
             Value = Values[i];
             var val = Value[j];
             var td = $("<td>" + val + "</td>");
             td.appendTo(tr);
         }
     }
     $("#createtable").append("</table>");
 }
 function CreateDmcjSuggestTable(algorithmname,data) {
     document.getElementById("container").setAttribute("style","");
     document.getElementById("createtable").innerHTML = "";
     var dataArray = eval("("+data+")");
     var Keys = dataArray[0];
     var Values = dataArray[1];
     var rowCount = Values.length;//行
     var cellCount = ["类型","推荐值"];//列

     var table = $("<table border=\"1\" class=\"table table-border table-bordered table-hover table-bg table-sort\">");
     table.appendTo($("#createtable"));

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
             if (j==0) {
                 var val = Keys[i];
             }
            else {
                 var val = Values[i];
             }
             var td = $("<td>" + val + "</td>");
             td.appendTo(tr);
         }
     }
     $("#createtable").append("</table>");
 }