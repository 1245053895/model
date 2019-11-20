/*轴线纠偏与决定参数的关联规则--csv可视化*/
/*轴线纠偏预测模型*/
function zxpcPredict(algorithmname,data) {
    dataJson=JSON.parse(data); //字符串转json
    //获得key为QcPredValue的value值
    var Qc=[];
    var Dc=[];
    var Qs=[];
    var Ds=[];
    Qc=dataJson["QcPredValue"];
    Dc=dataJson["DcPredValue"];
    Qs=dataJson["QsPredValue"];
    Ds=dataJson["DsPredValue"];
    var Qcvalue=Qc[0];
    var Dcvalue=Dc[0];
    var Qsvalue=Qs[0];
    var Dsvalue=Qs[0];
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    option = {
        title: {
            text: '轴线纠偏预测模型'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['QcPredValue','DcPredValue','QsPredValue','DsPredValue']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25]
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'QcPredValue',
                type:'line',
                stack: '总量',
                data: Qcvalue
            },
            {
                name:'DcPredValue',
                type:'line',
                stack: '总量',
                data: Dcvalue
            },
            {
                name:'QsPredValue',
                type:'line',
                stack: '总量',
                data: Qsvalue
            },
            {
                name:'DsPredValue',
                type:'line',
                stack: '总量',
                data: Dsvalue
            },
        ]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}

/*轴线纠偏最大位移预测*/
function zxpcClassfier(algorithmname,data) {
    dataJson=JSON.parse(data); //字符串转json
    //获得key为QcPredValue的value值
    var ring=[];
    var Qcpre=[];
    var Dcpre=[];
    var Qspre=[];
    var Dspre=[];
    ring=dataJson["Ring"];
    Qcpre=dataJson["QcPredValue"];
    Dcpre=dataJson["DcPredValue"];
    Qspre=dataJson["QsPredValue"];
    Dspre=dataJson["DsPredValue"];
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    option = {
        title: {
            text: '轴线纠偏最大位移预测模型'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['QcPredValue','DcPredValue','QsPredValue','DsPredValue']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ring
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'QcPredValue',
                type:'line',
                stack: '总量',
                data: Qcpre
            },
            {
                name:'DcPredValue',
                type:'line',
                stack: '总量',
                data: Dcpre
            },
            {
                name:'QsPredValue',
                type:'line',
                stack: '总量',
                data: Qspre
            },
            {
                name:'DsPredValue',
                type:'line',
                stack: '总量',
                data: Dspre
            },
        ]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}

/*轴线多环纠偏表格显示*/
function zxpcRecitify(algorithmname,data) {
    document.getElementById("container").setAttribute("style","");
    document.getElementById("createtable").innerHTML = "";
   dataJson=JSON.parse(data); //字符串转json
    //获得key为Total_ring的value值
    var rowCount=dataJson["Total_ring"];
    var each_ring=[];
    each_ring=dataJson["Each_ring"];
    // var rowCount=row+1;
    var cellCount=["每环纠偏量","刀盘扭矩","刀盘扭矩KN.M","刀盘转速","GrD左推进回路压力","螺旋机扭矩","GrB右推进回路压力","No1超挖刀行程"];
    var table = $("<table border=\"1\" class=\"table table-border table-bordered table-hover table-bg table-sort\">");
    table.appendTo($("#createtable"));

    var trHeader = $("<tr class=\"text-c\"></tr>");
    trHeader.appendTo(table);
    for (var j=0; j<cellCount.length;j++){
            var td = $("<td>" + cellCount[j] + "</td>");
        td.appendTo(trHeader);
    }

    for (var i = 0; i < rowCount; i++) {
        var tr = $("<tr class=\"text-c\"></tr>");
        tr.appendTo(table);
        for (var j = 0; j < cellCount.length; j++) {
            var hang = [];
            hang = each_ring[i];
            if (j==0){
                var val = hang[0];
            }
            else {
                var notOne = [];
                notOne = hang[1];
                var hangData = [];
                hangData = notOne[1];
                var val = hangData[j-1];
            }

            var td = $("<td>" + val + "</td>");
            td.appendTo(tr);
        }
    }
    $("#createtable").append("</table>");
}
/*非并行化关联规则*/
function zxpcrules(algorithmname,data) {
    document.getElementById("container").setAttribute("style","");
    document.getElementById("createtable1").innerHTML = "";
    document.getElementById("createtable2").innerHTML = "";
    document.getElementById("createtable3").innerHTML = "";
    document.getElementById("createtable4").innerHTML = "";
 //qs_rules
    datas=eval("("+data+")");
    var qs=[];
    qs=datas["qs_rules"];
    if (qs != undefined && qs.length != 0) {
        var cellCountqs = ["关联规则", "支持度", "置信度"];
        var rowCountqs = qs.length;
        var tableqs = $("<table id='qstable' border=\"1\" class=\"table table-border table-bordered table-hover table-bg table-sort\">");
        tableqs.appendTo($("#createtable1"));
        var qs_x=document.getElementById('qstable').createCaption();
        qs_x.innerHTML='导向垂直后点关联规则结果';

        var trHeaderqs = $("<tr class=\"text-c\"></tr>");
        trHeaderqs.appendTo(tableqs);
        for (var j = 0; j < cellCountqs.length; j++) {
            var tdqs = $("<td>" + cellCountqs[j] + "</td>");
            tdqs.appendTo(trHeaderqs);
        }
        for (var i = 0; i < rowCountqs; i++) {
            var trqs = $("<tr class=\"text-c\"></tr>");
            trqs.appendTo(tableqs);
            for (var j = 0; j < cellCountqs.length; j++) {
                var Lineqs = [];
                Lineqs = qs[i];
                var valqs = Lineqs[j];
                var tdqs = $("<td>" + valqs + "</td>");
                tdqs.appendTo(trqs);
            }
        }
        $("#createtable1").append("</table>");
    }
    else {
        document.getElementById("createtable1").setAttribute("style","");
    }
    //hs_rules //导向水平后点关联规则结果
    var hs=[];
    hs=datas["hs_rules"];
    if (hs != undefined && hs.length != 0) {
        var cellCounths = ["关联规则", "支持度", "置信度"];
        var rowCounths =hs.length;
        var tablehs = $("<table id='hstable' border=\"1\" class=\"table table-border table-bordered table-hover table-bg table-sort\">");
        tablehs.appendTo($("#createtable2"));
        var hs_x=document.getElementById('hstable').createCaption();
        hs_x.innerHTML='导向水平后点关联规则结果';
        var trHeaderhs = $("<tr class=\"text-c\"></tr>");
        trHeaderhs.appendTo(tablehs);
        for (var j = 0; j < cellCounths.length; j++) {
            var tdhs = $("<td>" + cellCounths[j] + "</td>");
            tdhs.appendTo(trHeaderhs);
        }
        for (var i = 0; i < rowCounths; i++) {
            var trhs = $("<tr class=\"text-c\"></tr>");
            trhs.appendTo(tablehs);
            for (var j = 0; j < cellCounths.length; j++) {
                var Linehs = [];
                Linehs = qs[i];
                var valhs = Linehs[j];
                var tdhs = $("<td>" + valhs + "</td>");
                tdhs.appendTo(trhs);
            }
        }
        $("#createtable2").append("</table>");
    }
    else {
        document.getElementById("createtable2").setAttribute("style","");
    }
    //qc_rules   导向垂直前点关联规则结果
    var qc=[];
    qc=datas["qc_rules"];
    if (qc != undefined && qc.length != 0) {
        var cellCountqc = ["关联规则", "支持度", "置信度"];
        var rowCountqc =qc.length;
        var tableqc = $("<table id='qctable' border=\"1\" class=\"table table-border table-bordered table-hover table-bg table-sort\">");
        tableqc.appendTo($("#createtable3"));
        var qc_x=document.getElementById('qctable').createCaption();
        qc_x.innerHTML='导向垂直前点关联规则结果';

        var trHeaderqc = $("<tr class=\"text-c\"></tr>");
        trHeaderqc.appendTo(tableqc);
        for (var j = 0; j < cellCountqc.length; j++) {
            var tdqc = $("<td>" + cellCountqc[j] + "</td>");
            tdqc.appendTo(trHeaderqc);
        }
        for (var i = 0; i < rowCountqc; i++) {
            var trqc = $("<tr class=\"text-c\"></tr>");
            trqc.appendTo(tableqc);
            for (var j = 0; j < cellCountqc.length; j++) {
                var Lineqc = [];
                Lineqc = qc[i];
                var valqc = Lineqc[j];
                var tdqc = $("<td>" + valqc + "</td>");
                tdqc.appendTo(trqc);
            }
        }
        $("#createtable3").append("</table>");
    }
    else {
        document.getElementById("createtable3").setAttribute("style","");
    }

    //hc_rules 导向水平前点关联规则
    var hc=[];
    hc=datas["hc_rules"];
    if (hc != undefined && hc.length != 0) {
        var cellCounthc = ["关联规则", "支持度", "置信度"];
        var rowCounthc =hc.length;
        var tablehc = $("<table id='hctable' border=\"1\" class=\"table table-border table-bordered table-hover table-bg table-sort\">");
        tablehc.appendTo($("#createtable4"));
        var hc_x=document.getElementById('hctable').createCaption();
        hc_x.innerHTML='导向水平前点关联规则';
        var trHeaderhc = $("<tr class=\"text-c\"></tr>");
        trHeaderhc.appendTo(tablehc);
        for (var j = 0; j < cellCounthc.length; j++) {
            var tdhc = $("<td>" + cellCounthc[j] + "</td>");
            tdhc.appendTo(trHeaderhc);
        }
        for (var i = 0; i < rowCounthc; i++) {
            var trhc = $("<tr class=\"text-c\"></tr>");
            trhc.appendTo(tablehc);
            for (var j = 0; j < cellCounthc.length; j++) {
                var Linehc = [];
                Linehc = hc[i];
                var valhc = Linehc[j];
                var tdhc = $("<td>" + valhc + "</td>");
                tdhc.appendTo(trhc);
            }
        }
        $("#createtable4").append("</table>");
    }
    else {
        document.getElementById("createtable4").setAttribute("style","");
    }


}

/*轴线报警预测*/
function zxpcWarning(algorithmname,data) {
    document.getElementById("container").setAttribute("style","");
    document.getElementById("createtable1").innerHTML = "";
    document.getElementById("createtable2").innerHTML = "";
    document.getElementById("createtable3").innerHTML = "";
    document.getElementById("createtable4").innerHTML = "";

    datas = eval("("+data+")"); //字符串转json
    // datas=dataJson["datas"];
    //Qs 切口水平方向报警预测返回为null
    var Qs=[];
    Qs = datas["Qs"];
    if (Qs != undefined && Qs.length != 0) {
        var cellCountQs = ["环号", "时间", "轴线偏差量预测"];
        var rowCountQs = Qs.length;
        var tableQs = $("<table id='Qstable' border=\"1\" class=\"table table-border table-bordered table-hover table-bg table-sort\">");
        tableQs.appendTo($("#createtable1"));
        var Qs_x=document.getElementById('Qstable').createCaption();
        Qs_x.innerHTML='切口水平方向报警预测';
        var trHeaderQs = $("<tr class=\"text-c\"></tr>");
        trHeaderQs.appendTo(tableQs);
        for (var j = 0; j < cellCountQs.length; j++) {
            var tdQs = $("<td>" + cellCountQs[j] + "</td>");
            tdQs.appendTo(trHeaderQs);
        }
        for (var i = 0; i < rowCountQs; i++) {
            var trQs = $("<tr class=\"text-c\"></tr>");
            trQs.appendTo(tableQs);
            for (var j = 0; j < cellCountQs.length; j++) {
                var QcLineQs = [];
                QcLineQs = Qs[i];
                var valQs = QcLineQs[j];
                var tdQs = $("<td>" + valQs + "</td>");
                tdQs.appendTo(trQs);
            }
        }
        $("#createtable1").append("</table>");
    }
    else {
        document.getElementById("createtable1").setAttribute("style","");
    }
    //Qc 切口垂直方向报警预测
    var Qc=[];
    Qc=datas["Qc"];
    if (Qc != undefined && Qc.length != 0) {
        var cellCount = ["环号", "时间", "轴线偏差量预测"];
        var rowCount = Qc.length;
        var table = $("<table id='Qctable' border=\"1\" class=\"table table-border table-bordered table-hover table-bg table-sort\">");
        table.appendTo($("#createtable2"));
        var Qc_x=document.getElementById('Qctable').createCaption();
        Qc_x.innerHTML='切口垂直方向报警预测';
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
                var QcLine = [];
                QcLine = Qc[i];
                var val = QcLine[j];
                var td = $("<td>" + val + "</td>");
                td.appendTo(tr);
            }
        }
        $("#createtable2").append("</table>");
    }
    else {
        document.getElementById("createtable2").setAttribute("style","");
    }
    //Ds 盾尾水平方向报警预测
    var Ds=[];
    Ds=datas["Ds"];
    if (Ds != undefined && Ds.length != 0) {
        var cellCountDs = ["环号", "时间", "轴线偏差量预测"];
        var rowCountDs = Ds.length;
        var tableDs = $("<table id='Dstable' border=\"1\" class=\"table table-border table-bordered table-hover table-bg table-sort\">");
        tableDs.appendTo($("#createtable3"));
        var Ds_x=document.getElementById('Dstable').createCaption();
        Ds_x.innerHTML='盾尾水平方向报警预测';
        var trHeaderDs = $("<tr class=\"text-c\"></tr>");
        trHeaderDs.appendTo(tableDs);
        for (var j = 0; j < cellCountDs.length; j++) {
            var tdDs = $("<td>" + cellCountDs[j] + "</td>");
            tdDs.appendTo(trHeaderDs);
        }
        for (var i = 0; i < rowCountDs; i++) {
            var trDs = $("<tr class=\"text-c\"></tr>");
            trDs.appendTo(tableDs);
            for (var j = 0; j < cellCountDs.length; j++) {
                var QcLineDs = [];
                QcLineDs = Ds[i];
                var valDs = QcLineDs[j];
                var tdDs = $("<td>" + valDs + "</td>");
                tdDs.appendTo(trDs);
            }
        }
        $("#createtable3").append("</table>");
    }
    else {
        document.getElementById("createtable3").setAttribute("style","");
    }
    //Dc 盾尾垂直方向报警预测
    var Dc=[];
    Dc=datas["Dc"];
    if (Dc != undefined && Dc.length != 0) {
        var cellCountDc = ["环号", "时间", "轴线偏差量预测"];
        var rowCountDc = Dc.length;
        var tableDc = $("<table id='Dctable' border=\"1\" class=\"table table-border table-bordered table-hover table-bg table-sort\">");
        tableDc.appendTo($("#createtable4"));
        var Dc_x=document.getElementById('Dctable').createCaption();
        Dc_x.innerHTML='盾尾垂直方向报警预测';
        var trHeaderDc = $("<tr class=\"text-c\"></tr>");
        trHeaderDc.appendTo(tableDc);
        for (var j = 0; j < cellCountDc.length; j++) {
            var tdDc = $("<td>" + cellCountDc[j] + "</td>");
            tdDc.appendTo(trHeaderDc);
        }
        for (var i = 0; i < rowCountDc; i++) {
            var trDc = $("<tr class=\"text-c\"></tr>");
            trDc.appendTo(tableDc);
            for (var j = 0; j < cellCountDc.length; j++) {
                var QcLineDc = [];
                QcLineDc = Dc[i];
                var valDc = QcLineDc[j];
                var tdDc = $("<td>" + valDc + "</td>");
                tdDc.appendTo(trDc);
            }
        }
        $("#createtable4").append("</table>");
    }
    else {
        document.getElementById("createtable4").setAttribute("style","");
    }
}

