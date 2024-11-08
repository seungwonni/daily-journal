$(document).ready(function () {
    pageObject.init();
});

var pageObject = {
    init: function() {
        var me = this;
        me.bind();
        mainObj.getRanking(null);
    },
    bind: function () {
        $('.nav-link').bind('click', function (e) {
            mainObj.clickTab(e);
            mainObj.getRanking($(e.currentTarget).attr('id'));
        });
    },
};

var mainObj = {
    getRankingURL : "/ranking/list",
    clickTab : function (e) {
        $('.nav-link').removeClass('active');
        $(e.currentTarget).addClass('active');
    },
    getRanking : function (handRanking) {
        mainObj.clearTable();
        if (handRanking == null) {
            handRanking = "STRAIGHT_FLUSH";
        }
        $.ajax({
            type: "GET",
            contentType:"application/json",
            url: mainObj.getRankingURL + "?handRanking=" + handRanking,
            success: function (result, status, xhr) {
                const tableBody = $("#rankingTable");
                $.each(result.data, function(index, data) {
                    var percent = data.percent.toFixed(2) + "%";
                    var row = "<tr>";
                    row += `<td>${data.ranking}</td>`;
                    row += `<td>${data.nickname}</td>`;
                    row += `<td>${data.tryCount}</td>`;
                    row += `<td>${percent}</td>`;
                    row += "</tr>";
                    tableBody.append(row);
                });
            },
            error: function (xhr, status, error) {
                $("#result").text(error);
            },
        });
    },
    clearTable : function () {
        $("#rankingTable").empty();
    }
}