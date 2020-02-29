package cn.tedu.pojo;

import java.io.Serializable;

public class Music implements Serializable {
    private int id;
    private String name;
    private String picture;
    private String writer;
    private String adds;

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", writer='" + writer + '\'' +
                ", adds='" + adds + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getAdds() {
        return adds;
    }

    public void setAdds(String adds) {
        this.adds = adds;
    }

    public Music() {
    }

    public Music(int id, String name, String picture, String writer, String adds) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.writer = writer;
        this.adds = adds;
    }
}
