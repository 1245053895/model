$(function(){
    //数字跳动
    function numRun(obj,_num,_speed,_symbol,_random){
        //数字滚动
        var numRun1 = $(obj).numberAnimate({num:_num, speed:_speed, symbol:_symbol});
        var nums1 = _num;
        // setInterval(function(){
        //     nums1=parseInt(_random*Math.random());
        //     numRun1.resetData(nums1);
        // },5000);
    }
    numRun("#num1",26581000,800,",",40000);
    numRun("#num2",9581000,800,",",40000);
    numRun("#num3",6581000,800,",",40000);
    numRun("#num4",552300,800,",",40000);

    //tip
    $('.out-yc').mouseover(function(){
        var w = $(this).width();

        $(this).children('.tip-r').css({'left':(w-2),'display':'block'}).stop().animate({'width':'111px'},500);
        $(this).children('.tip-l').css({'right':(w-2),'display':'block'}).stop().animate({'width':'111px'},500);
    }).mouseout(function(){
        $(this).children('.tip').stop().animate({'width':0},500,function(){
            $(this).css({'display':'none'});
        });
    });

    //数据传输运动
    var css1 = {'top':'124px','left':'258px'};
    var css2 = {'top':'14px','left':'-86px'};

    setInterval(function(){
        $('.date-arrow').animate(css1,1500,rowBack);
    },300);

    function rowBack(){
        var cLeft = $('.date-arrow').css('left');

        if(cLeft == '258px'){
           $('.date-arrow').css(css2);
        }

    };

    //layer
    $('.out-yc').click(function(){
        console.log(1)
        layer.open({
            type: 1,
            title: 'HBASE-传输内容',
            area:'506px',
            skin: 'demo-class',
            content: $('.pop'),
            // btn:['确定','取消'],
            cancel: function(){

            }
        });
    });
});