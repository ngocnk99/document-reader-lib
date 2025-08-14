// LangDataType.java
package com.ahmadullahpk.alldocumentreader.dataType;

import java.util.ArrayList;

public class LangDataType {
    private String name;
    private String localLanguageName;
    private String code;
    private boolean isSelected;
    private String flag;
    
    public LangDataType(String name, String localLanguageName, String code, boolean isSelected, String flag) {
        this.name = name;
        this.localLanguageName = localLanguageName;
        this.code = code;
        this.isSelected = isSelected;
        this.flag = flag;
    }
    
    // Getter methods
    public String getName() {
        return name;
    }
    
    public String getLocalLanguageName() {
        return localLanguageName;
    }
    
    public String getCode() {
        return code;
    }
    
    public boolean isSelected() {
        return isSelected;
    }
    
    public String getFlag() {
        return flag;
    }
    
    // Setter methods
    public void setName(String name) {
        this.name = name;
    }
    
    public void setLocalLanguageName(String localLanguageName) {
        this.localLanguageName = localLanguageName;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }
    
    public void setFlag(String flag) {
        this.flag = flag;
    }
    
    public static ArrayList<LangDataType> getLangDataTypes() {
        ArrayList<LangDataType> appLanguages = new ArrayList<LangDataType>();
        appLanguages.add(new LangDataType("English", "Default", "en", false, "uk.png"));
        appLanguages.add(new LangDataType("Arabic", "العربية", "ar", false, "uae.png"));
        appLanguages.add(new LangDataType("Spanish", "Español", "es", false, "spain.png"));
        appLanguages.add(new LangDataType("Russian", "Русский", "ru", false, "russia.png"));
        appLanguages.add(new LangDataType("Chinese", "中文", "zh", false, "china.png"));
        appLanguages.add(new LangDataType("India", "हिंदी", "hi", false, "india.png"));
        appLanguages.add(new LangDataType("French", "français", "fr", false, "abc.png"));
        appLanguages.add(new LangDataType("Bengali", "বাংলা", "bn", false, "bangladesh.png"));
        appLanguages.add(new LangDataType("Indonesian", "Indonesia", "id", false, "indonesia.png"));
        appLanguages.add(new LangDataType("Japanese", "日本語", "ja", false, "japan.png"));
        appLanguages.add(new LangDataType("Malay", "Malay", "ms", false, "malaysia.png"));
        appLanguages.add(new LangDataType("Portuguese", "português", "pt", false, "portugal.png"));
        appLanguages.add(new LangDataType("Turkish", "Türkçe ", "tr", false, "turkey.png"));
        appLanguages.add(new LangDataType("Urdu", "اُردُو ", "ur", false, "pakistan.png"));
        appLanguages.add(new LangDataType("German", "German", "de", false, "germany.png"));
        appLanguages.add(new LangDataType("Korean", "韓國語 ", "ko", false, "korean.png"));
        return appLanguages;
    }
}