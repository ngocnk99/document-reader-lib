// CategoryDataType.java
package com.ahmadullahpk.alldocumentreader.dataType;

public class CategoryDataType {
    private int image;
    private int title;
    private final int bg;
    private int index;
    private String description;
    
    public CategoryDataType(int image, int title, int bg, int index) {
        this.image = image;
        this.title = title;
        this.bg = bg;
        this.index = index;
        this.description = null;
    }
    
    // Getter methods
    public int getImage() {
        return image;
    }
    
    public int getTitle() {
        return title;
    }
    
    public int getBg() {
        return bg;
    }
    
    public int getIndex() {
        return index;
    }
    
    public String getDescription() {
        return description;
    }
    
    // Setter methods
    public void setImage(int image) {
        this.image = image;
    }
    
    public void setTitle(int title) {
        this.title = title;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}