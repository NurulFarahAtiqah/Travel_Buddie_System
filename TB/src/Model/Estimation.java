package Model;

public class Estimation {
	
	private int id;
	private double totalEstimation;
	private int id_food;
	private int id_Hotel;
	private int id_Attraction;
	private int id_session;
	private int quantity =0 ;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getTotalEstimation() {
		return totalEstimation;
	}
	public void setTotalEstimation(double totalEstimation) {
		this.totalEstimation = totalEstimation;
	}
	public int getId_food() {
		return id_food;
	}
	public void setId_food(int id_food) {
		this.id_food = id_food;
	}
	public int getId_Hotel() {
		return id_Hotel;
	}
	public void setId_Hotel(int id_Hotel) {
		this.id_Hotel = id_Hotel;
	}
	public int getId_Attraction() {
		return id_Attraction;
	}
	public void setId_Attraction(int id_Attraction) {
		this.id_Attraction = id_Attraction;
	}
	public int getId_session() {
		return id_session;
	}
	public void setId_session(int id_session) {
		this.id_session = id_session;
	}
	
	
}
