package baekjoon_study.study20220528;

import javax.security.sasl.SaslClient;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
문제
상근이는 매일 아침 알람을 듣고 일어난다. 알람을 듣고 바로 일어나면 다행이겠지만,
항상 조금만 더 자려는 마음 때문에 매일 학교를 지각하고 있다.
상근이는 모든 방법을 동원해보았지만, 조금만 더 자려는 마음은 그 어떤 것도 없앨 수가 없었다.
이런 상근이를 불쌍하게 보던, 창영이는 자신이 사용하는 방법을 추천해 주었다.
바로 "45분 일찍 알람 설정하기"이다.
이 방법은 단순하다. 원래 설정되어 있는 알람을 45분 앞서는 시간으로 바꾸는 것이다. 어차피 알람 소리를 들으면,
알람을 끄고 조금 더 잘 것이기 때문이다. 이 방법을 사용하면, 매일 아침 더 잤다는 기분을 느낄 수 있고, 학교도 지각하지 않게 된다.
현재 상근이가 설정한 알람 시각이 주어졌을 때, 창영이의 방법을 사용한다면, 이를 언제로 고쳐야 하는지 구하는 프로그램을 작성하시오.
*/

public class AlarmClock_2884 {
    static String guide = "입력 시간은 24시간 표현을 사용한다. 24시간 표현에서 하루의 시작은 \n" +
            "0:0(자정)이고, 끝은 23:59(다음날 자정 1분 전)이다. 시간을 나타낼 때, 불필요한 0은 사용하지 않는다.\n" +
            "ex) 10:10";
    static String fail = "fail";
    static String success = "success";
    static String inputData;
    static Scanner scan = new Scanner(System.in);
    //요구사항에 부합한 시간대 => 45분 일찍 일어나야 하는 요구사항에 45로 기입, 요구하는 시간은 항상 바뀔 수 있으니 변수로 따로 지정,
    static Integer requestedTime = 45;
    public static void main(String[] args) {
        //실행 함수 호출
        excute();
    }
    public static void excute() {
        //입력 가이드 제시
        System.out.println(guide);
        //알람시간을 입력 함수 호출
        input();
        //입력한 시간 유효성검사 실행
        Map<String, String> result = validate();
        //Map 형태의 데이터를 이용하여 failData 추출
        String failData = result.get(fail);
        //failData가 존재하면 Error 반환, 존재하지 않으면 계산 함수 실행
        if (failData != null)
            System.out.println("ERROR : "+failData);
        else
            calculate();
    }
    public static void input() {
        System.out.print("알람을 설정해주세요. >> ");
        inputData = scan.nextLine();
    }
    private static Map<String, String> validate() {
        Map<String, String> result = new HashMap<>();
        String regex = "^[0-9]*$";
        String split[] = inputData.split(":");
        if (!inputData.contains(":"))
            result.put(fail, "입력하신 문자열에 ':' 존재하지 않습니다.");
         else if (inputData.length() > 5)
            result.put(fail, "입력하신 문자열의 길이가 맞지 않습니다.");
         else if (!inputData.replace(":","").matches(regex))
            result.put(fail, "숫자만 입력 가능합니다.");
         else if (Integer.parseInt(split[0]) < 0 ||
                Integer.parseInt(split[0]) > 24)
            result.put(fail, "입력하신 시간 형식이 맞지않습니다.(0~24까지 입력 가능)");
         else if (Integer.parseInt(split[1]) < 0 ||
                Integer.parseInt(split[1]) > 60)
            result.put(fail, "입력하신 시간 형식이 맞지않습니다.(0~24까지 입력 가능)");
         else
            result.put(success, "알람이 성공적으로 설정되었습니다.");
         return result;
    }
    private static void calculate() {
        //':' 문자 기준으로 문자를 자름 :
        // ':' 기준 좌측은 시, 우측은 분
        String splitedTime[] = inputData.split(":");
        Integer hour = Integer.parseInt(splitedTime[0]);
        Integer min = Integer.parseInt(splitedTime[1]);
        // 요구하는 시간대는 항상 바뀔 수 있으니 변수로 선언해서 관리
        if (min - requestedTime < 0) {
            hour -= 1;
            hour = hour < 0 ? 23 : hour;
            min = 60 - (requestedTime - min);
        } else {
            min -= requestedTime;
        }
        System.out.println("입력하신 알람의 '"+requestedTime+"'분 전으로 맞춰졌습니다. " + hour + "시" + min+"분");
    }
}
