package api.service.contract;

import api.dao.model.User;

public interface UserService extends GenericService<User>{
    boolean checkSignIn(String email, String password);
}
