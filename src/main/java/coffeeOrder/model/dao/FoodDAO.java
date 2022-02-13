package coffeeOrder.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import coffeeOrder.model.dto.FoodDTO;
import coffeeOrder.model.util.DBUtil;

public class FoodDAO {
	
	public static boolean addFood(FoodDTO food) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into food values(?, ?, ?)");
			pstmt.setString(1, food.getFid());
			pstmt.setString(2, food.getFname());
			pstmt.setInt(3, food.getPrice());
			
			int result = pstmt.executeUpdate();
		
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	public static boolean deleteFood(String fid) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from food where fid=?");
			pstmt.setString(1, fid);
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	public static FoodDTO getFood(String fid) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		FoodDTO food = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from food where fid=?");
			pstmt.setString(1, fid);
			rset = pstmt.executeQuery();
			if(rset.next()){
				food = new FoodDTO(rset.getString(1), rset.getString(2), rset.getInt(3));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return food;
	}

	public static ArrayList<FoodDTO> getAllFoods() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FoodDTO> list = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from food");
			rset = pstmt.executeQuery();
			
			list = new ArrayList<FoodDTO>();
			while(rset.next()){
				list.add(new FoodDTO(rset.getString(1), rset.getString(2), rset.getInt(3)));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
}
