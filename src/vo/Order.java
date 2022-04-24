package vo;

public class Order {

    int number;
    String order_date;
    String cancle_date;
    String order_state;
    int order_price;

    public Order(int number, String order_date, int order_price) {
        this.number = number;
        this.order_date = order_date;
        this.order_price = order_price;
    }

    public Order(int number, String order_date, String cancle_date, String order_state, int order_price) {
        this.number = number;
        this.order_date = order_date;
        this.cancle_date = cancle_date;
        this.order_state = order_state;
        this.order_price = order_price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getCancle_date() {
        return cancle_date;
    }

    public void setCancle_date(String cancle_date) {
        this.cancle_date = cancle_date;
    }

    public String getOrder_state() {
        return order_state;
    }

    public void setOrder_state(String order_state) {
        this.order_state = order_state;
    }

    public int getOrder_price() {
        return order_price;
    }

    public void setOrder_price(int order_price) {
        this.order_price = order_price;
    }
}


//    order_idx NUMBER ,
//    order_date DATE NULL,
//        cancle_date DATE NULL,
//        order_state NUMBER NULL,
//        order_price NUMBER NULL,
//        PRIMARY KEY (order_idx));