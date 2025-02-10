import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int H, W;
    static boolean[][] map;
    static boolean[][] alreadyCheck;
    static int ans;

    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine().trim());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new boolean[W][H];
        alreadyCheck = new boolean[W][H];
        ans = 0;
        st = new StringTokenizer(br.readLine().trim());
        for (int row = 0; row < W; ++row) {
            int currWallHeight = Integer.parseInt(st.nextToken());
            for (int col = 0; col < currWallHeight; ++col) {
                map[row][col] = true;
            }
        }

        // SOLUTION
        for (int col = 0; col < H; ++col) {
            for (int row = 0; row < W; ++row) {
                if(!map[row][col] && !alreadyCheck[row][col]){
                    boolean hasWall = chkWall(row, col);
//                    System.out.println("row = " + row);
//                    System.out.println("col = " + col);
//                    System.out.println("hasWall = " + hasWall);
                    if (hasWall) {
//                        System.out.println(calArea(row, col));
                        ans += calArea(row, col);
                    }
                }
            }
        }

        System.out.println(ans);
    }

    private static int calArea(int row, int col) {
        int temp = 0;
        for (int chkRow = row; chkRow >= 0; --chkRow) {
            if(map[chkRow][col]) {
                break;
            }
            ++temp;
        }

        for (int chkRow = row; chkRow < W; ++chkRow) {
            if(map[chkRow][col]) {
                break;
            }
            ++temp;
        }
        return temp - 1;
    }

    private static boolean chkWall(int row, int col) {
        boolean hasRightWall = false;
        boolean hasLeftWall = false;
        for (int chkRow = row; chkRow >= 0; --chkRow) {
            if(map[chkRow][col]) {
                hasLeftWall = true;
                break;
            }
            alreadyCheck[chkRow][col] = true;
        }

        for (int chkRow = row; chkRow < W; ++chkRow) {
            if(map[chkRow][col]) {
                hasRightWall = true;
                break;
            }
            alreadyCheck[chkRow][col] = true;
        }

        return hasRightWall && hasLeftWall;
    }
}
