package service.userjoin;

import domain.user.UserJoinData;

public interface UserJoin {
    void join();
    boolean userIdIsDuplicate(UserJoinData userJoinData);

}
