class Solution {
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<food.length;++i){
            for(int j=0;j<food[i]/2;++j){
                sb.append(i);
            }
        }
        String tempAnswer = sb.toString();
        sb.append(0);
        for(int i=tempAnswer.length()-1;i>=0;--i){
            sb.append(tempAnswer.charAt(i));
        }
        return sb.toString();
    }
}