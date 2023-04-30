package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import db.ConnectionProvider;
import vo.BookVO;

public class BookDAO {
	
	
	//새로운 도서를 등록하는 메소드를 정의
	public int  insertBook(BookVO b) {
		int re = -1;
		
		int bookid = getNextBookId();
		String 
		sql = "insert into "
		+ "book(bookid,bookname,publisher,price) "
		+ "values(?,?,?,?)";
		
		try {
			Connection conn =
					ConnectionProvider.getconnection();
			PreparedStatement pstmt 
				= conn.prepareStatement(sql);
			pstmt.setInt(1, bookid);
			pstmt.setString(2, b.getBookname());
			pstmt.setString(3, b.getPublisher());
			pstmt.setInt(4, b.getPrice());
			
			re = pstmt.executeUpdate();
			ConnectionProvider.close2(pstmt, conn);
					
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return re;
	}
	
	
	
	
	
	//새로운 도서번호를 반환하는 메소드를 정의
	public int getNextBookId() {
		int bookid=0;
		String sql = "select nvl(max(bookid),0)+1 from book";
		try {
			Connection conn = ConnectionProvider.getconnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				bookid = rs.getInt(1);
			}
			ConnectionProvider.close(rs, stmt, conn);
					
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		return bookid;
	}
	
	
	
	//모든도서목록을 반환하는 메소드를 정의
	public ArrayList<BookVO> listBook(){
		ArrayList<BookVO> list 
			= new ArrayList<BookVO>();
		
		String sql = "select * from book order by bookid";
		try {
			Connection conn = 
					ConnectionProvider.getconnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int bookid = rs.getInt(1);
				String bookname = rs.getString(2);
				String publisher = rs.getString(3);
				int price = rs.getInt(4);
				BookVO b = new BookVO();
				b.setBookid(bookid);
				b.setBookname(bookname);
				b.setPublisher(publisher);
				b.setPrice(price);
				list.add(b);
			}
			ConnectionProvider.close(rs, stmt, conn);
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		
		return list;
	}
}
