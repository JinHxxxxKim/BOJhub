/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
public class Main
{
	public static void main (String[] args) throws java.lang.Exception
	{
	    int cnt = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Integer[] arr = new Integer[N+1];

		Stack<Integer> stk = new Stack<>();

		for(int i=1;i<=N;i++)
		    arr[i] = i;

		int curIdx = 1;
        boolean isItChecksHighestPoint = false;
        
		while(cnt<N){
		    int num = Integer.parseInt(br.readLine());
		    //System.out.println(num+"num");
		    //System.out.println(curIdx);
		    if(num<N && !isItChecksHighestPoint){
		        //System.out.println("________");
		        if(!stk.empty()&&num==stk.peek()){
		            //System.out.println("aaaaaaaa");
		            sb.append("-\n");
		            stk.pop();
		        }
		        else if(stk.search(num)!=-1){
		            System.out.println("NO");
		            return;
		        }
		        else{
		            while(num!=arr[curIdx]){
		                //System.out.println(arr[curIdx]);
		                stk.push(arr[curIdx]);
		                if(curIdx>N){
		                    System.out.println("NO");
		                    return;
		                }
		                sb.append("+\n");
		                curIdx++;
		            }
		            sb.append("+\n-\n");
		            curIdx++;
		        }
		    }
		    else if(num==N){
		        while(num!=arr[curIdx]){
		                //System.out.println(arr[curIdx]);
		                stk.push(arr[curIdx]);
		                if(curIdx>N){
		                    System.out.println("NO");
		                    return;
		                }
		                sb.append("+\n");
		                curIdx++;
		            }
		            sb.append("+\n-\n");
		            curIdx++;
		        isItChecksHighestPoint = true;
		    }
		    else{
		        if(num == stk.pop())
		            sb.append("-\n");
		        else{
		            System.out.println("NO");
		            return;
		        }
		            
		    }
		    cnt++;
		}
		System.out.println(sb.toString());
	}
}