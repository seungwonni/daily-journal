package baekjoon_study.study20220813;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * 문제
 * 다솜이는 사람의 마음을 읽을 수 있는 기계를 가지고 있다. 다솜이는 이 기계를 이용해서 2008년 4월 9일 국회의원 선거를 조작하려고 한다.
 * 다솜이의 기계는 각 사람들이 누구를 찍을 지 미리 읽을 수 있다. 어떤 사람이 누구를 찍을 지 정했으면, 반드시 선거때 그 사람을 찍는다.
 * 현재 형택구에 나온 국회의원 후보는 N명이다. 다솜이는 이 기계를 이용해서 그 마을의 주민 M명의 마음을 모두 읽었다.
 * 다솜이는 기호 1번이다. 다솜이는 사람들의 마음을 읽어서 자신을 찍지 않으려는 사람을 돈으로 매수해서 국회의원에 당선이 되게 하려고 한다.
 * 다른 모든 사람의 득표수 보다 많은 득표수를 가질 때, 그 사람이 국회의원에 당선된다.
 * 예를 들어서, 마음을 읽은 결과 기호 1번이 5표, 기호 2번이 7표, 기호 3번이 7표 라고 한다면,
 * 다솜이는 2번 후보를 찍으려고 하던 사람 1명과, 3번 후보를 찍으려고 하던 사람 1명을 돈으로 매수하면, 국회의원에 당선이 된다.
 * 돈으로 매수한 사람은 반드시 다솜이를 찍는다고 가정한다.
 * 다솜이가 매수해야하는 사람의 최솟값을 출력하는 프로그램을 작성하시오.
 * --------------------------------------------------------------------------------------
 * 입력
 * 첫째 줄에 후보의 수 N이 주어진다. 둘째 줄부터 차례대로 기호 1번을 찍으려고 하는 사람의 수, 기호 2번을 찍으려고 하는 수,
 * 이렇게 총 N개의 줄에 걸쳐 입력이 들어온다. N은 50보다 작거나 같은 자연수이고, 득표수는 100보다 작거나 같은 자연수이다.
 * --------------------------------------------------------------------------------------
 * 출력
 * 첫째 줄에 다솜이가 매수해야 하는 사람의 최솟값을 출력한다.
*
* */
public class voteCongressman {
    private static List<Integer> voteList = new ArrayList<>();
    private static Integer firstVote;
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        // 후보의 숫자를 지정하고 그 후보의 숫자에 따른 투표수를 지정
        setVotes(setCongressmanNum());
        // 다솜이가 매수해야 할 최소 값 계산 메소드 : calcMinVote
        System.out.println("다솜이가 매수해야 할 사람의 최솟값 : '" + calcMinNumOfBuying()+"' 명이다.");
    }
    //후보의 숫자를 지정하는 메소드
    private static Integer setCongressmanNum() {
        System.out.print("후보의 숫자를 지정하시오.\t");
        return scan.nextInt();
    }
    // 다솜이를 포함한 나머지 국회의원 투표수 지정 메소드
    private static void setVotes(Integer number) {
        for (int index = 0; index<number; index++) {
            if (index == 0) {
                System.out.print("다솜이의(1번째) 투표수를 지정해주세요. \t");
                firstVote = scan.nextInt();
            }
            else {
                System.out.print((index+1)+"번째 후보의 투표수롤 지정해주세요. \t");
                voteList.add(scan.nextInt());
            }
        }
    }
    // 최소 매수 계산 메소드
    private static Integer calcMinNumOfBuying() {
        Integer minVote = 0;
        Integer index;
        //계산이 필요한지 여부를 boolean형으로 확인
        boolean isCalc = true;
        // voteList 루프를 돌 때 다솜이보다 높은 투표수를 가진 인원이 있나 확인 필요
        while(isCalc) {
            // 루프가 다시 돌면 index와 isCalc 형을 초기화
            isCalc = false;
            index = 0;
            for (Integer voteNum : voteList) {
                // 다솜이보다 높은 투표수가 존재하면 계산필요
                // 'boolean'형을 'true'로 변환
                if (firstVote <= voteNum) {
                    voteList.set(index, voteNum-1);
                    firstVote+=1;
                    minVote+=1;
                    isCalc = true;
                }
                index++;
            }
        }
        return minVote;
    }
}
