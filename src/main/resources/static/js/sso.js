/**
 * 接收js参数
 * @param name 参数名称
 * @returns String 参数内容
 */
function getUrlParam(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return trim(r[2]);
    }
    return "";
}

/**
 * 删除左右两端的空格
 * @param str 输入值
 * @returns String 输出值
 */
function trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

/**
 * 删除左右两端的空格
 * @param str 输入值
 * @returns String 输出值
 */
function isEmpty(str) {
    return str == null || trim(str).length === 0;
}

/**
 * 验证登录返回的结果
 * @param result 输入值
 * @returns 登录返回的结果 true 是 false 否
 */
function checkLoginResult(result) {
    return result === "receive_id_success";
}

/**
 * 验证session是否存在返回的结果
 * @param result 输入值
 * @returns session是否存在返回的结果 true 是 false 否
 */
function checkSessionResult(result) {
    return result === "check_session_success";
}

/**
 * 获取登录地址
 * @param formData 输入值
 * @returns 登录地址
 */
function getLoginUrl(formData) {
    return formData.ssoSupServerUrl + "/sso/login.html?ssoClientUrl=" + formData.ssoClientUrl
}

/**
 * 获取注销地址
 * @param formData 输入值
 * @returns 注销地址
 */
function getLogoutUrl(formData) {
    return formData.ssoSupServerUrl + "/sso/logout.html?idNumber=" + formData.idNumber + "&sessionId=" + formData.sessionId + "&ssoClientUrl=" + formData.ssoClientUrl;
}

/**
 * JS写入cookies方法
 * @param name cookie名称
 * @param value 内容
 */
function setCookie(name, value) {
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}


function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null)
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}