$(document).ready(function () {
    pageObject.init();
});

var pageObject = {
    commonLoginURL : "common/login",
    homeURL : "/home",
    mainURL : "/main/show-result",
    bulkURL : "/main/show-bulk",
    init : function(){
        var me = this;
        me.bind();
        me.setInit();
    },
    bind : function () {
        $('.drawCard').bind('click', function (event) {
            const request = event.currentTarget.name;
            window.location.href = pageObject.mainURL + "?requestResult=" + request;
        });
        $('.bulkDrawCard').bind('click', function (event) {
            const request = event.currentTarget.name;
            window.location.href = pageObject.bulkURL + "?requestResult=" + request;
        });

    },
    setInit : function () {
        if (window.location.href.indexOf('kakao-login') > 1) {
            window.location.href = pageObject.homeURL;
        }
    },
}