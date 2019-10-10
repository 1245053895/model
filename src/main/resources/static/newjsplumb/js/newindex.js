$(document).ready(function(){
    //生成uuid
    function S4() {
        return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
    }
    function guid() {
        return S4()+S4()+""+S4()+""+S4()+""+S4()+""+S4()+S4()+S4();
    }


//设置左侧为可复制的
    $("#left").children().draggable({
        helper: "clone",
        scope: "zlg",
    });

//设置右侧为拖拽存放区
    var i=0;
    $("#main").droppable({
        scope:"zlg",
        drop:function (event , ui) {
            var left = parseInt(ui.offset.left - $(this).offset().left)+268.78;
            var top = parseInt(ui.offset.top - $(this).offset().top)+81;
            var name = ui.draggable[0].id;
            switch (name) {
                case "node1":
                    var id = guid();
                    $(this).append('<div class="node" style="position: absolute" id="' + id + '" >' + $(ui.helper).html() + '</div>');
                    $("#" + id).css("left", left).css("top", top);
                    jsPlumb.addEndpoint(id, { anchors: "TopCenter" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "RightMiddle" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "BottomCenter" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "LeftMiddle" }, hollowCircle);
                    jsPlumb.draggable(id);
                    $("#" + id).draggable({ containment: "parent" });
                    doubleclick("#" + id);
                    break;
                case "node2":
                    id = guid();
                    $(this).append("<div class='node node2css' style='position: absolute' id='" + id + "'>" + $(ui.helper).html() + "</div>");
                    $("#" + id).css("left", left).css("top", top);
                    jsPlumb.addEndpoint(id, { anchors: "TopCenter" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "RightMiddle" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "BottomCenter" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "LeftMiddle" }, hollowCircle);
                    jsPlumb.addEndpoint(id, hollowCircle);
                    jsPlumb.draggable(id);
                    $("#" + id).draggable({ containment: "parent" });
                    doubleclick("#" + id);
                    break;
                case "node3":
                    id = guid();
                    $(this).append("<div class='node node3css' style='position: absolute' id='" + id + "'>" + $(ui.helper).html() + "</div>");
                    $("#" + id).css("left", left).css("top", top);
                    jsPlumb.addEndpoint(id, { anchors: "TopCenter" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "RightMiddle" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "BottomCenter" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "LeftMiddle" }, hollowCircle);
                    jsPlumb.addEndpoint(id, hollowCircle);
                    jsPlumb.draggable(id);
                    $("#" + id).draggable({ containment: "parent" });
                    doubleclick("#" + id);
                    break;
                case "node4":
                    id = guid();
                    $(this).append('<div class="node node4css" style=\'position: absolute\' id="' + id + '" >' + $(ui.helper).html() + '</div>');
                    $("#" + id).css("left", left).css("top", top);
                    jsPlumb.addEndpoint(id, { anchors: "TopCenter" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "RightMiddle" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "BottomCenter" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "LeftMiddle" }, hollowCircle);
                    jsPlumb.draggable(id);
                    $("#" + id).draggable({ containment: "parent" });
                    doubleclick("#" + id);
                    break;
                case "node5":
                    id = guid();
                    $(this).append('<div class="node node4css" style=\'position: absolute\' id="' + id + '" >' + $(ui.helper).html() + '</div>');
                    $("#" + id).css("left", left).css("top", top);
                    jsPlumb.addEndpoint(id, { anchors: "TopCenter" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "RightMiddle" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "BottomCenter" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "LeftMiddle" }, hollowCircle);
                    jsPlumb.draggable(id);
                    $("#" + id).draggable({ containment: "parent" });
                    doubleclick("#" + id);
                    break;
                case "node6":
                    id = guid();
                    $(this).append('<div class="node node4css" style=\'position: absolute\' id="' + id + '" >' + $(ui.helper).html() + '</div>');
                    $("#" + id).css("left", left).css("top", top);
                    jsPlumb.addEndpoint(id, { anchors: "TopCenter" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "RightMiddle" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "BottomCenter" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "LeftMiddle" }, hollowCircle);
                    jsPlumb.draggable(id);
                    $("#" + id).draggable({ containment: "parent" });
                    doubleclick("#" + id);
                    break;
                case "node7":
                    id = guid();
                    $(this).append('<div class="node node4css" style=\'position: absolute\' id="' + id + '" >' + $(ui.helper).html() + '</div>');
                    $("#" + id).css("left", left).css("top", top);
                    jsPlumb.addEndpoint(id, { anchors: "TopCenter" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "RightMiddle" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "BottomCenter" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "LeftMiddle" }, hollowCircle);
                    jsPlumb.draggable(id);
                    $("#" + id).draggable({ containment: "parent" });
                    doubleclick("#" + id);
                    break;
                case "node8":
                    id = guid();
                    $(this).append('<div class="node node4css" style=\'position: absolute\' id="' + id + '" >' + $(ui.helper).html() + '</div>');
                    $("#" + id).css("left", left).css("top", top);
                    jsPlumb.addEndpoint(id, { anchors: "TopCenter" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "RightMiddle" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "BottomCenter" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "LeftMiddle" }, hollowCircle);
                    jsPlumb.draggable(id);
                    $("#" + id).draggable({ containment: "parent" });
                    doubleclick("#" + id);
                    break;
                case "node9":
                    id = guid();
                    $(this).append('<div class="node node4css" style=\'position: absolute\' id="' + id + '" >' + $(ui.helper).html() + '</div>');
                    $("#" + id).css("left", left).css("top", top);
                    jsPlumb.addEndpoint(id, { anchors: "TopCenter" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "RightMiddle" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "BottomCenter" }, hollowCircle);
                    jsPlumb.addEndpoint(id, { anchors: "LeftMiddle" }, hollowCircle);
                    jsPlumb.draggable(id);
                    $("#" + id).draggable({ containment: "parent" });
                    doubleclick("#" + id);
                    break;
            }
        }
    });

//基本连接线样式
    var connectorPaintStyle = {
        lineWidth: 4,
        strokeStyle: "#61B7CF",
        joinstyle: "round",
        outlineColor: "white",
        outlineWidth: 2
    };

// 鼠标悬浮在连接线上的样式
    var connectorHoverStyle = {
        lineWidth: 4,
        strokeStyle: "green",
        outlineWidth: 2,
        outlineColor: "white"
    };

//端点的颜色样式
    var paintStyle = {
        strokeStyle: "#1e8151",
        fillStyle: "transparent",
        radius: 3,
        lineWidth:6 ,
    }

// 鼠标悬浮在端点上的样式
    var hoverPaintStyle = {
        outlineStroke: 'lightblue'
    }

//设置连接端点和连接线
    var hollowCircle = {
        endpoint: ["Dot", { radius: 8 }],  //端点的形状
        connectorStyle: connectorPaintStyle,//连接线的颜色，大小样式
        connectorHoverStyle: connectorHoverStyle,
        paintStyle: paintStyle,
        hoverPaintStyle: hoverPaintStyle ,
        isSource: true,    //是否可以拖动（作为连线起点）
        connector: ["Flowchart", { stub: [40, 60], gap: 10, cornerRadius: 5, alwaysRespectStubs: true }],  //连接线的样式种类有[Bezier],[Flowchart],[StateMachine ],[Straight ]
        isTarget: true,    //是否可以放置（连线终点）
        maxConnections: -1,    // 设置连接点最多可以连接几条线
        connectorOverlays: [["Arrow"]]
    };

//鼠标进入增加一个删除的小图标
    $("#main").on("mouseenter", ".node", function () {



        var delpicture = $(this).append('<img src="/newjsplumb/images/clone2.png"  style="position: absolute;" id="dletimg1"/>');
        var editpicture = $(this).append('<img src="/newjsplumb/images/xinread4.png"  style="position: absolute;" id="dletimg2"/>');

        if(delpicture !== null){
            var widthnum = $(this).css("width").substr(0,5);
            if (widthnum <  60) {
                $("#dletimg1").css("left", 67).css("top", -13);
            } else {
                $("#dletimg1").css("left", 90).css("top", -12);
            }
        }else{
            alert("加载删除失败")
        }

        if(editpicture !== null){
            var widthnum1 = $(this).css("width").substr(0,5);
            if (widthnum1 <  60) {
                $("#dletimg2").css("left", 67).css("top", -13);
            } else {
                $("#dletimg2").css("left", 90).css("top", 50);
            }
        }else{
            alert("加载编辑失败")
        }

        if(selectpicture !== null){
            var widthnum2 = $(this).css("width").substr(0,5);
            if (widthnum2 <  60) {
                $("#dletimg3").css("left", 67).css("top", -13);
            } else {
                $("#dletimg3").css("left", -20).css("top", 50);
            }
        }else{
            alert("加载查询失败")
        }

    });

//鼠标离开小图标消失
    $("#main").on("mouseleave", ".node", function () {
        $("#dletimg1").remove();
        $("#dletimg2").remove();
    });

//节点小图标的单击事件
    $("#main").on("click", "#dletimg1",function () {
        if (confirm("确定要删除此节点吗?")) {
            // var str = document.getElementById("uuid_Value").text;
            // alert(str);
            jsPlumb.removeAllEndpoints($(this).parent().attr("id"));
            $(this).parent().remove();
        }
    });


    $("#main").on("click", "#dletimg2",function () {
        if (confirm("确定要查询节点吗?")) {
            var xin = $(this).parent().attr("id");
            // $("#uuid_Value").text(xin);
            $("#testval1").attr("value",xin);
            $.ajax({
                async: false,
                cache: false,
                type: "POST",
                dataType: "json",
                url: "/getNodeInfo",
                data: {
                    uuid: xin
                },
                success: function (data) {
                    for (var key in data) {
                        // alert(data[key]);
                        $("#" + key).val(data[key]);
                    }
                }
            })
        }
    });

//连接线的双击事件
    jsPlumb.bind("dblclick", function (conn, originalEvent) {
        if (confirm("确定删除此连线吗？"))
            jsPlumb.detach(conn);
    });
//双击节点内容区域时的事件
    function doubleclick(id) {
        $(id).dblclick(function () {
            var aimOb = $(this);
            var text = $(this).text();
            let imgSrc = $(this).find("img").attr("src");
            let imgClick = $(this).find("img").attr("onclick");
            $(this).html("");
            $(this).append("<input class=\"chart-text-edit\" id=\"inputchart\" type=\"text\" value=\"" + text + "\" />");
            $("#inputchart").blur(function () {
                var newEle = "<img onclick=\"" + imgClick + "\" src=\"" + imgSrc + "\">" + $("#inputchart").val();
                alert(newEle);
                aimOb.html(newEle);
            });
        });
    }

    //
    // function changeValue(id) {
    //     $(id).dblclick(function () {
    //         var aimOb = $(this);
    //         var text = $(this).text();
    //         let imgSrc = $(this).find("img").attr("src");
    //         let imgClick = $(this).find("img").attr("onclick")
    //         $(this).html("");
    //         $(this).append("<input class=\"chart-text-edit\" id=\"inputchart\" type=\"text\" value=\"" + text + "\" />");
    //         $("#inputchart").blur(function () {
    //             var newEle = "<img onclick=\"" + imgClick + "\" src=\"" + imgSrc + "\">" + $("#inputchart").val();
    //             alert(newEle);
    //             aimOb.html(newEle);
    //         });
    //     });
    // }








})
