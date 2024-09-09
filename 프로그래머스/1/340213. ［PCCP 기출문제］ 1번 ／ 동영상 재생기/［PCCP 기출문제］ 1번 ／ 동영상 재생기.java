import java.util.StringTokenizer;
class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int len, currPos, opStart, opEnd;
        StringTokenizer st;
        // 1. 동영상 길이 초로 변환
        st = new StringTokenizer(video_len, ":");
        len = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
        // 2. 현재 위치 초로 변환
        st = new StringTokenizer(pos, ":");
        currPos = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());

        // 3. 오프닝 시작시간 초로 변환
        st = new StringTokenizer(op_start, ":");
        opStart = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());

        // 4. 오프닝 종료시간 초로 변환
        st = new StringTokenizer(op_end, ":");
        opEnd = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
        
        if (currPos < opEnd && currPos >= opStart) {
            currPos = opEnd;
        }
        
        for (String command : commands) {
            // System.out.println("currPos = " + currPos);

            // 앞으로 가기
            if(command.equals("next")) {
                if (currPos + 10 > len) {
                    currPos = len;
                }else{
                    currPos += 10;
                }
            }
            // 뒤로 가기
            else if(command.equals("prev")) {
                if (currPos - 10 < 0) {
                    currPos = 0;
                } else {
                    currPos -= 10;
                }
            }
            // 오프닝 검증
            if (currPos < opEnd && currPos >= opStart) {
                currPos = opEnd;
            }
        }
        StringBuilder sb = new StringBuilder();
        int min = currPos / 60;
        if(min >=0 && min < 10) {
            sb.append("0").append(min);
        }else {
            sb.append(min);
        }
        sb.append(":");
        int sec = currPos % 60;
        if(sec >=0 && sec < 10) {
            sb.append("0").append(sec);
        }else {
            sb.append(sec);
        }
        return sb.toString();
    }
}