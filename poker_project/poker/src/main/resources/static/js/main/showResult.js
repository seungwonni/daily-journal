var count = 0;
var time = 100; // 간격 시간

$(document).ready(function () {
    pageObject.init();
});

var pageObject = {
    init: function() {
        var me = this;
        mainObj.doMain();
    },
};

var mainObj = {
    // 변수를 추가하여 AJAX 호출 중인지 여부를 추적합니다.
    isAjaxInProgress: false,

    doMain: function () {
        if (mainObj.isSuccess()) {
            count = Number($('#totalTry').text());
            $('#totalTry').text(count);
            alert("축하합니다!" + (count) + "회만에 성공했습니다~\n" +
                "확률 : " + ((1 / count) * 100).toFixed(4) + "%\n" +
            "(" + mainObj.convertResult() +"평균 확률 : " + mainObj.convertRate() + ")" );
            return false; // 성공했으니 더 이상 호출하지 않음
        }

        // AJAX 호출이 진행 중이 아니면 시작합니다.
        if (!this.isAjaxInProgress) {
            this.isAjaxInProgress = true; // AJAX 호출 진행 중으로 설정
            this.processRetry();
        }
    },
    convertResult : function () {
        var result = $('#requestResult').val();
        switch (result) {
            case "STRAIGHT_FLUSH" :
                return "스트레이트 플러쉬";
            case "FULL_HOUSE" :
                return "풀 하우스";
            case "FOUR_OF_A_KIND" :
                return "포카드";
        }
    },
    convertRate : function () {
        var result = $('#requestResult').val();
        switch (result) {
            case "STRAIGHT_FLUSH" :
                return "0.029%";
            case "FULL_HOUSE" :
                return "2.63%";
            case "FOUR_OF_A_KIND" :
                return "0.16%";
        }
    },

    processRetry: function () {
        console.log("processRetry");
        var obj = {"requestResult": $('#requestResult').val()};
        $.ajax({
            type: "POST",
            url: "/main/retry",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(obj),
            success: function (data, status, xhr) {
                mainObj.clearSection();
                mainObj.drawSection(data);
                // 시도 횟수 업데이트
                count++; // count 증가
                $('#totalTry').text(count); // UI에 업데이트
            },
            error: function (xhr, status, error) {
                $("#result").text(error);
            },
            complete: function () {
                mainObj.isAjaxInProgress = false; // AJAX 호출이 끝났으므로 상태를 초기화
                setTimeout(function () {
                    mainObj.doMain(); // 일정 시간이 지나면 doMain() 호출
                }, time);
            }
        }); // end ajax
    },

    isSuccess: function () {
        return $('#requestResult').val() === $('#result').val();
    },

    clearSection: function () {
        $('#showResultSection').remove();
    },

    drawSection: function (data) {
        const section = $('<section>').addClass('page-section').attr('id', 'showResultSection');
        $('#mainContainer').append(section);
        var customCon = $('<div>').addClass('customCon');
        section.append(customCon);
        data.board.forEach(card => {
            var div = $('<div>');
            var img = $('<img>').addClass('boardCardImg').attr('src', '/images/card/' + card + '.png');
            customCon.append(div);
            div.append(img);
        });

        var customCon = $('<div>').addClass('customCon');
        section.append(customCon);
        data.myCard.forEach(card => {
            var customBox2 = $('<div>').addClass('customBox2');
            var firstCard = $('<img>').addClass('myCardImg').attr('src', '/images/card/' + card.firstCard + '.png');
            var secondCard = $('<img>').addClass('myCardImg').attr('src', '/images/card/' + card.secondCard + '.png');
            var result = $('<input>').attr('type', 'hidden').attr('id', 'result').val(card.result);
            var text = $('<p>').addClass('bigFontP').text(card.result);
            customCon.append(customBox2);
            customBox2.append(firstCard);
            customBox2.append(secondCard);
            customBox2.append(result);
            customBox2.append(text);
        });

        var customBox3 = $('<div>').addClass('customBox3');
        var span = $('<span>').attr('id', 'totalTry').text(count);
        var text = $('<p>').addClass('bigFontP').text('시도 횟수 : ').append(span).append("회");
        customCon.append(customBox3);
        customBox3.append(text);
    }
}