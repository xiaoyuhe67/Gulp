

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBUtil.DBUtil;
import DBUtil.Dataget;
import Model.*;

/**
 * Servlet implementation class ProcessForm
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		
		if(request.getParameter("register")!=null)
		{
			request.getRequestDispatcher("/Register.jsp").forward(request, response);
		}
		else
		{
		
		try
		{	
			String email= request.getParameter("email");
			String password=request.getParameter("password");
			Guser user=Dataget.getUserByEmail(email);		
			if(Dataget.isValidUser(email,password))
			{	
					List<Grestaurant> restaurants=Dataget.Grestaurant();		
					HashMap<String, String> averageRatings=Dataget.averageRating();
						    
					
					
					session.setAttribute("AverageRatings", averageRatings);
					session.setAttribute("Restaurants", restaurants);
				    session.setAttribute("userid", user.getUserID());
					session.setAttribute("useremail",user.getUserEmail());
					session.setAttribute("username", user.getUserName());
					session.setAttribute("userpassword", user.getUserPassword());
					session.setAttribute("userzipcode", user.getZipcode());
					request.setAttribute("messages", user.getUserName());
					session.setAttribute("images", "https://www.gravatar.com/avatar/"+Util.MD5Util.md5Hex(user.getUserEmail())+"?s=80");
					List<GReview> Reviews=Dataget.GReview();
					session.setAttribute("Reviews", Reviews);
					List<GReview> MyReviews=Dataget.MyReview(user.getUserID());
					HashMap<String,String> imageurls=new HashMap<String,String>();
				    imageurls=Dataget.getGravatarUrl(Reviews);	 
				   
				   session.setAttribute("imageurls",imageurls ); 	    
					
					
					session.setAttribute("MyReviews", MyReviews);
					request.getRequestDispatcher("/home.jsp").forward(request, response);
					
			}	
			else
			{
				request.setAttribute("loginerror", "The user is not valid");
			
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				
			}		
					
			
			
		}catch(Exception e)
		{
			String message1="There is no match";
			System.out.println(e.getMessage());
			request.setAttribute("loginerror", message1);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			
	
		}
		finally
		{
			em.close();
			
		}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
		
		
	}
	
	

}
