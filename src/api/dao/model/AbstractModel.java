package api.dao.model;

public abstract class AbstractModel<T> {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
