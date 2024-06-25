package api.dao.model;

import java.util.Objects;

public class ReservationRoomService extends AbstractModel<ReservationRoomService>{
    private long roomServiceId;
    private long reservationId;
    private double fixedServicePrice;

    public ReservationRoomService() {
    }

    public ReservationRoomService(long roomServiceId, long reservationId, double fixedServicePrice) {
        this.roomServiceId = roomServiceId;
        this.reservationId = reservationId;
        this.fixedServicePrice = fixedServicePrice;
    }

    public ReservationRoomService(long id, long roomServiceId, long reservationId, double fixedServicePrice) {
        super.setId(id);
        this.roomServiceId = roomServiceId;
        this.reservationId = reservationId;
        this.fixedServicePrice = fixedServicePrice;
    }

    public void setId(int id){
        super.setId(id);
    }
    public long getRoomServiceId() {
        return roomServiceId;
    }

    public void setRoomServiceId(long roomServiceId) {
        this.roomServiceId = roomServiceId;
    }
    public void setRoomServiceId(int roomServiceId) {
        this.roomServiceId = roomServiceId;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public double getFixedServicePrice() {
        return fixedServicePrice;
    }

    public void setFixedServicePrice(double fixedServicePrice) {
        this.fixedServicePrice = fixedServicePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationRoomService that = (ReservationRoomService) o;
        return roomServiceId == that.roomServiceId && reservationId == that.reservationId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomServiceId, reservationId);
    }

    @Override
    public String toString() {
        return "ReservationRoomService{" +
                "id='" + super.getId() + '\'' +
                "roomServiceId=" + roomServiceId +
                ", reservationId=" + reservationId +
                ", fixedServicePrice=" + fixedServicePrice +
                '}';
    }
}
