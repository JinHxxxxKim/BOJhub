import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [최솟값] - BOJ segment tree
 * <p>
 * <p>
 * 75 30 100 38 50 51 52 20 81 5
 * <p>
 * Segment Tree (By Array) 5                                  [75, 30, 100, 38, 50, 51, 52, 20, 81, "5"] (1 ~ 10) |
 *    \ 30         5                       LEFT: [75, "30", 100, 38, 50] (1 ~ 5) / RIGHT: [51, 52, 20, 81, "5"] (6 ~ 10)
 * |    \     |   \ 30   38    20   5                  LEFT: [75, "30", 100] (1 ~ 3) / RIGHT: ["38", 50] / lEFT: [51,
 * 52, "20"] (6 ~ 8) / RIGHT: [81, "5"] (9 ~ 10) | \  | \   | \  | \ 30 100 38 50 51 20 81 5            [75,
 * "30"][100]["38"][50][51, 52]["20"][81]["5"] [75]["30"][100]["38"][50][51][52]["20"][81]["5"]
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] srcArr;
    static int[] segmentTree;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        srcArr = new int[N + 1];
        // 배열 입력
        for (int idx = 1; idx < N + 1; idx++) {
            srcArr[idx] = Integer.parseInt(br.readLine().trim());
        }

        // segment tree 초기화
        createSegmentTree();
//        System.out.println("Arrays.toString(segmentTree) = " + Arrays.toString(segmentTree));

        // M개의 구간에 대해 최솟값 구하기
        for (int cnt = 0; cnt < M; cnt++) {
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int minValue = getMin(start, end, 1, N, 1);
            sb.append(minValue).append("\n");
        }
        System.out.println(sb);
    }

    // start ~ end 구간에 대해 최솟값을 구하는 메소드
    // left, right는 현재 탐색중인 segment tree node에 대한 left, right 값
    private static int getMin(int start, int end, int left, int right, int node) {
//        System.out.println("================");
//        System.out.println("탐색 범위: " + start + " ~ " + end);
//        System.out.println("현재 노드 범위: " + left + " ~ " + right);
//        System.out.println("node = " + node);

        int mid = (left + right) / 2;
        // CASE 1. 현재 node 의 범위를 완전히 벗어난 경우
        if (start > right || end < left) {
            return Integer.MAX_VALUE;
        }
        // CASE 2. 현재 node 의 범위가 포함하는 경우
        if (start <= left && end >= right) {
            return segmentTree[node];
        } else {
            int leftValue = getMin(start, end, left, mid, node * 2);
            int rightValue = getMin(start, end, mid + 1, right, node * 2 + 1);
            return Math.min(leftValue, rightValue);
        }
    }

    // segment tree 생성 및 초기화 하는 메소드
    private static void createSegmentTree() {
        // 1. segment tree 의 필요 높이를 구한다. (LOG 밑 변환 공식 사용)
        int treeHeight = (int) Math.ceil(Math.log(N) / Math.log(2));

        // 2. segment tree 의 크기를 트리의 높이로 부터 구한다.
        int treeSize = (int) (Math.pow(2, treeHeight + 1) - 1);

//        System.out.println("treeHeight = " + treeHeight);
//        System.out.println("treeSize = " + treeSize);

        segmentTree = new int[treeSize + 1]; // (0번 인덱스는 사용하지 않음)

        // 3. segment tree 값 채우기
        initSegmentTree(1, 1, N);
    }

    // srcArr 의 left ~ right 까지 범위에 대해 연산 결과를 segmentTree(node)에 저장
    private static void initSegmentTree(int node, int left, int right) {
        int mid = (left + right) / 2;
        // leaf node 인 경우, 바로 저장
        if (left == right) {
            segmentTree[node] = srcArr[left];
        } else { // leaf node 가 아니라면, divide
            // 1. 일단 분할한 뒤
            initSegmentTree(node * 2, left, mid); // 왼쪽 자식 노드
            initSegmentTree(node * 2 + 1, mid + 1, right); // 오른쪽 자식 노드

            // 2. 이후, 자식 노드들의 값을 바탕으로 현재 노드의 값 저장
            segmentTree[node] = Math.min(segmentTree[node * 2], segmentTree[node * 2 + 1]);
        }
    }
}
