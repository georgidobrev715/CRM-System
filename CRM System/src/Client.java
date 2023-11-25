import com.fasterxml.jackson.annotation.JsonProperty;

public class Client {
    @JsonProperty("client_id")
    private int Id;

    @JsonProperty("client_name")
    private String name;

    @JsonProperty("client_companyname")
    private String companyName;


    @JsonProperty("client_industry")
    private String industry;


    @JsonProperty("client_revenue")
    private double revenue;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
}