/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author sofian
 */
public class DiscussionGroup {

    private String title;
    private String description;
    
    public DiscussionGroup()
    {
        
    }

        public DiscussionGroup(String title, String description)
    {
        this.title = title;
        this.description = description;        
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
        
        
}
