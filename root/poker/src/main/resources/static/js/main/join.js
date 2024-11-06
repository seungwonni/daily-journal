$(document).ready(function () {
    pageObject.init();
});

var pageObject = {
    joinURL : "/common/join",
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
                if (data.result == 1) {
                    alert("회원가입이 완료되었습니다.");
                    window.location.href = "/index";
                    return false;
                }
                alert("아이디가 중복되었습니다. 다시 확인 바랍니다.");
                return false;
            },
            error: function (xhr, status, error) {
                $("#result").text(error);
            },
        });
    }

}