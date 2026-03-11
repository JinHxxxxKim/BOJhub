import java.util.*;

class Solution {
    
    public int calTime(String start, String end) {
        int endMinute = Integer.parseInt(end.split(":")[0]) * 60 + Integer.parseInt(end.split(":")[1]);
        int startMinute = Integer.parseInt(start.split(":")[0]) * 60 + Integer.parseInt(start.split(":")[1]);
        return endMinute - startMinute;
    }
    
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        boolean flag = false;
        
        List<String> targetList = new ArrayList<>();
        char[] targetCharArray = m.toCharArray();
        for(int idx = 0; idx < targetCharArray.length; ++idx) {
            if(idx + 1 < targetCharArray.length && targetCharArray[idx + 1] == '#') {
                targetList.add(new String(targetCharArray, idx, 2));
                ++idx;
            }else{
                targetList.add(new String(targetCharArray[idx] + ""));
            }
        }
        // System.out.println(targetList);
        
        PriorityQueue<Music> musicPq = new PriorityQueue<>();
        
        for(String music : musicinfos) {
            // System.out.println("===========================");
            String[] infos = music.split(",");
            int time = calTime(infos[0], infos[1]);
            // System.out.println(Arrays.toString(infos));
            // System.out.println(calTime(infos[0], infos[1]));
            
            
            List<String> musicList = new ArrayList<>();
            char[] charArray = infos[3].toCharArray();
            for(int idx = 0; idx < charArray.length; ++idx) {
                if(idx + 1 < charArray.length && charArray[idx + 1] == '#') {
                    musicList.add(new String(charArray, idx, 2));
                    ++idx;
                }else{
                    musicList.add(new String(charArray[idx] + ""));
                }
            }
            
            // System.out.println(musicList);
            
            boolean isCorrect = false;
            for(int musicIdx = 0; musicIdx < musicList.size(); ++musicIdx){
                // System.out.println("-----------------------");
                // System.out.println("musicIdx::" + musicIdx);
                int tempIdx = musicIdx;
                int tempTime = tempIdx;
                boolean localCorrect = true;
                for(int startIdx = 0; startIdx < targetList.size(); ++startIdx) {
                    // System.out.println("startIdx::" + startIdx);
                    if(!targetList.get(startIdx).equals(musicList.get(tempIdx))) {
                        localCorrect = false;
                        break;
                    }
                    
                    ++tempTime;
                    if(tempTime > time){
                        localCorrect = false;
                        break;
                    }
                    tempIdx = (tempIdx + 1) % musicList.size();
                }    
                if(localCorrect) isCorrect = true;
            }
            
            if(isCorrect) {
                musicPq.offer(new Music(infos[0], infos[1], infos[2]));
            }
        }
        while(!musicPq.isEmpty()){
            return musicPq.poll().name;    
        }
        
        
        return "(None)";
    }
    
    class Music implements Comparable<Music>{
        int start, end;
        String name;
        
        public Music(String s, String e, String n) {
            this.start = Integer.parseInt(s.split(":")[0]) * 60 + Integer.parseInt(s.split(":")[1]);
            this.end = Integer.parseInt(e.split(":")[0]) * 60 + Integer.parseInt(e.split(":")[1]);
            this.name = n;
        }
        
        @Override
        public int compareTo(Music m) {
            if((this.end - this.start) == (m.end - m.start)) {
                return this.start - m.start;
            }else{
                return (m.end - m.start) - (this.end - this.start);
            }
        }
    }
}