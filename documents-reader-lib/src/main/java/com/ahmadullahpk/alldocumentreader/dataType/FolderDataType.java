// FolderDataType.java
package com.ahmadullahpk.alldocumentreader.dataType;

import java.util.ArrayList;

public class FolderDataType {
    private String folderName;
    private ArrayList<ImageParcelable> images;
    
    public FolderDataType(String folderName) {
        this.folderName = folderName;
        this.images = new ArrayList<ImageParcelable>();
    }
    
    // Getter methods
    public String getFolderName() {
        return folderName;
    }
    
    public ArrayList<ImageParcelable> getImages() {
        return images;
    }
    
    // Setter methods
    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
    
    public void setImages(ArrayList<ImageParcelable> images) {
        this.images = images;
    }
    
    // Utility methods for working with images list
    public void addImage(ImageParcelable image) {
        this.images.add(image);
    }
    
    public void removeImage(ImageParcelable image) {
        this.images.remove(image);
    }
    
    public void clearImages() {
        this.images.clear();
    }
    
    public int getImageCount() {
        return this.images.size();
    }
}