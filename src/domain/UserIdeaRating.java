package domain;

import java.util.Date;

public class UserIdeaRating {
    public Idea idea;
    public Rating rating;
    public User user;
    private Date ratedDate;

    public UserIdeaRating(Idea idea, Rating rating, User user, Date ratedDate) {
        this.idea = idea;
        this.rating = rating;
        this.user = user;
        this.ratedDate = ratedDate;
    }

    public Idea getIdea() {
        return idea;
    }

    public void setIdea(Idea idea) {
        this.idea = idea;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getRatedDate() {
        return ratedDate;
    }

    public void setRatedDate(Date ratedDate) {
        this.ratedDate = ratedDate;
    }

    
}
