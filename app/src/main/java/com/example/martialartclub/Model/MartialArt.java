package com.example.martialartclub.Model;

public class MartialArt {

    private int martialArtId;
    private String martialArtName;
    private double martialArtPrice;
    private String martialArtColor;



    public MartialArt(int id, String name, double price, String color){
        setMartialArtId(id);
        setMartialArtName(name);
        setMartialArtPrice(price);
        setMartialArtColor(color);


    }
    public int getMartialArtId() {
        return martialArtId;
    }

    public String getMartialArtName() {
        return martialArtName;
    }

    public double getMartialArtPrice() {
        return martialArtPrice;
    }

    public String getMartialArtColor() {
        return martialArtColor;
    }

    public void setMartialArtId(int martialArtId) {
        this.martialArtId = martialArtId;
    }

    public void setMartialArtName(String martialArtName) {
        this.martialArtName = martialArtName;
    }

    public void setMartialArtPrice(double martialArtPrice) {
        this.martialArtPrice = martialArtPrice;
    }

    public void setMartialArtColor(String martialArtColor) {
        this.martialArtColor = martialArtColor;
    }


    @Override
    public String toString(){
        return getMartialArtName() + "\n" + getMartialArtPrice() + "\n" + getMartialArtColor()+ "\n";
    }
}
