import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import DBUtil.DBUtil;

public class Test {

	@org.junit.Test
	public void test() {
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        HashMap<String, String> averageRatings =new HashMap<String,String>();
        String qString = "select b.grestaurant.restaurantName , avg(b.rateNumber) from GReview b group by b.grestaurant.restaurantName";
        try{
            Query query = em.createQuery(qString);
            List<Object[]> resultList = query.getResultList();
            for(Object[] result: resultList)
            {
            	
            	averageRatings.put(result[0].toString(),result[1].toString());
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            em.close();
            
        }
        
        System.out.println(averageRatings.get("Cajun Food"));
	}

}
