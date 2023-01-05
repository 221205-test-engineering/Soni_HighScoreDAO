package dev.app;

import dev.daos.ScoreDAO;
import dev.model.HighScore;
import dev.util.ConnectionUtil;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

import java.sql.Connection;
import java.util.List;
public class HighScoreApp {
    public static void main(String[] args){
        Connection conn = ConnectionUtil.getConnectionUtil().getConnection();
        if(conn != null){
            System.out.println("Connection Successful");
        }else System.out.println("Connection unsuccessful");

        ScoreDAO score = new ScoreDAO();
        Javalin app = Javalin.create();

        app.get("/scores", ctx ->{
           List<HighScore> hs  = score.getAll();
           ctx.status(200);
           ctx.json(score);
        });

        app.get("/scores/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            HighScore hs = score.getById(id);
            if (hs != null) {
                ctx.status(200);
                ctx.json(hs);
            } else {
                ctx.status(HttpStatus.NOT_FOUND);
                ctx.result(" No " + id + "found.");
            }
        });
        app.start(8086);

    }

}
