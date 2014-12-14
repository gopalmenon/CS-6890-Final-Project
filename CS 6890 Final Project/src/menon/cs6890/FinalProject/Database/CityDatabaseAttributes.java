package menon.cs6890.FinalProject.Database;

public class CityDatabaseAttributes {
	
	private String cityName;
	private String cityCodeType;
	
	public CityDatabaseAttributes(String cityName, String cityCodeType) {
		this.cityName = cityName;
		this.cityCodeType = cityCodeType;
	}
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityCodeType() {
		return cityCodeType;
	}
	public void setCityCodeType(String cityCodeType) {
		this.cityCodeType = cityCodeType;
	}
	
}
