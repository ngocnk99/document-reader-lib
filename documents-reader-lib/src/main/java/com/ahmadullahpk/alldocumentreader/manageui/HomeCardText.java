package com.ahmadullahpk.alldocumentreader.manageui;

import java.util.Objects;

public class HomeCardText {
    private final String sectionTitle;
    private final String cardTitle;
    private final String cardSubtitle;
    private final String cardSubPrimaryText;

    public HomeCardText(String sectionTitle, String cardTitle, String cardSubtitle, String cardSubPrimaryText) {
        this.sectionTitle = sectionTitle;
        this.cardTitle = cardTitle;
        this.cardSubtitle = cardSubtitle;
        this.cardSubPrimaryText = cardSubPrimaryText;
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public String getCardSubtitle() {
        return cardSubtitle;
    }

    public String getCardSubPrimaryText() {
        return cardSubPrimaryText;
    }

    @Override
    public String toString() {
        return "HomeCardText{" +
                "sectionTitle='" + sectionTitle + '\'' +
                ", cardTitle='" + cardTitle + '\'' +
                ", cardSubtitle='" + cardSubtitle + '\'' +
                ", cardSubPrimaryText='" + cardSubPrimaryText + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = (sectionTitle != null ? sectionTitle.hashCode() : 0);
        result = 31 * result + (cardTitle != null ? cardTitle.hashCode() : 0);
        result = 31 * result + (cardSubtitle != null ? cardSubtitle.hashCode() : 0);
        result = 31 * result + (cardSubPrimaryText != null ? cardSubPrimaryText.hashCode() : 0);
        return result;
    }
}
