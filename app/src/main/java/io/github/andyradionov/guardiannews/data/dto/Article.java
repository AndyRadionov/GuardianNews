package io.github.andyradionov.guardiannews.data.dto;

import java.util.Date;


/**
 * Article dto class for gson deserialization
 *
 * @author Andrey Radionov
 */

public class Article {

    private String sectionName;
    private String webTitle;
    private String webUrl;
    private Date webPublicationDate;
    private Fields fields;

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public Date getWebPublicationDate() {
        return webPublicationDate;
    }

    public void setWebPublicationDate(Date webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }

    public boolean hasThumbNail() {
        return fields != null
                && getThumbnail() != null
                && !getThumbnail().isEmpty();
    }

    public String getThumbnail() {
        return fields.thumbnail;
    }

    public boolean hasTrailText() {
        return fields != null && getThumbnail() != null;
    }

    public String getTrailText() {
        return fields.trailText;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    private class Fields {
        String trailText;
        String thumbnail;
    }

    @Override
    public String toString() {
        return "Article{" +
                "sectionName='" + sectionName + '\'' +
                ", webTitle='" + webTitle + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", webPublicationDate=" + webPublicationDate +
                '}';
    }
}
