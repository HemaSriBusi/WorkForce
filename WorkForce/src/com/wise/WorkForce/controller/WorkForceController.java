package com.wise.WorkForce.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wise.WorkForce.dao.AddressDAO;
import com.wise.WorkForce.dao.BidDAO;
import com.wise.WorkForce.dao.ForceDAO;
import com.wise.WorkForce.dao.ServiceTypeDAO;
import com.wise.WorkForce.dao.SubServiceTypeDAO;
import com.wise.WorkForce.dao.UserDAO;
import com.wise.WorkForce.dao.WorkDAO;
import com.wise.WorkForce.dto.Address;
import com.wise.WorkForce.dto.Bid;
import com.wise.WorkForce.dto.Force;
import com.wise.WorkForce.dto.ServiceType;
import com.wise.WorkForce.dto.SubServiceType;
import com.wise.WorkForce.dto.User;
import com.wise.WorkForce.dto.Work;

/**
 * Servlet implementation class WorkForceController
 */
@WebServlet("/WorkForceController")
public class WorkForceController extends HttpServlet {
	User user = new User();
	private static final long serialVersionUID = 1L;	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkForceController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("WELCOME");
		String action = request.getParameter("action");
		if( action == null ) {
			List<ServiceType> serviceTypeList = getServiceTypes();
			request.setAttribute("serviceTypesList", serviceTypeList);
			List<SubServiceType> subServiceTypeList = new SubServiceTypeDAO().getAll();
			request.setAttribute("subServiceTypesList", subServiceTypeList);
			request.getRequestDispatcher("Home.jsp").forward(request, response);
		} else if(action.equals("subServicesTypes")) {
			request.getRequestDispatcher("userHome.jsp").forward(request, response);
		} else if ( action.equals("logout") ) {
			request.getRequestDispatcher("logout.jsp").forward(request, response);
		}
	}

	/**xx
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String action = request.getParameter("action");
		String action1 = request.getParameter("action1");
		System.out.println(action +" "+action1);
		String role = request.getParameter("role");
		User user = new User();
		if ( action.equals("Login") ) {
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			if ( user.getPassword().equals(new UserDAO().getPassword(user.getEmail())) ) {
				request.getSession().setAttribute("logged", user);
				if ( role.equals("User")) {
					System.out.println("Login Successful");
					int id = new UserDAO().getUserId(user.getEmail());
					List<ServiceType> serviceTypeList = getServiceTypes();
					request.setAttribute("serviceTypesList", serviceTypeList);
					List<Work> workList = getUserWorks(id);
					System.out.println("work" +  workList);
					request.setAttribute("worksList",workList );
					request.getRequestDispatcher("userHome.jsp").forward(request, response);	
				}
				else if ( role.equals("Force") ){
					boolean isForce = false;
					int id = new UserDAO().getUserId(user.getEmail());
					System.out.println(id);
					List<Force> forceList = new ForceDAO().getAll();
					for ( Force force : forceList ) {
						if ( id == force.getForceId() ) {
							System.out.println("Login Successful");
							int subid = force.getSubServiceTypeId();
							List<Work> workDescriptionList = new WorkDAO().getForceWorkDetails(subid);
							//System.out.println(workDescriptionList);
							request.setAttribute("worksDescriptionList",workDescriptionList );
							request.getRequestDispatcher("./forceHome.jsp").forward(request, response);
							isForce = true;
						}
					}
					if ( !isForce){
						request.getRequestDispatcher("./Home.jsp").forward(request, response);
						System.out.println("Login Unsuccessful.Please Register First");
					}
				}
				else if ( !(role.equals("Force")) && !(role.equals("User")) ) {
					request.getRequestDispatcher("./Home.jsp").forward(request, response);

				}
			}
			else {
				System.out.println("Login Failed");
				request.getRequestDispatcher("./Home.jsp").forward(request, response);
			}
				
		}
		else if(action.equals("postWork")){
			PostWork(request,response);
		}
		else if(action.equals("bid")){
			System.out.println("hello");
			if( bidByForce(request,response) ) {
				request.getRequestDispatcher("./forceHome.jsp").forward(request, response);
			}
		}
		else if ( action.equals("acceptBid") ) {
			Work work = new Work();
			String id = request.getParameter("action1");
			work.setWorkId(Integer.parseInt(id));
			if( new WorkDAO().workUpdate(work.getWorkId()) != 0 ){
				System.out.println("work");
				request.getRequestDispatcher("./userHome.jsp").forward(request, response);
			}
			//request.getRequestDispatcher("userHome.jsp").forward(request, response);
		} 
		else if ( action.equals("RegisterAsUser") ){
			if ( registerAsUser(request) ) {
				int userId = new UserDAO().getUserId(user.getEmail());
				if ( insertAddress(request, userId) ) {
					System.out.println("Successfully registered");
					request.getRequestDispatcher("./forceHome.jsp").forward(request, response);
				}
			}
			else {
				System.out.println("Registration unsuccessful");
				request.getRequestDispatcher("./Home.jsp").forward(request, response);
			}
		}

		else if ( action.equals("RegisterAsForce") ){
			if ( registerAsUser(request) ) {
				int userId = new UserDAO().getUserId(user.getEmail());
				if ( insertAddress(request, userId) ) {
					if ( insertForceDetails(request,userId) ) {
							System.out.println("Successfully registered");
							request.getRequestDispatcher("./Home.jsp").forward(request, response);
					}
				}
			}
			else {
				System.out.println("Registration unsuccessful");
				request.getRequestDispatcher("./Home.jsp").forward(request, response);
			}
		}
		//dispatcher.forward(request,response);
		//dispatcher.forward(request,response);
		return;
	}
	
	
	
	private void PostWork(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Object obj = request.getSession().getAttribute("logged");
		User user = (User)obj;
		int userId = new UserDAO().getUserId(user.getEmail());
		//System.out.println("postwork"+userId);
		Work work = new Work();
		work.setUserId(userId);
		work.setDescription(request.getParameter("description"));
		work.setSubServiceTypeId(Integer.parseInt(request.getParameter("SubServiceType")));
		work.setDate(new Date());
		if ( new WorkDAO().insertWork(work) != 0 ){
			System.out.println("True");
			request.getRequestDispatcher("./userHome.jsp");
		} else
		   System.out.println("False");
	}
	

	protected boolean registerAsUser(HttpServletRequest request) {
		//User user = new User();
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setPhoneNumber(request.getParameter("phoneNumber"));
		if ( new UserDAO().insert(user) != 0)
			return true;
		return false;
	}
	
	protected boolean insertAddress(HttpServletRequest request, int userId){
		
		Address address = new Address();
		address.setUserId(userId);
		address.setDoorNo(request.getParameter("doorNumber"));
		address.setStreet(request.getParameter("street"));
		address.setCity(request.getParameter("city"));
		address.setState(request.getParameter("state"));
		address.setPostalCode(Integer.parseInt(request.getParameter("postalCode")));
		address.setCountry(request.getParameter("country"));
		if ( new AddressDAO().insert(address) != 0 ){
			return true;
		}
		return false;
	}
	
	protected boolean insertForceDetails(HttpServletRequest request, int userId) {
		Force force = new Force();
		force.setForceId(userId);
		force.setExperience(Integer.parseInt(request.getParameter("experience")));
		force.setCostPerHour(Integer.parseInt(request.getParameter("costPerHour")));
		force.setSubServiceTypeId(Integer.parseInt(request.getParameter("subserviceType")));
		if ( new ForceDAO().insert(force) != 0  )
			return true;
		return false;
	}
	
	protected List<Work> getUserWorks(int userId) {
		List<Work> workList = new WorkDAO().getWorkDetails(userId);
		for ( Work work : workList ) {
			work.setBid(new BidDAO().getBidDetails(work.getWorkId()));
		}
		return workList;
	}
	
	protected boolean bidByForce(HttpServletRequest request, HttpServletResponse response) {
		Object obj = request.getSession().getAttribute("logged");
		User user = (User)obj;
		Bid bid = new Bid();
		bid.setWorkId(Integer.parseInt(request.getParameter("action1")));
		bid.setBidAmount(Integer.parseInt(request.getParameter("amount")));
		int id = new UserDAO().getUserId(user.getEmail());
		System.out.println(id);
		bid.setForceId(id);
		if ( new BidDAO().insertBid(bid) != 0 ) {
			return true;
		}
		return false;
	}
	
	private List<ServiceType> getServiceTypes() {
        List<ServiceType> serviceTypesList = new ServiceTypeDAO().getAll();
        for (ServiceType serviceType : serviceTypesList) {
            serviceType.setSubServiceType(new SubServiceTypeDAO().getSubService(serviceType.getServiceTypeId())); 
        }
        return serviceTypesList;
    }
}