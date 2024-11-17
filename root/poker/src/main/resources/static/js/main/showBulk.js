var count = 0;

$(document).ready(function () {
    pageObject.init();
});

var pageObject = {
    init: function() {
        mainObj.getBulkCard();
    },
};

var mainObj = {
    canStop : false,
    saveResultURL : "/ranking/save",
    getURL : "/main/retry-bulk",
    isRendering : false,
    successProcess : function () {
        count = Number($('#totalTry').text());
        $('#totalTry').text(count);
        if (confirm("축하합니다!" + (count) + "회만에 성공했습니다~\n" +
            "확률 : " + ((1 / count) * 100).toFixed(4) + "%\n" +
            "(" + mainObj.convertResult() +"평균 확률 : " + mainObj.convertRate() + ")"  + "\n"
            + "랭킹에 등록하시겠습니까?"
        ) === false) {
            return false;
        }
        var obj = {
            "handRanking": $('#requestResult').val(),
            "tryCount" : count,
            "percent" : ((1 / count) * 100).toFixed(4)
        };
        $.ajax({
                type: "POST",
                url: mainObj.saveResultURL,
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(obj),
                success: function (data, status, xhr) {

                },
                error: function (xhr, status, error) {
                    $("#result").text(error);
                },
            });
    },
    convertResult : function () {
        var result = $('#requestResult').val();
        switch (result) {
            case "ROYAL_STRAIGHT_FLUSH" :
                return "로얄 스트레이트 플러쉬!!!";
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
            case "ROYAL_STRAIGHT_FLUSH" :
                return "0.000154%";
            case "STRAIGHT_FLUSH" :
                return "0.029%";
            case "FULL_HOUSE" :
                return "2.63%";
            case "FOUR_OF_A_KIND" :
                return "0.16%";
        }
    },
    getBulkCard : function () {
        var obj = {"requestResult": $('#requestResult').val()};
        $.ajax({
            type: "POST",
            url: mainObj.getURL,
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(obj),
            success: function (data, status, xhr) {
                setTimeout(() => {
                    $('.mask').fadeOut();
                    //$('.mask').attr("hidden", true);
                }, 1500); // 비동기 작업을 0.1초 지연시키며 실행
                mainObj.processResult(data);
                setTimeout(() => {
                    mainObj.successProcess();
                }, 500); // 비동기 작업을 0.1초 지연시키며 실행

            },
            error: function (xhr, status, error) {
                $("#result").text(error);
            }
        }); // end ajax
    },
    processResult : function (data){
        var cardInfo = data.cardInfo;
        mainObj.clearSection();
        mainObj.drawSection(cardInfo);
        $('#totalTry').text(data.tryCount);
    },
    isSuccess: function (result) {
        return $('#requestResult').val() === result;
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
        data.playerCard.forEach(card => {
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