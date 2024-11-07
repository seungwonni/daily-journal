$(document).ready(function () {
    pageObject.init();
});

var pageObject = {
    commonLoginURL : "common/login",
    homeURL : "/home",
    mainURL : "/main/show-result",
    init : function(){
        var me = this;
        me.bind();
        me.setInit();
    },
    bind : function () {
        $('.drawCard').bind('click', function (event) {
            const request = event.currentTarget.id;
            window.location.href = pageObject.mainURL + "?requestResult=" + request;
        });
    },
    setInit : function () {
    },
}