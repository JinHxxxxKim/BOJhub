import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int p, m;

    static class Player implements Comparable<Player> {
        int lv;
        String name;

        public Player(int lv, String name){
            this.lv = lv;
            this.name = name;
        }

        @Override
        public int compareTo(Player player) {
            return this.name.compareTo(player.name);
        }
    }

    static class Room{
        int cnt;
        int baseLv;
        List<Player> players;

        public Room(int baseLv, Player player) {
            this.cnt = 1;
            this.baseLv = baseLv;
            this.players = new ArrayList<>();
            this.players.add(player);
        }

    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        p = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        List<Room> rooms = new ArrayList<>();
        for (int num = 0; num < p; ++num) {
            st = new StringTokenizer(br.readLine().trim());
            int lv = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            boolean canEnterRoom = false;
            for (Room room : rooms) {
                // 정원 & level
                if (room.cnt == m || room.baseLv > (lv + 10) || room.baseLv + 10 < lv) {
                    continue;
                }
                // 입장가능
                room.cnt++;
                room.players.add(new Player(lv, name));
                canEnterRoom = true;
                break;
            }

            if (!canEnterRoom) {
                rooms.add(new Room(lv, new Player(lv, name)));
            }
        }

        for (Room room : rooms) {
            Collections.sort(room.players);
        }

        for (Room room : rooms) {
            if (room.cnt == m) {
                sb.append("Started!").append("\n");
            } else {
                sb.append("Waiting!").append("\n");
            }
            for (Player player : room.players) {
                sb.append(player.lv).append(" ").append(player.name).append("\n");
            }
        }
        System.out.print(sb);
    }
}