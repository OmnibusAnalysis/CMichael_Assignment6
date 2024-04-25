package  com.coderscampus.assignment6;

public class CarSaleData {

    private String month;
    private String year;
    private Integer sales;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "CarSaleData{" +
                "month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", sales=" + sales +
                '}';
    }
}
