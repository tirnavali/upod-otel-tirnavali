package api.dao.model;

import java.util.Date;
import java.util.Objects;

public class Reservation extends AbstractModel<Reservation>{
    private long customerId;
    private long roomId;
    private double fixedPrice;
    private Date entranceDate;
    private Date leaveDate;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation() {
    }

    public Reservation(long id, long customerId, long roomId, double fixedPrice, Date entranceDate, Date leaveDate, Date checkInDate, Date checkOutDate) {
        super.setId(id);
        this.customerId = customerId;
        this.roomId = roomId;
        this.fixedPrice = fixedPrice;
        this.entranceDate = entranceDate;
        this.leaveDate = leaveDate;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    public Reservation(long id, long customerId, long roomId, double fixedPrice, Date entranceDate, Date leaveDate) {
        super.setId(id);
        this.customerId = customerId;
        this.roomId = roomId;
        this.fixedPrice = fixedPrice;
        this.entranceDate = entranceDate;
        this.leaveDate = leaveDate;
    }

    public Reservation(long customerId, long roomId, double fixedPrice, Date entranceDate, Date leaveDate, Date checkInDate, Date checkOutDate) {
        this.customerId = customerId;
        this.roomId = roomId;
        this.fixedPrice = fixedPrice;
        this.entranceDate = entranceDate;
        this.leaveDate = leaveDate;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    public Reservation(long customerId, long roomId, double fixedPrice, Date entranceDate, Date leaveDate) {
        this.customerId = customerId;
        this.roomId = roomId;
        this.fixedPrice = fixedPrice;
        this.entranceDate = entranceDate;
        this.leaveDate = leaveDate;
    }

    public void setId(int id){
        super.setId(id);
    }
    public void setId(long id){
        super.setId(id);
    }

    public void setId(Integer id){
        super.setId(id);
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public double getFixedPrice() {
        return fixedPrice;
    }

    public void setFixedPrice(double fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    public Date getEntranceDate() {
        return entranceDate;
    }

    public void setEntranceDate(Date entranceDate) {
        this.entranceDate = entranceDate;
    }

    public void setEntranceDate(java.sql.Date entranceDate) {
        this.entranceDate = entranceDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }
    public void setLeaveDate(java.sql.Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }
    public void setCheckInDate(java.sql.Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    public void setCheckOutDate(java.sql.Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return customerId == that.customerId && roomId == that.roomId && Objects.equals(entranceDate, that.entranceDate) && Objects.equals(leaveDate, that.leaveDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, roomId, entranceDate, leaveDate);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + super.getId() + '\'' +
                "customerId=" + customerId +
                ", roomId=" + roomId +
                ", fixedPrice=" + fixedPrice +
                ", entranceDate=" + entranceDate +
                ", leaveDate=" + leaveDate +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}
