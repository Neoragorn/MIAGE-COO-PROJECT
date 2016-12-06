package Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Models.User;

public class AdminBdd extends UserBdd {
	
    private static final Connection conn = PersistenceConnection.getInstance().getConn();

    public static void modifyUserPwd(User user, String pwd) throws SQLException {
        String req = " UPDATE User SET password = ? WHERE idUser = ? ";
        PreparedStatement pss = conn.prepareStatement(req);
        pss.setString(1, pwd);
        pss.setInt(2, user.getIdUser());
        pss.executeUpdate();
    }

}
