package sample.Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;


    public MemberDAO() {
        try {
            String url = "jdbc:mysql://localhost:3306/FXLibrary";
            String userID = "root";
            String userPass = "ehdgh3333";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, userID, userPass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int join(MemberDTO memberDTO) {
        String SQL = "INSERT INTO member VALUES(?, ?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, memberDTO.getMemberID());
            pstmt.setString(2, memberDTO.getMemberPassword());
            pstmt.setInt(3, memberDTO.getMemberAge());
            pstmt.setString(4, memberDTO.getMemberGender());
            pstmt.setString(5, memberDTO.getMemberEmail());

            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public int checkRedundantID(String memberID) {
        String SQL = "SELECT * FROM member WHERE memberID = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, memberID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return 1;
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -2;
    }

    public int login(MemberDTO memberDTO) {
        String SQL = "SELECT * FROM member WHERE memberID = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, memberDTO.getMemberID());

            rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getString("memberPassword").equals(memberDTO.getMemberPassword())) {
                    return 1; // 로그인 성공
                } else {
                    return 0; // 비밀번호 오류
                }
            }
            return -1; // 없는 아이디

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -2; // 데이터베이스 에러
    }
}
