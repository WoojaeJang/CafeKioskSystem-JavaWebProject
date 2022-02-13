package coffeeOrder.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import coffeeOrder.model.dto.PayDTO;
import coffeeOrder.model.util.DBUtil;

public class PayDAO {
	
	public static boolean addPay(PayDTO pay) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into pay values(?, ?)");
			pstmt.setString(1, pay.getPid());
			pstmt.setString(2, pay.getPname());
			
			int result = pstmt.executeUpdate();
		
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	public static boolean deletePay(String pid) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from pay where pid=?");
			pstmt.setString(1, pid);
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	public static PayDTO getPay(String pid) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PayDTO pay = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from pay where pid=?");
			pstmt.setString(1, pid);
			rset = pstmt.executeQuery();
			if(rset.next()){
				pay = new PayDTO(rset.getString(1), rset.getString(2));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return pay;
	}

	public static ArrayList<PayDTO> getAllPays() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PayDTO> list = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from pay");
			rset = pstmt.executeQuery();
			
			list = new ArrayList<PayDTO>();
			while(rset.next()){
				list.add(new PayDTO(rset.getString(1), rset.getString(2)));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
}
