package app.mapin.service;

import app.mapin.database.DbClass;
import app.mapin.model.Floor;
import app.mapin.model.WayPoint;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WayPointService {


    public static final String WAYPOINTS_TABLE = "waypoints";
    public static final String WAYPOINTS_WAYPOINT_ID_COLUMN = "waypointid";
    public static final String WAYPOINTS_FLOOR_PLAN_ID_COLUMN = "floorplanid";
    public static final String WAYPOINTS_LAT_COLUMN = "waypointLat";
    public static final String WAYPOINTS_LNG_COLUMN = "waypointLng";

    public List<WayPoint> getAllWayPoints(String floorPlanId) {

        List<WayPoint> wayPoints = new ArrayList<>();

//        String sql = "select * " +
//                "from " + FloorService.FLOOR_PLAN_TABLE
//                +" INNER JOIN " + WayPointService.WAYPOINTS_TABLE +
//                " ON " + FloorService.FLOOR_PLAN_TABLE + "." + FloorService.FLOOR_ID_COLUMN +
//                " = " + WAYPOINTS_TABLE + "." + WAYPOINTS_FLOOR_PLAN_ID_COLUMN + ";"
//                ;

        String sql = "select * " +
                "from " + WAYPOINTS_TABLE +
                " where " + WAYPOINTS_FLOOR_PLAN_ID_COLUMN +
                " = ?";
        // create a new connection from MySQLJDBCUtil
        try (Connection conn = DbClass.getConnection()) {


            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, floorPlanId);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                WayPoint wayPoint = new WayPoint();
                wayPoint.setWayPointId(rs.getString(WAYPOINTS_WAYPOINT_ID_COLUMN));
                wayPoint.setWayPointLat(rs.getDouble(WAYPOINTS_LAT_COLUMN));
                wayPoint.setWayPointLng(rs.getDouble(WAYPOINTS_LNG_COLUMN));
                wayPoints.add(wayPoint);
            }

            rs.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return wayPoints;
    }

    public WayPoint addWaypoint(String floorPlanId, WayPoint wayPoint) {
        String sql = "INSERT INTO " + WAYPOINTS_TABLE +
                " VALUES (?,?,?,?);";

        String selectSql = "select *" +
                " from " + WAYPOINTS_TABLE +
                " where " + WAYPOINTS_WAYPOINT_ID_COLUMN  +
                " = " + wayPoint.getWayPointId();
        try (Connection conn = DbClass.getConnection()) {



            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, wayPoint.getWayPointId());
            preparedStatement.setString(2, floorPlanId);
            preparedStatement.setDouble(3, wayPoint.getWayPointLat());
            preparedStatement.setDouble(4, wayPoint.getWayPointLng());
            preparedStatement.executeUpdate();

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(selectSql);

            while(rs.next()) {
                WayPoint resultWaypoint = new WayPoint();
                resultWaypoint.setWayPointId(rs.getString(WAYPOINTS_WAYPOINT_ID_COLUMN));
                resultWaypoint.setWayPointLat(rs.getDouble(WAYPOINTS_LAT_COLUMN));
                resultWaypoint.setWayPointLng(rs.getDouble(WAYPOINTS_LNG_COLUMN));
            }

            rs.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return wayPoint;
    }

    public WayPoint updateWayPoint(String floorPlanId,WayPoint wayPoint) {
        String updateSql = "UPDATE " + WAYPOINTS_TABLE +
                " SET "
                + WAYPOINTS_LAT_COLUMN + " = " + wayPoint.getWayPointLat() + ","
                + WAYPOINTS_LNG_COLUMN + " = " + wayPoint.getWayPointLng()
                + " WHERE "
                + WAYPOINTS_WAYPOINT_ID_COLUMN + " = " +  wayPoint.getWayPointId();

        String selectSql = "SELECT * "
                + " from " + WAYPOINTS_TABLE
                + " WHERE " + WAYPOINTS_WAYPOINT_ID_COLUMN
                + " = " + wayPoint.getWayPointId();


        try (Connection conn = DbClass.getConnection()) {

            Statement statement = conn.createStatement();
            statement.executeUpdate(updateSql);

            Statement selectStatement = conn.createStatement();
            ResultSet rs = selectStatement.executeQuery(selectSql);

            while(rs.next()) {
                WayPoint resultWaypoint = new WayPoint();
                resultWaypoint.setWayPointId(rs.getString(WAYPOINTS_WAYPOINT_ID_COLUMN));
                resultWaypoint.setWayPointLat(rs.getDouble(WAYPOINTS_LAT_COLUMN));
                resultWaypoint.setWayPointLng(rs.getDouble(WAYPOINTS_LNG_COLUMN));
            }

            rs.close();
            statement.close();
            selectStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return wayPoint;
    }

    public int removeWayPoint(String floorplanid,
                               String waypointid) {
        String deleteSql = "DELETE " +
                " FROM " + WAYPOINTS_TABLE +
                " WHERE " + WAYPOINTS_WAYPOINT_ID_COLUMN + " = ?";

        try (Connection conn = DbClass.getConnection()) {

           PreparedStatement preparedStatement = conn.prepareStatement(deleteSql);
           preparedStatement.setString(1, waypointid);

           preparedStatement.executeUpdate();
           conn.close();
           preparedStatement.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        }

        return 200;
    }
}
