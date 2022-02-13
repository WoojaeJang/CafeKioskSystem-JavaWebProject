package coffeeOrder.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import coffeeOrder.exception.NotExistException;
import coffeeOrder.model.dto.CoffeeOrderDTO;
import coffeeOrder.model.dto.CustomerDTO;
import coffeeOrder.model.dto.FoodDTO;
import coffeeOrder.model.dto.PayDTO;
import coffeeOrder.model.dao.CoffeeOrderDAO;
import coffeeOrder.model.dao.CustomerDAO;
import coffeeOrder.model.dao.FoodDAO;
import coffeeOrder.model.dao.PayDAO;



public class CoffeeOrderService {

	public static Map<String, Object> getAllData() throws SQLException, NotExistException{
		
		ArrayList<CustomerDTO> customerAll = CustomerDAO.getAllCustomers();
		ArrayList<FoodDTO> foodAll = FoodDAO.getAllFoods();
		ArrayList<PayDTO> payAll = PayDAO.getAllPays();
		
		Map<String, Object> dataAll = new HashMap<String, Object>(); 
		dataAll.put("customerData", customerAll);
		dataAll.put("foodData", foodAll);
		dataAll.put("payData", payAll);
		
		
		return dataAll;
	}

	public static Timestamp addCustomerOrder(String cid, String fid, String pid) throws SQLException,NotExistException {
		
		if(cid != null) {
			CustomerDTO customer = CustomerDAO.getCustomer(cid);
			if(customer == null) {
				cid = null;
			}
		}
		FoodDTO food = FoodDAO.getFood(fid);
		PayDTO pay = PayDAO.getPay(pid);
		Timestamp orderTime = new Timestamp(System.currentTimeMillis());
		
		CoffeeOrderDTO order = new CoffeeOrderDTO(1, cid, food.getFname(), food.getPrice(), pay.getPname(), orderTime, null);
		
		boolean result = CoffeeOrderDAO.addOrder(order);
		if(!result){
			throw new NotExistException("결제 실패");
		}
		return orderTime;
		
	}
	
	
	public static boolean addCustomer(String cid, String cpw, String cname, String phone_number) throws SQLException, NotExistException {
		
		return CustomerDAO.addCustomer(cid, cpw, cname, phone_number);
	}

	public static ArrayList<CoffeeOrderDTO> getAllOrders() throws SQLException{
		return CoffeeOrderDAO.getAllOrders();
	}

	public static Object getOrderByTime(Timestamp orderTime) throws SQLException{
		return CoffeeOrderDAO.getOrderByTime(orderTime);
	}

	public static boolean updateOrder(int orderNo, String order_check) throws SQLException{
		return CoffeeOrderDAO.updateOrder(orderNo, order_check);
	}

	
}
