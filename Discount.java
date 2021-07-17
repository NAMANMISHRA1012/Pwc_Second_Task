package org.pwc.application.secondTask;

public class Discount {
	
	public double discount(String customerType, int amountSpend) {
		double discountAmount;
		int discount=0;
		if (customerType.compareTo("regular")== 0){
			if (amountSpend<= 4000) {
				discount=10;
				
			}
			else if (amountSpend> 4000 && amountSpend<8000) {
				discount=15;
				
			}
			else if (amountSpend> 8000 && amountSpend<12000) {
				discount=20;
				
			}
			else {
				discount=25;
			}
			
		}
		else if (customerType.compareTo("premium")== 0)
		{
			if (amountSpend<= 5000) {
				discount=0;
				
			}
			else if (amountSpend> 5000 && amountSpend<10000) {
				discount=10;
				
			}
			
			else {
				discount=20;
			}
			
		}
		else {
			System.out.println("Invalid customer type");
		}
		System.out.println(discount);
	discountAmount = (double)amountSpend*((double)discount/100);
	return discountAmount;

}
}