package baekjoon_study.study20220719;

import java.util.ArrayList;
import java.util.List;

public class CompareLoopFunction {
    //카운트 될 숫자
    static final Integer count = 28888888;
    //유저 정보 리스트 객체 사이즈 => 3000만개로 셋팅
    static final Integer listSize = 30000000;
    //리스트 객체 생성자 생성
    static List<UserInfo> list = new ArrayList<>();
    public static void main(String[] args) {
        Long currentMs;
        // list 객체에 임의로 3000만개 데이터 누적
        for (int i = 0; i < listSize; i++) {
            list.add(new UserInfo("json"+i, "010-xxxx-xxxx",24));
        }
        System.out.println("for문 시작");
        currentMs = System.currentTimeMillis();
        System.out.println("for문 종료 : " + (calcForLoop() - currentMs) + "ms");
        System.out.println("향상된 for문 시작");
        currentMs = System.currentTimeMillis();
        System.out.println("향상된 for문 종료 : " + (calcEnhancedForLoop() - currentMs) + "ms");
        System.out.println("stream Filter 함수 시작");
        currentMs = System.currentTimeMillis();
        System.out.println("stream Filter 함수 종료 : " + (calcSteamFilter() - currentMs) + "ms");
    }
    //for문 계산식
    private static Long calcForLoop() {
        Integer listSize = list.size();
        for (int i = 0; i< listSize; i++) {
            if (list.get(i).getName().equals("json"+count)) {
                break;
            }
        }
        return System.currentTimeMillis();
    }
    //향상된 for문 계산식
    private static Long calcEnhancedForLoop() {
        for (UserInfo userInfo: list) {
            if (userInfo.getName().equals("json"+count)) {
                break;
            }
        }
        return System.currentTimeMillis();
    }
    //Stream의 Filter 메소드 계산식
    private static Long calcSteamFilter() {
        list.stream()
                .filter(userInfo -> userInfo.getName().equals("json"+count))
                .findAny().get().getName()
        ;
        return System.currentTimeMillis();
    }
}
