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
    myChart.clear();
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
                        BingPredictEcharts(algorithmname,data); //这里可以加入自己的处理代码，切换不同的图形
                    }
                }
            }
        }
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

 function BingPredictEcharts(algorithmname,data) {
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
                         DmcjPredictEcharts(algorithmname,data);//这里可以加入自己的处理代码，切换不同的图形
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