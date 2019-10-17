package com.example.shailee.camo;

public class ModelItem {

    String commonName, scientificName, genus, familyname, benifits;
    byte[] images;

    public ModelItem(String commonName, String scientificName, String genus, String familyname, String benifits, byte[] images) {
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.genus = genus;
        this.familyname = familyname;
        this.benifits = benifits;
        this.images = images;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public String getBenifits() {
        return benifits;
    }

    public void setBenifits(String benifits) {
        this.benifits = benifits;
    }

    public byte[] getImages() {
        return images;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }
}
