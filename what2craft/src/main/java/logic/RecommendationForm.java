package logic;

public class RecommendationForm {
	
	private ItemCombined forItem;
	
	private boolean isRecommended;
	
	private double profitability;
	private int profit;
	
	private int recommendedAmount;
	
	public boolean isRecommended() {
		return isRecommended;
	}
	
	public RecommendationForm(ItemCombined forItem) {
		this.forItem = forItem;
	}
	
	public void setStatus(boolean status) {
		this.isRecommended = status;
	}
	
	public void setProfitFactors(int profit, double profitability) {
		this.profit = profit;
		this.profitability = profitability;
	}
	
	public void setAmountToCraft(int amount) {
		recommendedAmount = amount;
	}
	
}
