package ibf2022.assessment.paf.batch3.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class OrderDetail {
    private String orderId;
    private LocalDateTime date;
    private int breweryId;
    private List<Order> orders;
    
    public OrderDetail() {
    }

    
    public OrderDetail(String orderId, LocalDateTime date, int breweryId, List<Order> orders) {
        this.orderId = orderId;
        this.date = date;
        this.breweryId = breweryId;
        this.orders = orders;
    }

    public OrderDetail(LocalDateTime date, int breweryId, List<Order> orders) {
        this.date = date;
        this.breweryId = breweryId;
        this.orders = orders;
    }


    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public int getBreweryId() {
        return breweryId;
    }
    public void setBreweryId(int breweryId) {
        this.breweryId = breweryId;
    }
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


    @Override
    public String toString() {
        return "OrderDetail [orderId=" + orderId + ", date=" + date + ", breweryId=" + breweryId + ", orders=" + orders
                + "]";
    }

    public JsonObject toJson(){
        JsonArrayBuilder jab = Json.createArrayBuilder();

        for(Order o : this.getOrders()){
            jab.add(o.toJson());
        }
        return Json.createObjectBuilder()
                    .add("orderId", this.getOrderId())
                    .add("date", LocalDateTime.now().toString())
                    .add("breweryId", this.getBreweryId())
                    .add("orders", jab)
                    .build();
    }
    
}
