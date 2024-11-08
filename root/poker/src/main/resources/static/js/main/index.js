$(document).ready(function () {
    pageObject.init();
});

var pageObject = {
    commonLoginURL : "/common/login",
    homeURL : "/home",
    init : function(){
        var me = this;

        me.bind();
    },
    bind : function () {
        $('#commonLoginBtn').bind('click', function () {
            pageObject.processCommonLogin();
        });
        $('#kakaoLoginBtn').bind('click', function () {
            pageObject.processKakaoLogin();
        });
    },
    processCommonLogin : function () {
        var obj = {
            "email" : $("#email").val(),
            "password" : $("#password").val()
        }
        $.ajax({
            type: "POST",
            url: pageObject.commonLoginURL,
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(obj),
            success: function (data, status, xhr) {
                if (data.result == 1) {
                    alert("로그인이 성공되었습니다.");
                    window.location.href = pageObject.homeURL +
                        "?type=common" +
                        "&nickname=" + data.data.nickname;
                    return false;
                }
                alert("로그인에 실패하였습니다.\n"+
                    "아이디 또는 비밀번호를 확인해주세요.");
                return false;
            },
            error: function (xhr, status, error) {
                $("#result").text(error);
            },
        });
    },
    processKakaoLogin : function () {
        var uri = "https://kauth.kakao.com/oauth/authorize?" +
            "client_id=" + "6317706b858c66cfec49fdb2e64b7f8d" +
            "&redirect_uri=" + "http://localhost:8080/kakao-login" +
            "&response_type=" + "code"
            "&scope=" + "profile_nickname";
        window.location.href = uri;
    },
}