package com.zzh.h2test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
public class H2Controller {
    @GetMapping("h2/test")
    public String test() throws Exception {

//        Connection conn1 = DriverManager.getConnection("jdbc:h2:mem:test", "sa", "sa");
//        ResultSet rs1 = conn1.createStatement().executeQuery(
//                "select * from USERS where id = 1 limit 1");

        testDatabase("jdbc:h2:mem:test");
        return "test";
    }

    private void testDatabase(String url) throws SQLException {
        Connection connection = DriverManager.getConnection(url);
        Statement s = connection.createStatement();
        try {
            s.execute("DROP TABLE PERSON");
        } catch (SQLException sqle) {
            System.out.println("Table not found, not dropping");
        }
        s.execute("CREATE TABLE PERSON (ID INT PRIMARY KEY, FIRSTNAME VARCHAR(64), LASTNAME VARCHAR(64))");
        s.execute("INSERT INTO PERSON (ID, FIRSTNAME, LASTNAME) VALUES (1, 'ss', 'bb')");
        s.execute("INSERT INTO PERSON (ID, FIRSTNAME, LASTNAME) VALUES (2, '33', '66')");
        PreparedStatement ps = connection.prepareStatement("select * from PERSON");
        ResultSet r = ps.executeQuery();
        while (r.next()) {
            System.out.println("data?");
            String firstName = r.getString("FIRSTNAME");
            String secondName = r.getString("LASTNAME");
        }
        r.close();
        ps.close();
        s.close();
        connection.close();
    }
}
