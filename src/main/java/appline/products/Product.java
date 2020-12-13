package appline.products;

public  class Product {
    private String  name;
    private int     price;
    private int     warrantyPrice;
    private int     yearsOfWarranty;

    public Product() {
    }

    public Product(String name, int price, int yearsOfWarranty) {
        this.name = name;
        this.price = price;
//        this.warrantyPrice = warrantyPrice;
        this.yearsOfWarranty = yearsOfWarranty;
    }

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public int    getPrice() {
        return price;
    }
    public int    getWarrantyPrice() {
        return warrantyPrice;
    }
    public int    getYearsOfWarranty() {
        return yearsOfWarranty;
    }
    public void   setName(String name) {
        this.name = name;
    }
    public void   setPrice(int price) {
        this.price = price;
    }
    public void   setWarrantyPrice(int warrantyPrice) {
        this.warrantyPrice = warrantyPrice;
    }
    public void   setYearsOfWarranty(int yearsOfWarranty) {

        this.yearsOfWarranty = yearsOfWarranty;
    }
}
