package server;

import vo.Member;
import vo.Order;

import java.sql.SQLException;

public interface Pratice {
    void reserve(Member member) throws SQLException;
    void cancle(Order order) throws SQLException;

    Member strat() throws SQLException;
}
