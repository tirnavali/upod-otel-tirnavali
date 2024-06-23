package api.dao.model;

import java.util.Objects;

public class CommonRoomSpeciality extends AbstractModel{
    private String speciality;

    public CommonRoomSpeciality(long id, String speciality) {
        super.setId(id);
        this.speciality = speciality;
    }
    public CommonRoomSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "CommonRoomSpeciality{" +
                "id='" + super.getId() + '\'' +
                "speciality='" + speciality + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonRoomSpeciality that = (CommonRoomSpeciality) o;
        return Objects.equals(speciality, that.speciality) && Objects.equals(super.getId(), ((CommonRoomSpeciality) o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(speciality, super.getId());
    }
}
