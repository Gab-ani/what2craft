package logic;

public class ItemCombined {						// representation of a concrete item with known tier, ench.level and quality FROM the ItemBasic base
	
	ItemBasic base;
	
	int tier;
	int enchantment;
	int quality;
	
	private ItemCombined(ItemBasic base) {					// privating constructor to force builder behaviour
		this.base = base;
	}
	
	public static ItemCombined forBase(ItemBasic base) {
		return new ItemCombined(base);
	}
	
	public ItemCombined forTier(int tier) {
		this.tier = tier;
		return this;
	}
	
	public ItemCombined withEnchantmentLevelOf(int chant) {
		this.enchantment = chant;
		return this;
	}
	
	public ItemCombined ofQuality(int quality) {
		this.quality = quality;
		return this;
	}
	
	@Override
	public String toString() {
		return this.getName()+ " " + this.getTier()+ " " + this.getChant()+ " " + this.getQuality();
	}
	
	public String getName() {
		return base.getName();
	}
	
	public String getRequestName() {
		return base.getRequestName();
	}
	
	public int getTier() {
		return tier;
	}
	
	public int getChant() {
		return enchantment;
	}
	
	public int getQuality() {
		return quality;
	}
	
}
