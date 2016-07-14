

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBUtil.Dataget;
import Model.*;


/**
 * Servlet implementation class homeServlet
 */
@WebServlet("/homeServlet")
public class homeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public homeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String method = request.getParameter("method");
		String restaurantid = (String)request.getParameter("restaurantid");
		if(restaurantid!=null)
		{
			long longrestrantpostid=Dataget.getrestaurantid(restaurantid);
		
			Grestaurant grestaurant=Dataget.restaurantofrestaurantid(longrestrantpostid);
			List<GReview> Reviews=Dataget.Reviewsofrestaurant(longrestrantpostid);
			session.setAttribute("myrestaurantid", grestaurant.getRestaurantID());
			session.setAttribute("restaurantname", grestaurant.getRestaurantName());
			session.setAttribute("restaurantaddress", grestaurant.getRestaurantAddress());
			session.setAttribute("restaurantdescription", grestaurant.getRestaurantDiscription());
			HashMap<String, String> averageRatings=Dataget.averageRating();
			session.setAttribute("AverageRatings", averageRatings);
			HashMap<String, String> NumberofRatings =Dataget.NumberofRatings();
			session.setAttribute("NumberofRatings", NumberofRatings);
			session.setAttribute("Reviewsofrestaurant", Reviews);
			
			HashMap<String,String> imageurls=new HashMap<String,String>();
		    imageurls=Dataget.getGravatarUrl(Reviews);	 
		   
		   session.setAttribute("imageurlsofrestaurant",imageurls ); 	    
			
			request.getRequestDispatcher("/restaurant.jsp").forward(request, response);
		}
		 else if(method.equals("Search"))
		 {
			 String searchrestaurant= request.getParameter("searchrestaurant"); 
			 List<Grestaurant> searchRestaurants=new ArrayList<Grestaurant>();
			 
			 try
			 {
				 if(searchrestaurant.equals(null))
				 {
					 List<Grestaurant> restaurants=Dataget.Grestaurant();		
						HashMap<String, String> averageRatings=Dataget.averageRating();
						session.setAttribute("AverageRatings", averageRatings);
						session.setAttribute("Restaurants", restaurants);		    
						
				 }
				 else
				 {
					 searchRestaurants=Dataget.searchRestaurants(searchrestaurant);
					 session.setAttribute("Restaurants", searchRestaurants);
					 HashMap<String, String> averageRatings=Dataget.averageRating();
					 session.setAttribute("AverageRatings", averageRatings);
					 getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);;
				 }
				 
			 }
			 catch(Exception e)
			 {
				 
			 }
			 
		 }
	
		
		
		
	}
		
		

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub 	
		
	}
	

}
