package TimeScheduling;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import java.io.IOException;

public class ThucHien 
{ 

	public static void main(String[] args)throws IOException
	{
		new GiaoDien();
		System.out.println();
		
	}
}
////---------------------------------------------------------------------------------------		
//		ArrayList<String> tkb = new ArrayList<String>();
//		ArrayList<Integer> ctkb = new ArrayList<>();
//		tkb.add("21 13 35");
//		tkb.add("23 33 26 45");
//		int buoi[][] = new int[4][7];
//		int lichcuthe[][] = new int[4][7];
//		
//		int b[] = new int[] {1,3,5}; 
//		for(int i = 0; i < 4; i++)
//		{
//			for(int j = 0; j < 7; j++) {
//				buoi[i][j] = 0;
//				lichcuthe[i][j] = 0;
//				System.out.print(buoi[i][j] + " ");
//			}
//			System.out.println();
//		}
//
//		for (int i = 0; i < 2; i++) {
//			String[] part = tkb.get(i).split(" "); // tach 23-21 thanh 23 21
//			for(String s2 : part) { 
//				String[] p2 = s2.split("");		//tach 23 thanh 2 3 roi luu vao lichcuthe23= cauhinhdau[i]
//				int h[] = new int[2];
//				h[0] = Integer.parseInt(p2[0]);
//				h[1] = Integer.parseInt(p2[1]);
//				lichcuthe[h[0]-1][h[1]-1] = b[i];
//				}
//		}
//		System.out.println();
//		for(int i = 0; i < 4; i++)
//		{
//			for(int j = 0; j < 7; j++) {
//				System.out.print(lichcuthe[i][j] + " ");
//			}
//			System.out.println();
//		}
//---------------------------------------------------------------------------------------


//System.out.println(tkb.get(1));
//// ghep tkb1 tkb2
//for (String s1 : tkb) {
//	String[] part = s1.split(" ");
//	for(String s2 : part) {
//		System.out.println(s2);
//		ctkb.add(Integer.parseInt(s2));
//	}
//}
//
//
//
//for(int i : ctkb) {
//	System.out.println(i);
//}
//---------------------------------------------------------------------------------------
//static String[] s = new String[] {"toan1","toan2","van1", "van2", "van3", "anh" };
//		int[] soloai = new int[] {2, 5, 6};
//		int[] flag = new int[] {1, 3, 6};
//		int somon = 3;
//		int[] a1 = new int[3];

//		genarate(a1, somon , soloai, flag);
	
//	public static void Init(int a[], int k, int c[]) {
//		int i;
//		for (i = 0; i < k; i++) {
//			a[i] = c[i];
//		}
//	}
//	
//	public static void Out(int a[], int k) {
//		for(int i = 0; i < k; i++) {
//			System.out.print(a[i] + " ");
//		}
//		System.out.println("\n");
//	}
//	
//	public static int lastconfig(int a[], int k, int b[]) {
//		int i;
//		for( i = k-1; i >= 0; i--) {
//			if( a[i] != b[i]) return 0;
//		}
//		return 1;
//	}
//	static void next_combine(int a[], int k, int b[], int c[]) {
//		int i = k-1;
//		while( i>= 0 && a[i] == b[i]) i--;
//		a[i]++;
//		int j;
//		for ( j = i + 1; j < k; j++) a[j] = c[j];
//	}
//	static void genarate(int a[], int k, int b[], int c[]) {
//		Init(a,k,c);
//		Out(a,k);
//		int stop = lastconfig(a,k,b);
//		while (stop == 0) {
//			next_combine(a,k,b,c); Out(a,k);
//			stop = lastconfig(a,k,b);
//		}
//	}

