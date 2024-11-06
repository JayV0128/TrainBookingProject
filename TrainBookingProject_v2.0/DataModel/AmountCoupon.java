package DataModel;
import java.time.LocalDate;


public class AmountCoupon implements Coupon {
	private double amount;
	private String couponType;
	private String couponCode;
	private LocalDate expiryDate;
	
	

	public AmountCoupon(double amount, String couponType, String couponCode, LocalDate expiryDate) {
		this.amount = amount;
		this.couponType = couponType;
		this.couponCode = couponCode;
		this.expiryDate = expiryDate;
	}

	public double getDiscount(double totalAmount) {
		return amount;
		
	}

	public String getCouponType() {
		return couponType;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public boolean isExpired() {
		return LocalDate.now().isAfter(expiryDate);
	}
}