$(document).ready(function () {
    pageObject.init();
});

var pageObject = {
    joinURL : "/join",
    init : function(){
        var me = this;
        me.bind();
    },
    bind : function () {
        $('#join').bind('click', function (event) {
            if (!pageObject.validPassword()) {
                alert('비밀번호가 옳바르지 않습니다.');
                return false;
            }
            if (confirm('회원가입 하시겠습니까?') === false) {
                return false;
            }
            pageObject.doProcess();
        });

    },
    validPassword : function () {
        return $("#password").val() === $("#confirmPassword").val();
    },

    doProcess : function () {
        var obj = {
            "email" : $("#email").val(),
            "password" : $("#password").val(),
            "nickname" : $("#nickname").val()
        }
        $.ajax({
            type: "POST",
            url: pageObject.joinURL,
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(obj),
            success: function (data, status, xhr) {

            },
            error: function (xhr, status, error) {
                $("#result").text(error);
            },
        });
    }

}