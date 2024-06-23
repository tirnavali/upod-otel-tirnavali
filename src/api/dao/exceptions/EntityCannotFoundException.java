package api.dao.exceptions;

public class EntityCannotFoundException extends Exception{
    public EntityCannotFoundException(String entityName, String id) {
        super(entityName + " cannot be found with id: " + id);
    }
}
