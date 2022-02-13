package coffeeOrder.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import coffeeOrder.model.dto.CustomerDTO;
import coffeeOrder.model.util.DBUtil;

public class CustomerDAO {
	
	public static boolean addCustomer(String cid, String cpw, String cname, String phone_number) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into customer (cid, cpw, cname, phone_number) values(?, ?, ?, ?)");
			pstmt.setString(1, cid);
			pstmt.setString(2, cpw);
			pstmt.setString(3, cname);
			pstmt.setString(4, phone_number);
			
			int result = pstmt.executeUpdate();
		
			if(result == 1){
				return true;
			}
		}catch(SQLException s) {
			s.printStackTrace();
		}
		finally{
			DBUtil.close(con, pstmt);
		}
		
		return false;
	}
	
	public static boolean deleteCustomer(String cid) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from customer where cid=?");
			pstmt.setString(1, cid);
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	public static CustomerDTO getCustomer(String cid) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		CustomerDTO customer = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from customer where cid=?");
			pstmt.setString(1, cid);
			rset = pstmt.executeQuery();
			if(rset.next()){
				customer = new CustomerDTO(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getInt(5));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return customer;
	}

	public static ArrayList<CustomerDTO> getAllCustomers() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<CustomerDTO> list = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from customer");
			rset = pstmt.executeQuery();
			
			list = new ArrayList<CustomerDTO>();
			while(rset.next()){
				list.add(new CustomerDTO(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getInt(5)) );
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
}
