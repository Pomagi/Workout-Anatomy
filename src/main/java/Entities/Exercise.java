package Entities;

public class Exercise {

    private int id;
    private String name;
    private String video;

    public Exercise(int id, String name, String video) {
        this.id = id;
        this.name = name;
        this.video = video;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getVideo(){
        return video;
    }

    @Override
    public String toString() {
        return name;
    }

}
