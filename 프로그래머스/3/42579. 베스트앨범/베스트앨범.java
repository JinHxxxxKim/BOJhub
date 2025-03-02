import java.util.*;

class Solution {
    
    static class Music implements Comparable<Music>{
        int number;
        int playCnt;
        
        public Music(int number, int playCnt){
            this.number = number;
            this.playCnt = playCnt;
        }
        
        @Override
        public int compareTo(Music music){
            if(this.playCnt == music.playCnt){
                return this.number - music.number;
            }
            return -1 * (this.playCnt - music.playCnt);
        }
        
        @Override
        public String toString(){
            return "number: " + this.number + "/ playCnt: " + this.playCnt;
        }
    }
    
    static class Genre implements Comparable<Genre>{
        String genre;
        int playCnt;
        
        public Genre(String genre, int playCnt){
            this.genre = genre;
            this.playCnt = playCnt;
        }
        
        @Override
        public int compareTo(Genre genre){
            return -1 * (this.playCnt - genre.playCnt);
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new LinkedList<>();
        
        Map<String, List> map = new HashMap<>();
        Map<String, Integer> playCntMap = new HashMap<>();
        
        for(int idx = 0; idx < genres.length; ++idx){
            String key = genres[idx];
            if(map.containsKey(key)){
                map.get(key).add(new Music(idx + 1, plays[idx]));
                playCntMap.put(key, playCntMap.get(key) + plays[idx]);
            }else{
                map.put(key, new ArrayList<Music>());
                map.get(key).add(new Music(idx + 1, plays[idx]));
                playCntMap.put(key, plays[idx]);
            }
        }
    
        for(String key : map.keySet()){
            Collections.sort(map.get(key));
        }
        Genre[] genress = new Genre[playCntMap.size()];      
        int idx = 0;
        for(String key : playCntMap.keySet()){
            genress[idx] = new Genre(key, playCntMap.get(key));
            ++idx;
        }
        Arrays.sort(genress);
        for(int i = 0; i < genress.length; ++i){
            String genre = genress[i].genre;
            List<Music> list = map.get(genre);
            
            if(list.size() == 1){
                answer.add(list.get(0).number - 1);
            }else{
                answer.add(list.get(0).number - 1);
                answer.add(list.get(1).number - 1);
            }
        }        
        
        int[] ans = new int[answer.size()];
        int j =0;
        for(int num : answer){
            ans[j++] = num;
        }
        
        return ans;
    }
}