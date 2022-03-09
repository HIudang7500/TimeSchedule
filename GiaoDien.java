package TimeScheduling;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import chieuthu2.ATM;

public class GiaoDien implements ActionListener {
	Frame f = new Frame("TKB");
	TextField bangtrangThai = new TextField(10);
	Label trangThai = new Label("Bảng trạng thái");
	Button hienThiTuFile = new Button("Nhập và hiển thị chi tiết danh sách môn học:");
	TextArea danhSach1 = new TextArea(10,18);
	TextArea danhSach2 = new TextArea(10,18);
	TextArea danhSach3 = new TextArea(10,10);
	Button hienThiTKB = new Button("Thời khóa biểu gợi ý");
	static TextArea goiY = new TextArea(30,60);
	Button dong = new Button("Đóng");
	Panel p1 = new Panel();
	
	static ArrayList<String> tenMonHoc = new ArrayList<String>();
	static ArrayList<String> tenGV = new ArrayList<String>();
	static ArrayList<String> tkb = new ArrayList<String>();
	static int counte = 0;
	public GiaoDien()
	{
		f.setLayout(new FlowLayout());
		f.add(hienThiTuFile);
		f.add(p1);
		p1.setLayout(new GridLayout(1,3));
		p1.add(danhSach1);
		p1.add(danhSach2);
		p1.add(danhSach3);
		f.add(trangThai);
		f.add(bangtrangThai);
		f.add(hienThiTKB);
		f.add(goiY);
		f.add(dong);
		f.setSize(550,750);
		f.setVisible(true);
		dong.addActionListener(this);
		hienThiTuFile.addActionListener(this);
		hienThiTKB.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == dong) {
			System.exit(0);
		}
		if(ae.getSource() == hienThiTuFile) {
			String s;
			danhSach1.setText("");
			danhSach2.setText("");
			danhSach3.setText("");
			tenMonHoc.clear();
			tenGV.clear();
			tkb.clear();
			try {
				FileInputStream f1 = new FileInputStream("TKB.txt");
				DataInputStream df1 = new DataInputStream(f1); 
				bangtrangThai.setText("OK");
				    danhSach1.append("  Tên Học Phần\n");
				    danhSach2.append("  Tên Giảng Viên\n");
				    danhSach3.append("  Lịch\n");
				int i = 1;
				while ((s= df1.readLine()) != null) {
					String[] part = s.split(" ", 3); 
					tenMonHoc.add(part[0]);
					tenGV.add(part[1]);
					tkb.add(part[2]);
					danhSach1.append(i+ ". "+ part[0] +"\n");
				    danhSach2.append(i+ ". "+ part[1] +"\n");
				    danhSach3.append(i+ ". "+ part[2] +"\n");
				    i++;
				}
				
				df1.close();
				f1.close();
			}
			catch (IOException e) {
				bangtrangThai.setText("Loi");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ae.getSource() == hienThiTKB) {
			counte = 0;
			goiY.setText("");
			
			goiY.append("                     ~Chú ý~\n");
			goiY.append("     - Buổi 1: Tương đương: Buổi Sáng tiết 1-2\n");
			goiY.append("     - Buổi 2:           ~            Buổi Sáng tiết 3-5\n");
			goiY.append("     - Buổi 3:           ~            Buổi Chiều tiết 6-7\n");
			goiY.append("     - Buổi 4:           ~            Buổi Chiều tiết 8-9\n");
			goiY.append("     - Số trên thời khóa biểu là số của giáo viên !!\n\n");
			
			int tongsomon = 1;
			for(int i = 0; i < tenMonHoc.size() -1 ; i++ ) {
				if ( ! (tenMonHoc.get(i)).equals(tenMonHoc.get(i+1) ) ) { // nếu môn học trước khac môn học ngay sau nó					tongsomon++;
					tongsomon ++;
				}
			}
//			System.out.println(tongsomon); ////da check oke
//			
			int[] a = new int[tongsomon]; // a = a1
			int[] cauhinhdau = new int[tongsomon]; // cauhinhdau = flag
			int[] cauhinhcuoi = new int[tongsomon]; // cauhinhcuoi = soloai
//			
			int j = 1;
			cauhinhdau[0] = 1;
			for(int i = 0; i < tenMonHoc.size() - 1; i++ ) {
				if ( ! ((tenMonHoc.get(i)).equals(tenMonHoc.get(i+1)) )) { // nếu môn học trước khac môn học ngay sau nó					tongsomon++;
					cauhinhdau[j] = i+2;
					cauhinhcuoi[j-1] = i+1; 
					j++;
				}
			}
			cauhinhcuoi[tongsomon - 1] = tenMonHoc.size();
//			for (int i = 0; i < tongsomon; i++) {
//	    		System.out.print(" " + cauhinhcuoi[i] + ", ");
//	    	}
//	    	System.out.println();         //da check cau hinh dau, cau hinh cuoi OKE                    
//			
////			 Thuật toán tìm tổ hợp các môn.
			genarate(a, tongsomon, cauhinhdau, cauhinhcuoi );
			goiY.append("\n\n\t Số thời khóa biểu bị trùng lặp là: " + counte);
		}
	}
	public static void Init(int a[], int k, int b[]) {
		int i;
		for (i = 0; i < k; i++) {
			a[i] = b[i];
		}
	}
	
	public static void Out(int a[], int k, int b[]) {
		// create 2 dimension array bij = 0;
		int lichcuthe[][] = new int[4][7];
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 7; j++) 
				lichcuthe[i][j] = 0;
		// trans string to int in tkb[] & filter collapse value
		int collapse = 0;
		for (int i = 0; i < k; i++) {
			String[] part = tkb.get(a[i]-1).split(" "); // tach 23-21 thanh 23 21
			for(String s2 : part) { 
				String[] p2 = s2.split("");		//tach 23 thanh 2 3 roi luu vao lichcuthe23= cauhinhdau[i]
				int h[] = new int[2];
				h[0] = Integer.parseInt(p2[0]);
				h[1] = Integer.parseInt(p2[1]);
				if(! (lichcuthe[h[0]-1][h[1]-1] == 0))  collapse = 1;
				lichcuthe[h[0]-1][h[1]-1] = a[i];
				}
		}
		if(collapse == 1) counte ++;
		//------------
		
		if(collapse == 0) {
			for(int h = 0; h < k; h++) {
				goiY.append(tenMonHoc.get(a[h]-1)+ ": "+ tenGV.get(a[h]-1) +"___Số:" + a[h] + "\n");
			}
			goiY.append("    Buổi    |    Thứ 2    |    Thứ 3    |    Thứ 4    |    Thứ 5    |    Thứ 6    |    Thứ 7    |  Chủ Nhật  |\n");
			goiY.append("---------------------------------------------------------------------------------------------------------------------\n");
			for(int h = 0; h < 4; h++)
			{
				if(h == 2) goiY.append("---------------------------------------------------------------------------------------------------------------------\n");
				goiY.append("       " + (h+1) + "      |");
				
				for(int j = 0; j < 7; j++) 
						
					     if(! (lichcuthe[h][j] == 0)) {
					    	 goiY.append("        " +lichcuthe[h][j] + "        |");//lichcuthe[h][j] là chỉ số của cauhinhdau
					     }
					     else goiY.append("        =        |");
				goiY.append("\n");
			}
			goiY.append("\n");
			goiY.append("                     ~~~~~~~~~~*************\\o0.0o/*************~~~~~~~~~~\n");
			goiY.append("                               ~~~~~~~~~~*********v*********~~~~~~~~~~\n");
		}
	}
	
	public static int lastconfig(int a[], int k, int c[]) {
		int i;
		for( i = k-1; i >= 0; i--) {
			if( a[i] != c[i]) return 0;
		}
		return 1;
	}
	static void next_combine(int a[], int k, int b[], int c[]) {
		int i = k-1;
		while( i>= 0 && a[i] == c[i]) i--;
		a[i]++;
		int j;
		for ( j = i + 1; j < k; j++) a[j] = b[j];
	}
	static void genarate(int a[], int k, int b[], int c[]) {
		Init(a,k,b);
		Out(a,k,b);
		int stop = lastconfig(a,k,c);
		while (stop == 0) {
			next_combine(a,k,b,c); Out(a,k,b);
			stop = lastconfig(a,k,c);
		}
	}
	
}
