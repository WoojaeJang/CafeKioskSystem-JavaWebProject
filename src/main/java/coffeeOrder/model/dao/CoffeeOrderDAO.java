package coffeeOrder.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import coffeeOrder.model.dto.CoffeeOrderDTO;
import coffeeOrder.model.util.DBUtil;

public class CoffeeOrderDAO {
	
	public static boolean addOrder(CoffeeOrderDTO order) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into coffee_order values(seq_no.nextval, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, order.getCid());
			pstmt.setString(2, order.getFname());
			pstmt.setInt(3, order.getPayment());
			pstmt.setString(4, order.getPname());
			pstmt.setTimestamp(5, order.getOrderTime());
			pstmt.setString(6, order.getOrderCheck());
			
			int result = pstmt.executeUpdate();
		
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	public static boolean deleteOrder(int orderNo) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from coffee_order where order_no=?");
			pstmt.setInt(1, orderNo);
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	public static CoffeeOrderDTO getOrder(int orderNo) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		CoffeeOrderDTO order = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from coffee_order where order_no=?");
			pstmt.setInt(1, orderNo);
			rset = pstmt.executeQuery();
			if(rset.next()){
				order = new CoffeeOrderDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getTimestamp(6), rset.getString(7));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return order;
	}

	public static ArrayList<CoffeeOrderDTO> getAllOrders() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<CoffeeOrderDTO> list = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from coffee_order order by order_no");
			rset = pstmt.executeQuery();
			
			list = new ArrayList<CoffeeOrderDTO>();
			while(rset.next()){
				list.add(new CoffeeOrderDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getTimestamp(6), rset.getString(7)));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}

	public static CoffeeOrderDTO getOrderByTime(Timestamp orderTime) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		CoffeeOrderDTO order = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from coffee_order where order_time=TO_TIMESTAMP(?)");
			pstmt.setTimestamp(1, orderTime);
			rset = pstmt.executeQuery();
			if(rset.next()){
				order = new CoffeeOrderDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4), rset.getString(5), rset.getTimestamp(6), rset.getString(7));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return order;
	}

	public static boolean updateOrder(int orderNo, String orderCheck) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update coffee_order set order_check=? where order_no=?");
			pstmt.setString(1, orderCheck);
			pstmt.setInt(2, orderNo);
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}		
		return false;
	}
}
