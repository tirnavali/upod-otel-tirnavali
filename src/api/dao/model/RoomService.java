package api.dao.model;

import java.util.Objects;

public class RoomService extends AbstractModel<RoomService>{
    private String name;
    private double price;

    public RoomService() {
    }

    public RoomService(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public RoomService(long id, String name, double price) {
        super.setId(id);
        this.name = name;
        this.price = price;
    }

    public void setId(int id){
        super.setId(id);
    }
    public void setId(Integer id){
        super.setId(id);
    }
    public void setId(long id){
        super.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "RoomService{" +
                "id=" + super.getId() +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomService that = (RoomService) o;
        return Double.compare(price, that.price) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
