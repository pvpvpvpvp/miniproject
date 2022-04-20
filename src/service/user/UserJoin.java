package service.user;

import domain.user.UserJoinData;

public interface UserJoin {
    void userJoin();

    boolean userIdIsDuplicate(UserJoinData userJoinData);

}
