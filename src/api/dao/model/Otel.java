package api.dao.model;

import java.util.Objects;

public class Otel extends AbstractModel<Otel>{
    private String name;

    public Otel() {
    }

    public Otel(String name){
        this.name = name;
    }
    public Otel(long id, String name) {
        super.setId(id);
        this.name = name;
    }

    public long getId() {
        return super.getId();
    }


    public void setId(long id) {
        super.setId(id);
    }
    public void setId(int id) {
        super.setId(id);
    }
    public void setId(Integer id) {
        super.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Otel{" +
                "id='" + super.getId() + '\'' +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Otel otel = (Otel) o;
        return super.getId() == otel.getId() && Objects.equals(name, otel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId(), name);
    }


}
