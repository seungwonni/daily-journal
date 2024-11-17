function createModal(options) {
    // 기본 옵션 설정
    const defaultOptions = {
        title: 'Modal Title',
        body: 'Modal body text goes here.',
        closeText: 'Close',
        saveText: 'Save changes',
        onClose: null, // 닫기 버튼 클릭 시 실행할 콜백 함수
        onSave: null   // 저장 버튼 클릭 시 실행할 콜백 함수
    };
    // 사용자 입력과 기본 옵션 병합
    const settings = $.extend({}, defaultOptions, options);

    // 모달의 최상위 div
    var $modal = $('<div>').addClass('modal').attr('tabindex', '-1');
    var $dialog = $('<div>').addClass('modal-dialog');
    var $content = $('<div>').addClass('modal-content');

    // 모달 헤더
    var $header = $('<div>').addClass('modal-header');
    var $title = $('<h5>').addClass('modal-title').text(settings.title);
    var $closeButton = $('<button>')
        .addClass('btn-close')
        .attr('type', 'button')
        .attr('data-bs-dismiss', 'modal')
        .attr('aria-label', 'Close');
    $header.append($title, $closeButton);

    // 모달 바디
    var $body = $('<div>').addClass('modal-body');
    var $bodyText = $('<p>').text(settings.body);
    $body.append($bodyText);

    // 모달 푸터
    var $footer = $('<div>').addClass('modal-footer');
    var $closeFooterButton = $('<button>')
        .addClass('btn btn-secondary')
        .attr('type', 'button')
        .attr('data-bs-dismiss', 'modal')
        .text(settings.closeText);
    var $saveFooterButton = $('<button>')
        .addClass('btn btn-primary')
        .attr('type', 'button')
        .text(settings.saveText);

    // 이벤트 추가
    $closeFooterButton.on('click', function () {
        if (typeof settings.onClose === 'function') {
            settings.onClose();
        }
        $modal.modal('hide');
    });

    $saveFooterButton.on('click', function () {
        if (typeof settings.onSave === 'function') {
            settings.onSave();
        }
        $modal.modal('hide');
    });

    $footer.append($closeFooterButton, $saveFooterButton);

    // 모달 조립
    $content.append($header, $body, $footer);
    $dialog.append($content);
    $modal.append($dialog);

    // Body에 모달 추가
    $('body').append($modal);

    // 모달 표시
    $modal.modal('show');

    // 모달 닫힘 시 DOM에서 제거
    $modal.on('hidden.bs.modal', function () {
        $modal.remove();
    });
}
