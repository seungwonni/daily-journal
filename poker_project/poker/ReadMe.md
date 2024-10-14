# 🤡포커(개인 사이드 프로젝트)

## 📝릴리즈노트 작성 예시
### ☠ 패키징에 포함된 모듈 : (dto, service, view, controller)
- [2022-08-13] 최초 커밋
    - dto
        - 카드 정보 및 플레이어 정보 구현
    - view
        - 콘솔창에 결과값 출력 가능하도록 구현
    - service
        - SettingCardService
            - 카드 셔플 및 플레이어 갯수에 맞춰 카드를 나눠줌
            - 보드에 카드 셋팅
            - 결과값을 view 화면에 송출할 수 있게 객체 전달
    - controller
        - 실행 메소드 구현
- [2022-08-29] 로티플 계산 로직 및 스티플 계산 로직 추가
    - dto
        - 족보 관련 데이터 구현
    - service
        - CalculateResultService
            - 플러쉬 가능성 여부 체크 함수 구현
            - 로티플 및 스티플 계산 로직 구현
- [2024.10.10] Vue로 작업했던 내용들을 thymeleaf로 전환
    - 분리된 모듈을 하나로 마이그레이션 작업
    - 간단하게 view 작업 진행
- [2024.10.14] UI 개선 및 기능 추가
    - BootStrap UI로 간단하게 화면 구현
    - 확률 안내 modal 구현
    - 목표한 상위 족보 떴을 시 횟수 카운트 및 확률 퍼센트 구현