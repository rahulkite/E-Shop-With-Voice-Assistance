package com.example.priya.finalprojectsdp;

public class Blog {
    private String Name,Image;

    public Blog()
    {  }

    public Blog(String name,String image)
    {

        Name = name;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
