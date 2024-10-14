$(document).ready(function () {
    pageObject.init();
});

var pageObject = {
    init : function(){
        var me = this;
        me.bind();
    },
    bind : function () {
        $('.drawCard').bind('click', function (event) {
            const request = event.currentTarget.id;
            window.location.href = "/main/show-result?requestResult="+request;
        });
    }
}