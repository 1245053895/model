$(document).ready(function(){
    //生成uuid
    function S4() {
        return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
    }
    function guid() {
        return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
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
        connectorOverlays: [["Arrow", { width: 10, length: 10, location: 1 }]]
    };

//鼠标进入增加一个删除的小图标
    $("#main").on("mouseenter", ".node", function () {
        $(this).append('<img src="/newjsplumb/images/clone2.png"  style="position: absolute;" />');
        var widthnum = $(this).css("width").substr(0,5);
        if (widthnum <  60) {
            $("img").css("left", 67).css("top", -13);
        } else {
            $("img").css("left", 167).css("top", -12);
        }
    });
//鼠标离开小图标消失
    $("#main").on("mouseleave", ".node", function () {
        $("img").remove();
    });

//节点小图标的单击事件
    $("#main").on("click", "img",function () {
        if (confirm("确定要删除此节点吗?")) {
            jsPlumb.removeAllEndpoints($(this).parent().attr("id"));
            $(this).parent().remove();
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
            var text = $(this).text();
            $(this).html("");
            $(this).append("<input style='width: 130px' type='text' value='" + text + "' />");
            $(this).mouseleave(function () {
                $(this).html($("input[type='text']").val());
            });
        });
    }
})