package app.mapin.service;

import app.mapin.database.DbClass;
import app.mapin.model.FloorPlan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FloorPlanService {

    public static final String FLOOR_PLAN_TABLE = "floorplans";
    public static final String FLOOR_ID_COLUMN =  "floorid";

    public List<FloorPlan> getAllFloors() {
        List<FloorPlan> floorPlans = new ArrayList<>();


        String sql = "select * from floorplans";

        // create a new connection from MySQLJDBCUtil
        try (Connection conn = DbClass.getConnection()) {

            // print out a message
            System.out.println(String.format("Connected to database %s "
                    + "successfully.", conn.getCatalog()));

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                FloorPlan floorPlan = new FloorPlan();
                floorPlan.setId(rs.getString("floorplanid"));
                floorPlan.setLocation(rs.getString("location"));
                floorPlan.setFloorNumber(rs.getInt("floornumber"));
                floorPlans.add(floorPlan);
            }

            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return floorPlans;
    }

    public FloorPlan getFloor(String floorPlanId) {
        FloorPlan floorPlan = new FloorPlan();
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
                floorPlan.setId(rs.getString("floorplanid"));
                floorPlan.setLocation(rs.getString("location"));
                floorPlan.setFloorNumber(rs.getInt("floornumber"));
            }

            rs.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return floorPlan;
    }

}
