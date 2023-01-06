package dev.model;

public class HighScore {
    private int id;
    private String initials;
    private int point;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint() {
        this.point = point;
    }
    public HighScore(){}

    public HighScore(int id, String initials, int point) {
        this.id = id;
        this.initials = initials;
        this.point = point;
    }
    @Override
    public String toString(){
        return "\n{" +
                "Id: "+ id +
                ",Initials: " + initials +
                "point: " + point +
                "}";
    }
}
