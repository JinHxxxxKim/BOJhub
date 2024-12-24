import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 1. Stack 이 비어있을 경우 > push
 * 2. Stack 이 비어있지 않을 경우
 *   2-1. peek 이 나보다 클 경우 > push 후 다음
 *   2-2. peek 이 나보다 작을 경우 > pop & count ++
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[] buildingHeight;
    static int N;

    public static void main(String[] args) throws Exception{
        // Input
        N = Integer.parseInt(br.readLine().trim());
        buildingHeight = new int[N];
        for (int i = 0; i < N; i++) {
            buildingHeight[i] = Integer.parseInt(br.readLine().trim());
        }

        // Solution
        int[] sumArray = new int[N];
        Stack<Elem> stack = new Stack<Elem>();
        for (int i = N - 1; i >= 0; --i) {
            int tempIdx = i;
            int currBuildingHeight = buildingHeight[i];
            if (stack.isEmpty()) {
                stack.push(new Elem(i, 0, currBuildingHeight));
                sumArray[i] = 0;
            } else {
                while(!stack.isEmpty() && currBuildingHeight > stack.peek().height){
                    Elem elem = stack.pop();
                    sumArray[i]++;
                    sumArray[i] += sumArray[elem.idx];
                }
                stack.push(new Elem(i, sumArray[i], currBuildingHeight));
            }
        }
//        System.out.println("sumArray = " + Arrays.toString(sumArray));
        long ans = 0;
        for (int i : sumArray) {
            ans += i;
        }
        System.out.println(ans);
    }
    static class Elem{
        int idx, count, height;
        public Elem(int idx, int count, int height) {
            this.idx = idx;
            this.count = count;
            this.height = height;
        }
    }
}
