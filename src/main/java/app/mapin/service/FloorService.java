package app.mapin.service;

import app.mapin.database.DbClass;
import app.mapin.model.Floor;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FloorService {

    public static final String FLOOR_PLAN_TABLE = "floorplans";
    public static final String FLOOR_ID_COLUMN =  "floorid";

    public List<Floor> getAllFloors() {
        List<Floor> floors = new ArrayList<>();


        String sql = "select * from floorplans";

        // create a new connection from MySQLJDBCUtil
        try (Connection conn = DbClass.getConnection()) {

            // print out a message
            System.out.println(String.format("Connected to database %s "
                    + "successfully.", conn.getCatalog()));

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                Floor floor = new Floor();
                floor.setId(rs.getString("floorplanid"));
                floor.setLocation(rs.getString("location"));
                floor.setFloorNumber(rs.getInt("floornumber"));
                floors.add(floor);
            }

            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return floors;
    }

    public Floor getFloor(String floorPlanId) {
        Floor floor = new Floor();
        String sql = "select * from floorplans where floorplanid = ?";


        // create a new connection from MySQLJDBCUtil
        try (Connection conn = DbClass.getConnection()) {

            // print out a message
            System.out.println(String.format("Connected to database %s "
                    + "successfully.", conn.getCatalog()));


            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, floorPlanId);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                floor.setId(rs.getString("floorplanid"));
                floor.setLocation(rs.getString("location"));
                floor.setFloorNumber(rs.getInt("floornumber"));
            }

            rs.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return floor;
    }

}
