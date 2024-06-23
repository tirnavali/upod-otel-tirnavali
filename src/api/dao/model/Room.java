package api.dao.model;

import java.util.Objects;

public class Room extends AbstractModel{
    private String name;
    private long otelId;
    private int capacity;
    private double dailyPrice;
    private String extraSpeciality;
    private long commonRoomSpecialityId;

    public Room() {
    }

    public Room(String name, long otelId, int capacity, double dailyPrice, String extraSpeciality, long commonRoomSpecialityId) {
        this.name = name;
        this.otelId = otelId;
        this.capacity = capacity;
        this.dailyPrice = dailyPrice;
        this.extraSpeciality = extraSpeciality;
        this.commonRoomSpecialityId = commonRoomSpecialityId;
    }

    public Room(long id, String name, long otelId, int capacity, double dailyPrice, String extraSpeciality, long commonRoomSpecialityId) {
        super.setId(id);
        this.name = name;
        this.otelId = otelId;
        this.capacity = capacity;
        this.dailyPrice = dailyPrice;
        this.extraSpeciality = extraSpeciality;
        this.commonRoomSpecialityId = commonRoomSpecialityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOtelId() {
        return otelId;
    }

    public void setOtelId(int otelId) {
        this.otelId = otelId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public String getExtraSpeciality() {
        return extraSpeciality;
    }

    public void setExtraSpeciality(String extraSpeciality) {
        this.extraSpeciality = extraSpeciality;
    }

    public long getCommonRoomSpecialityId() {
        return commonRoomSpecialityId;
    }

    public void setCommonRoomSpecialityId(long commonRoomSpecialityId) {
        this.commonRoomSpecialityId = commonRoomSpecialityId;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + super.getId() + '\'' +
                "name='" + name + '\'' +
                ", otelId=" + otelId +
                ", capacity=" + capacity +
                ", dailyPrice=" + dailyPrice +
                ", extraSpeciality='" + extraSpeciality + '\'' +
                ", commonRoomSpecialityId=" + commonRoomSpecialityId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return otelId == room.otelId && Objects.equals(name, room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, otelId);
    }
}
