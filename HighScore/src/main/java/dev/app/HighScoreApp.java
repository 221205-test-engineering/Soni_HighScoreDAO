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
        // connecting to database
        Connection conn = ConnectionUtil.getConnectionUtil().getConnection();
        // checking database connection
        if(conn != null){
            System.out.println("Connection Successful");
        }else System.out.println("Connection unsuccessful");

        // creating object of ScoreDAO
        ScoreDAO scoreDAO = new ScoreDAO();
        Javalin app = Javalin.create();

        // Get all scores
        app.get("/scores", ctx ->{
           List<HighScore> hs  = scoreDAO.getAll();
           ctx.status(200);
           ctx.json(scoreDAO);
        });

        app.get("/scores/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            HighScore hs = scoreDAO.getById(id);
            if (hs != null) {
                ctx.status(200);
                ctx.json(hs);
            } else {
                ctx.status(HttpStatus.NOT_FOUND);
                ctx.result(" No " + id + "found.");
            }
        });
        app.put("/scores/{id}", ctx ->{
           int id = Integer.parseInt(ctx.pathParam("id"));
           HighScore editScore = ctx.bodyAsClass(HighScore.class);
           if(scoreDAO.getById(id) != null){
               editScore.setId(id);
               scoreDAO.update(editScore);
               ctx.status(HttpStatus.CREATED);
               ctx.result("Editing database was successful for the id "+id);
           }else{
               ctx.status(HttpStatus.NOT_FOUND);
               ctx.result(id+" not found");
           }
        });

        app.post("/scores", ctx ->{
           HighScore addScore = ctx.bodyAsClass(HighScore.class);
           HighScore insertScore = scoreDAO.insert(addScore);
           if(insertScore != null){
               ctx.status(HttpStatus.CREATED);
               ctx.json(insertScore);
           }else{
               ctx.status(HttpStatus.NOT_FOUND);
               ctx.result("Something went wrong");
           }
        });

        app.start(8086);

    }

}
