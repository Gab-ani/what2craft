package logic;

public class ItemCombined {						// representation of a concrete item with known tier, ench.level and quality FROM the ItemBasic base
	
	private static final double[] baseFames = new double[]{0, 1.5, 7.5, 22.5, 90, 270, 645, 1395};
	
	private ItemBasic base;
	
	private int tier;
	private int enchantment;
	private int quality;
	
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
	
	
	public int getItemValue() {				// IV is ingame term used in many formulas, based on constants and materials amount
		int itemValue = 0;
		itemValue += base.getMaterialsAmount() * Math.pow(2, tier + enchantment);
		if(base.requiresArtifact()) {
			itemValue += base.getBaseArtifactValue() * Math.pow(2, tier - 4);
		}
		return itemValue;
	}
	
	public int fameForCrafting() {
		return (int)(base.getMaterialsAmount() * baseFames[tier - 1] * Math.pow(2, enchantment)) ;
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
	
	public boolean containsArtifact() {
		return base.requiresArtifact();
	}
	
	public int getQuality() {
		return quality;
	}
	
	public String[] getRecipe() {
		return base.getRecipe();
	}
	
	public String craftBranch() {
		return base.getCraftBranch();
	}

	public String craftNode() {
		return base.getCraftNode();
	}
	
	public ItemBasic getBase() {
		return base;
	}
//	public String artifactName() {
//		return base.getArtifactName();
//	}

	
}
