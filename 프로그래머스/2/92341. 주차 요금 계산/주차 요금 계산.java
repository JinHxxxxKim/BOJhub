import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        // 기본시간, 기본요금, 단위시간, 단위요금
        
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> ansMap = new HashMap<>();
        
        for(String record : records){
            StringTokenizer st1 = new StringTokenizer(record);
            String timeStr = st1.nextToken();
            String car = st1.nextToken();
            String inOut = st1.nextToken();
            
            StringTokenizer st2 = new StringTokenizer(timeStr, ":");
            int time = Integer.parseInt(st2.nextToken()) * 60 + Integer.parseInt(st2.nextToken()); // 현재시간
            
            if(inOut.equals("IN")){
                map.put(car, time);
            }else{
                int totalFee = 0;
                int totalTime = time - map.get(car); // 출차시간 - 입차시간 = 총 주차시간
                map.remove(car);
                if(ansMap.containsKey(car)){
                    ansMap.put(car, ansMap.get(car) + totalTime);
                }else{
                    ansMap.put(car, totalTime);
                }
            }
        }
        
        if(map.size()!=0){
            int totalFee = 0;
            for(String car : map.keySet()){
                System.out.println("잔여: :" + car);
                int totalTime = (23 * 60 + 59) - map.get(car);
                System.out.println("totalTime: :" + totalTime);
                if(ansMap.containsKey(car)){
                    ansMap.put(car, ansMap.get(car) + totalTime);
                }else{
                    ansMap.put(car, totalTime);
                }
            }
        }
        
        System.out.println(ansMap);
        Car[] cars = new Car[ansMap.size()];
        int idx = 0;
        for(String key : ansMap.keySet()) {
            Integer totalTime = ansMap.get(key);
            int totalFee = 0;
            if(totalTime <= fees[0]){
                totalFee = fees[1];
            }else{
                totalFee += fees[1];
                if((totalTime - fees[0]) % fees[2] == 0){
                    totalFee += ((totalTime - fees[0]) / fees[2]) * fees[3];
                }else{
                    totalFee += ((totalTime - fees[0]) / fees[2] + 1) * fees[3];
                }
            }  
            System.out.println("TOTAL FEE: " + totalFee);
            cars[idx] = new Car();
            cars[idx].num = key;
            cars[idx].fee = totalFee;
            ++idx;
        }
        Arrays.sort(cars);
        int[] answer = new int[cars.length];
        idx = 0;
        for(Car car : cars){
            answer[idx++] = car.fee;
        }
        
        
        
        System.out.println(map);
        System.out.println(ansMap);
        
        return answer;
    }
    
    static class Car implements Comparable<Car> {
        String num;
        int fee;
        @Override
        public int compareTo(Car o){
            return this.num.compareTo(o.num);
        }
    }
}