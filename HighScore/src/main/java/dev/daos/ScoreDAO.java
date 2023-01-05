package dev.daos;

import dev.daos.GenericDAO;
import dev.model.HighScore;
import dev.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreDAO implements GenericDAO<HighScore> {
    public static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    @Override
    public HighScore add(HighScore o) {
        return null;
    }

    @Override
    public HighScore getById(int id){
        String sql = "select * from highscore where id = ?";
        try(Connection conn = cu.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new HighScore(
                        rs.getInt("id"),
                        rs.getString("initials"),
                        rs.getInt("point")
                );
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public  List<HighScore> getAll(){
        String sql = "select * from highscore";
        List<HighScore> hs = new ArrayList<>();
        try(Connection conn = cu.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                hs.add(new HighScore(
                        rs.getInt("id"),
                        rs.getString("initials"),
                        rs.getInt("points")));
            }
            return hs;

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(HighScore o) {

    }

    @Override
    public void delete(int id) {

    }
}