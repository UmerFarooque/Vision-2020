package domain;

import java.util.Date;

public class Idea {
    private int id;
    private String description;
    User postedBy;
    private String status;
    private Date postedDate;

    public Idea(int id, String description, User postedBy, String status, Date postedDate) {
        this.id = id;
        this.description = description;
        this.postedBy = postedBy;
        this.status = status;
        this.postedDate = postedDate;
    }
    
    public Idea() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }
    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }
    
}
