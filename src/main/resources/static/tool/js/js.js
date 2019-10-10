//列表页面上点击删除按钮弹出删除框
$(function () {
    $('.delete').click(function () {
        //灰背景遮挡效果
        $('.zhezhao').css('display', 'block');
        $('#removeProv').fadeIn();
        //获取点击删除的那个按键的del_uri，然后赋值给提交删除表单的那个action
        $("#deleteForm").attr("action", $(this).attr("del_uri"));
    });
    //点击 确定
    $('#yes').click(function () {
        $("#deleteForm").submit();
        $('.zhezhao').css('display', 'none');
        $('#removeProv').fadeOut();
    });
    //点击 取消
    $('#no').click(function () {
        $('.zhezhao').css('display', 'none');
        $('#removeProv').fadeOut();
    });
});
