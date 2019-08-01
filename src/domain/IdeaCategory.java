package domain;

public class IdeaCategory {
    Idea idea;
    Category category;

    public IdeaCategory(Idea idea, Category category) {
        this.idea = idea;
        this.category = category;
    }

    public Idea getIdea() {
        return idea;
    }

    public void setIdea(Idea idea) {
        this.idea = idea;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    
    
}
