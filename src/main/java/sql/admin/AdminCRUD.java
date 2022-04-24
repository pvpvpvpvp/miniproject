package sql.admin;

import vo.Admin;

public class AdminCRUD implements AdminSql{

    private Admin admin;
    public AdminCRUD(){};

    public AdminCRUD(Admin admin){
        this.admin=admin;
    }

    @Override
    public String adminInsert() {
        String statemt="INSERT INTO admin(admin_idx, id, pw, grade, nickname)\r\n"
                + "  VALUES(admin_idx_seq.NEXTVAL, '"+admin.getId()+"', '"+admin.getPw0()+"',"+admin.getGrade()+",'"+admin.getNickname()+"')";
        return statemt;
    }

    @Override
    public String adminUpdate() {
        return null;
    }

    @Override
    public String adminDelete() {
        return null;
    }

    @Override
    public String adminSelect() {
        return null;
    }
}
