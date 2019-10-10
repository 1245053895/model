$(function(){
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
                strokeStyle: "#216477",
                outlineWidth: 2,
                outlineColor: "white"
            };
            var hollowCircle = {
                endpoint: ["Dot", { radius: 8 }],  //端点的形状
                connectorStyle: connectorPaintStyle,//连接线的颜色，大小样式
                connectorHoverStyle: connectorHoverStyle,
                paintStyle: {
                    strokeStyle: "#1e8151",
                    fillStyle: "transparent",
                    radius: 2,
                    lineWidth: 2
                },        //端点的颜色样式
                //anchor: "AutoDefault",
                isSource: true,    //是否可以拖动（作为连线起点）
                connector: ["Flowchart", { stub: [40, 60], gap: 10, cornerRadius: 5, alwaysRespectStubs: true }],  //连接线的样式种类有[Bezier],[Flowchart],[StateMachine ],[Straight ]
                isTarget: true,    //是否可以放置（连线终点）
                maxConnections: -1,    // 设置连接点最多可以连接几条线
                connectorOverlays: [["Arrow", { width: 10, length: 10, location: 1 }]]
            };

$("#right").on("mouseenter", ".node", function () {
                $(this).append('<img src="./jsPlumb/resource/close2.png"  style="position: absolute;" />');
                if ($(this).text() == "开始" || $(this).text() == "结束") {
                    $("img").css("left", 158).css("top", 0);
                } else {
                    $("img").css("left", 158).css("top", -10);
                }
            });
            $("#right").on("mouseleave", ".node", function () {
                $("img").remove();
            });
$("#right").on("click", "img",function () {
                if (confirm("确定要删除吗?")) {
                    jsPlumb.removeAllEndpoints($(this).parent().attr("id"));
                    $(this).parent().remove();
                    
                }
            });
function doubleclick(id) {
            $(id).dblclick(function () {
                var text = $(this).text();
                $(this).html("");
                $(this).append("<input type='text' value='" + text + "' />");
                $(this).mouseleave(function () {
                    $(this).html($("input[type='text']").val());
                });
            });
        }

$("#left").children().draggable({
                 helper: "clone",
                 scope: "ss",
             });
            $("#right").droppable({
                scope: "ss",
                drop: function (event, ui) {
                    var left = parseInt(ui.offset.left - $(this).offset().left+235); //235
                    var top = parseInt(ui.offset.top - $(this).offset().top+9); //9
                    var name = ui.draggable[0].id;
                    var i = 0;
                    switch (name) {
                    case "node1":
                        i++;
                        var id = "state_start" + i;
                        $(this).append('<div class="node" style="border-radius: 25em"  id="' + id + '" >' + $(ui.helper).html() + '</div>');
                        $("#" +  id).css("left", left).css("top", top);
                        jsPlumb.addEndpoint(id, { anchors: "TopCenter" }, hollowCircle);
                        jsPlumb.addEndpoint(id, { anchors: "RightMiddle" }, hollowCircle);
                        jsPlumb.addEndpoint(id, { anchors: "BottomCenter" }, hollowCircle);
                        jsPlumb.addEndpoint(id, { anchors: "LeftMiddle" }, hollowCircle);
                        jsPlumb.draggable(id);
                        $("#" + id).draggable({ containment: "parent" });
                        doubleclick("#" + id);
                        break;
                    case "node2":
                        i++;
                        id = "state_flow" + i;
                        $(this).append("<div class='node' id='" + id + "'>" + $(ui.helper).html() + "</div>");
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
                        i++;
                        id = "state_decide" + i;
                        $(this).append("<div class='node' id='" + id + "'>" + $(ui.helper).html() + "</div>");
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
                        i++;
                        id = "state_end" + i;
                        $(this).append('<div class="node" style="border-radius: 25em"  id="' + id + '" >' + $(ui.helper).html() + '</div>');
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
 });


