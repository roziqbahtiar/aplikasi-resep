package com.example.d2a.aplikasiresep.data;

public class ResepModel {

    String title;
    String image;
    String desc;
    String bahan;
    String cara;


    public ResepModel() {
    }

    public ResepModel(String title, String desc, String bahan, String cara) {
        this.title = title;
        this.desc = desc;
        this.bahan = bahan;
        this.cara = cara;

    }

    public String getCara() {
        return cara;
    }

    public void setCara(String cara) {
        this.cara = cara;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }
}
