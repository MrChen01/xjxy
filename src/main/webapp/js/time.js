/*时钟*/
//js完成，创建一个定时器（可重复的）
var mytime = setInterval(function() {
    getTime();
}, 1000);//1000毫秒==1秒
function getTime() {
    var d = new Date();
    /*得到月，日，时，分，秒，要处理少10补0  */
    var M = (d.getMonth() + 1) < 10 ? ('0' + (d.getMonth() + 1)) : (d
        .getMonth() + 1);
    var D = d.getDate() < 10 ? ('0' + d.getDate()) : d.getDate();
    var H = d.getHours() < 10 ? ('0' + d.getHours()) : d.getHours();
    var m = d.getMinutes() < 10 ? ('0' + d.getMinutes()) : d.getMinutes();
    var s = d.getSeconds() < 10 ? ('0' + d.getSeconds()) : d.getSeconds();
    var t = d.getFullYear() + "年" + M + "月" + D
        + "号&nbsp;&nbsp;&nbsp;&nbsp;" + H + ":" + m + ":" + s
        + "&nbsp;&nbsp;&nbsp;&nbsp;星期" + "日一二三四五六".charAt(d.getDay());
    ;
    //将控件的内容修改为处理好的字符串
    $("#ptime").html(t);
}