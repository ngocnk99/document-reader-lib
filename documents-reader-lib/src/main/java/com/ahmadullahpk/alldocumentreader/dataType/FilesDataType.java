// FilesDataType.java
package com.ahmadullahpk.alldocumentreader.dataType;

public class FilesDataType {
    private String createdAt;
    private String fileContent;
    private String fileName;
    private int fileType;
    private int id;
    private String updatedAt;
    
    public FilesDataType() {
        this.createdAt = null;
        this.fileContent = null;
        this.fileName = null;
        this.fileType = 0;
        this.id = 0;
        this.updatedAt = null;
    }
    
    // Getter methods
    public String getCreatedAt() {
        return createdAt;
    }
    
    public String getFileContent() {
        return fileContent;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public int getFileType() {
        return fileType;
    }
    
    public int getId() {
        return id;
    }
    
    public String getUpdatedAt() {
        return updatedAt;
    }
    
    // Setter methods
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public void setFileType(int fileType) {
        this.fileType = fileType;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}