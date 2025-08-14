// HomeDrawerItemParser.java
package com.ahmadullahpk.alldocumentreader.dataType;

public class HomeDrawerItemParser {
    private final int icon;
    private final String name;
    private boolean isNotificationPresent = false;
    private String tagName = null;
    
    public HomeDrawerItemParser(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }
    
    public HomeDrawerItemParser(String name, int icon, int notificationAvailable) {
        boolean notificationPresent = false;
        this.name = name;
        this.icon = icon;
        if (notificationAvailable > 0) {
            notificationPresent = true;
        }
        this.isNotificationPresent = notificationPresent;
    }
    
    // Getter methods
    public int getIcon() {
        return icon;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isNotificationPresent() {
        return isNotificationPresent;
    }
    
    public String getTagName() {
        return tagName;
    }
    
    // Setter methods (only for fields that can be modified)
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    
    // Note: isNotificationPresent has private setter in Kotlin, 
    // so no public setter is provided here
}