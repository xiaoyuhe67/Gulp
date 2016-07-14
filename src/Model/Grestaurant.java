package Model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Grestaurant" database table.
 * 
 */
@Entity
@Table(name="\"Grestaurant\"")
@NamedQuery(name="Grestaurant.findAll", query="SELECT g FROM Grestaurant g")
public class Grestaurant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="\"GRESTAURANT\"_RESTAURANTID_GENERATOR", sequenceName="Grestaurant_seq", allocationSize=1  )
	@GeneratedValue(strategy=GenerationType.AUTO, generator="\"GRESTAURANT\"_RESTAURANTID_GENERATOR")
	@Column(name="\"RestaurantID\"")
	private long restaurantID;

	@Column(name="\"RestaurantAddress\"")
	private String restaurantAddress;

	@Column(name="\"RestaurantDiscription\"")
	private String restaurantDiscription;

	@Column(name="\"RestaurantName\"")
	private String restaurantName;

	//bi-directional many-to-one association to GReview
	@OneToMany(mappedBy="grestaurant")
	private List<GReview> greviews;

	public Grestaurant() {
	}

	public long getRestaurantID() {
		return this.restaurantID;
	}

	public void setRestaurantID(long restaurantID) {
		this.restaurantID = restaurantID;
	}

	public String getRestaurantAddress() {
		return this.restaurantAddress;
	}

	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}

	public String getRestaurantDiscription() {
		return this.restaurantDiscription;
	}

	public void setRestaurantDiscription(String restaurantDiscription) {
		this.restaurantDiscription = restaurantDiscription;
	}

	public String getRestaurantName() {
		return this.restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public List<GReview> getGreviews() {
		return this.greviews;
	}

	public void setGreviews(List<GReview> greviews) {
		this.greviews = greviews;
	}

	public GReview addGreview(GReview greview) {
		getGreviews().add(greview);
		greview.setGrestaurant(this);

		return greview;
	}

	public GReview removeGreview(GReview greview) {
		getGreviews().remove(greview);
		greview.setGrestaurant(null);

		return greview;
	}

}