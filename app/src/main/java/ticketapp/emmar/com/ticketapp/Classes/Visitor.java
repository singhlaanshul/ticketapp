package ticketapp.emmar.com.ticketapp.Classes;


public class Visitor {

    private String aliasId;  //unique identifier
    private String ageCriteria;
    private String description;
    private String maxAge;
    private String minAge;
    private String price;
    private String ticketContenturl;

    public String getAliasId() {
        return aliasId;
    }

    public void setAliasId(String aliasId) {
        this.aliasId = aliasId;
    }

    public String getAgeCriteria() {
        return ageCriteria;
    }

    public void setAgeCriteria(String ageCriteria) {
        this.ageCriteria = ageCriteria;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(String maxAge) {
        this.maxAge = maxAge;
    }

    public String getMinAge() {
        return minAge;
    }

    public void setMinAge(String minAge) {
        this.minAge = minAge;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTicketContenturl() {
        return ticketContenturl;
    }

    public void setTicketContenturl(String ticketContenturl) {
        this.ticketContenturl = ticketContenturl;
    }
}
