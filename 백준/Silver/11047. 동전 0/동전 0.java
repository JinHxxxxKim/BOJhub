/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Main
{
	public static void main (String[] args) throws java.lang.Exception
	{
		int N, K;
		int numOfCoin = 0;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[] typeOfCoin = new int[N];
		
		for(int i=0;i<N;i++)
			typeOfCoin[N-1-i] = Integer.parseInt(bf.readLine());
		
		for(int i=0;i<N;i++){
			if(typeOfCoin[i]<=K){
				numOfCoin += (K/typeOfCoin[i]);
				K = K%typeOfCoin[i];
			}
		}
		System.out.println(numOfCoin);
	}
}