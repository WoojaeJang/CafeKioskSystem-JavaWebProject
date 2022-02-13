package coffeeOrder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coffeeOrder.service.CoffeeOrderService;
import coffeeOrder.model.dao.CoffeeOrderDAO;
import coffeeOrder.model.dao.CustomerDAO;
import coffeeOrder.model.dto.CoffeeOrderDTO;
import coffeeOrder.model.dto.CustomerDTO;


@WebServlet("/coffeeOrder")
public class CoffeeOrderController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String command = request.getParameter("command");
		
		try {
			if(command.equals("customerOrder")){ // 주문 하기
				customerOrder(request, response);
			}else if(command.equals("customerOrderCheck")){ // 주문 현황 확인 (고객용)
				customerOrderCheck(request, response);
			}else if(command.equals("customerOrderAdd")){ // 결제
				customerOrderAdd(request, response);		
			}else if(command.equals("customerSignUp")) { // 회원가입
				customerSignUp(request, response);
			}else if(command.equals("employeeOrderCheck")) { // 주문 현황 확인 (직원용)
				employeeOrderCheck(request, response);
			}else if(command.equals("employeeOrderProceeding")) { // 주문 확인 완료
				employeeOrderProceeding(request, response);
			}else if(command.equals("employeeOrderDone")) { // 제조 완료
				employeeOrderDone(request, response);
			}
			
		}catch(Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("showError.jsp").forward(request, response);
			e.printStackTrace();
		}
	}


	public void customerOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("customerOrder", CoffeeOrderService.getAllData());
			url = "customerOrder.jsp";
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	public void customerOrderCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("customerOrderCheck", CoffeeOrderService.getAllOrders());
			url = "customerOrderCheck.jsp";
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	public void customerOrderAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			String cid = request.getParameter("cid");
			String fid = request.getParameter("fid");
			String pid = request.getParameter("pid");
			
			CustomerDTO customer = CustomerDAO.getCustomer(cid);
			if(cid != "" && customer == null) {
				
				response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter writer = response.getWriter(); 
				url = "coffeeOrder?command=customerOrder";
				writer.println("<script>alert('존재하지 않는 id 입니다.'); history.go(-1);</script>"); 
				writer.close();
				
			}else if(fid == null){
				
				response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter writer = response.getWriter(); 
				url = "coffeeOrder?command=customerOrder";
				writer.println("<script>alert('메뉴 선택을 안하셨습니다.'); history.go(-1);</script>"); 
				writer.close();
				
			}else if(pid == null){
				
				response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter writer = response.getWriter(); 
				url = "coffeeOrder?command=customerOrder";
				writer.println("<script>alert('결제방식 선택을 안하셨습니다.'); history.go(-1);</script>"); 
				writer.close();
				
			}else {
				
				Timestamp orderTime = CoffeeOrderService.addCustomerOrder(cid, fid, pid);
				request.setAttribute("customerOrder", CoffeeOrderService.getOrderByTime(orderTime));
				url = "customerOrderSuccess.jsp";
				request.getRequestDispatcher(url).forward(request, response);
				
			}			
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		
	}
	
	public void customerSignUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			String cid= request.getParameter("cid");
			String cpw= request.getParameter("cpw");
			String cname= request.getParameter("cname");
			String phone_number = request.getParameter("phone_number");
			
			CustomerDTO customer = CustomerDAO.getCustomer(cid);
			if(cid == ""){
				
				response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter writer = response.getWriter(); 
				url = "customerSignUp.jsp";
				writer.println("<script>alert('id를 미입력했습니다.'); history.go(-1);</script>"); 
				writer.close();
				
			}else if(cid != "" && customer != null) {
				
				response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter writer = response.getWriter(); 
				url = "customerSignUp.jsp";
				writer.println("<script>alert('이미 존재하는 id 입니다.'); history.go(-1);</script>"); 
				writer.close();
				
			}else if(cpw == ""){
				
				response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter writer = response.getWriter(); 
				url = "customerSignUp.jsp";
				writer.println("<script>alert('비밀번호를 미입력했습니다.'); history.go(-1);</script>"); 
				writer.close();
				
			}else if(cname == ""){
				
				response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter writer = response.getWriter(); 
				url = "customerSignUp.jsp";
				writer.println("<script>alert('이름을 미입력했습니다.'); history.go(-1);</script>"); 
				writer.close();
				
			}else if(phone_number == ""){
				
				response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter writer = response.getWriter(); 
				url = "customerSignUp.jsp";
				writer.println("<script>alert('휴대폰 번호를 미입력했습니다.'); history.go(-1);</script>"); 
				writer.close();
				
			}else {
				
				request.setAttribute("customerSignUpResult", CoffeeOrderService.addCustomer(cid, cpw, cname, phone_number));
				url = "customerSignUpSuccess.jsp";
				request.getRequestDispatcher(url).forward(request, response);
				
			}
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}		
		
	}
	
	
	public void employeeOrderCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "showError.jsp";
		
		try {
			request.setAttribute("employeeOrderCheck", CoffeeOrderService.getAllOrders());
			url = "employeeOrderCheck.jsp";
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	public void employeeOrderProceeding(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "showError.jsp";
		try {
			String sOrderNo = request.getParameter("orderNo");
			if(sOrderNo == null) {
				
				response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter writer = response.getWriter(); 
				url = "employeeOrderCheck.jsp";
				writer.println("<script>alert('선택된 것이 업습니다.'); history.go(-1);</script>"); 
				writer.close();
				
			}else {
				
				int orderNo= Integer.parseInt(sOrderNo);
				CoffeeOrderDTO order = CoffeeOrderDAO.getOrder(orderNo);
				String orderCheck = order.getOrderCheck();
			
				if(orderCheck != null && orderCheck.equals("check")) {
					
					response.setContentType("text/html; charset=UTF-8"); 
					PrintWriter writer = response.getWriter(); 
					url = "employeeOrderCheck.jsp";
					writer.println("<script>alert('이미 확인된 주문입니다.'); history.go(-1);</script>"); 
					writer.close();
					
				}else if(orderCheck != null && orderCheck.equals("done")) {
					
					response.setContentType("text/html; charset=UTF-8"); 
					PrintWriter writer = response.getWriter(); 
					url = "employeeOrderCheck.jsp";
					writer.println("<script>alert('이미 완료된 주문입니다.'); history.go(-1);</script>"); 
					writer.close();
					
				}else {
					
					boolean result = CoffeeOrderService.updateOrder(orderNo, "check");
					request.setAttribute("employeeOrderCheckResult", result);
					request.setAttribute("employeeOrderCheck", CoffeeOrderService.getAllOrders());
					url = "employeeOrderCheck.jsp";
					request.getRequestDispatcher(url).forward(request, response);
				}
			}		
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}		
		
	}


	public void employeeOrderDone(HttpServletRequest request, HttpServletResponse response) {
		
		String url = "showError.jsp";
		try {
			String sOrderNo = request.getParameter("orderNo");
			if(sOrderNo == null) {
				
				response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter writer = response.getWriter(); 
				url = "employeeOrderCheck.jsp";
				writer.println("<script>alert('선택된 것이 업습니다.'); history.go(-1);</script>"); 
				writer.close();
				
			}else {
				
				int orderNo= Integer.parseInt(sOrderNo);
				CoffeeOrderDTO order = CoffeeOrderDAO.getOrder(orderNo);
				String orderCheck = order.getOrderCheck();
								
				if(orderCheck == null) {
					
					response.setContentType("text/html; charset=UTF-8"); 
					PrintWriter writer = response.getWriter(); 
					url = "employeeOrderCheck.jsp";
					writer.println("<script>alert('아직 확인이 안된 주문입니다.'); history.go(-1);</script>"); 
					writer.close();
					
					
				}else if(orderCheck.equals("done")) {
					
					response.setContentType("text/html; charset=UTF-8"); 
					PrintWriter writer = response.getWriter(); 
					url = "employeeOrderCheck.jsp";
					writer.println("<script>alert('이미 완료된 주문입니다.'); history.go(-1);</script>"); 
					writer.close();
					
				}else {
					
					boolean result = CoffeeOrderService.updateOrder(orderNo, "done");
					request.setAttribute("employeeOrderCheckResult", result);
					request.setAttribute("employeeOrderCheck", CoffeeOrderService.getAllOrders());
					url = "employeeOrderCheck.jsp";
					request.getRequestDispatcher(url).forward(request, response);
					
				}		
				
			}
			
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}		
		
	}

}
