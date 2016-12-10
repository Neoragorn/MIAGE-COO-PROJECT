/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Persistence.UserDiscussionGroupVirtualProxy;

/**
 *
 * @author sofian
 */
public class DiscussionGroup {

    private int idDiscussion;
    private String title;
    private String description;
    private UserDiscussionGroupVirtualProxy members;

    public DiscussionGroup(int id, String title, String description) {
        this.idDiscussion = id;
        this.title = title;
        this.description = description;
        this.members = new UserDiscussionGroupVirtualProxy(id);
    }

    public DiscussionGroup() {

    }

    public DiscussionGroup(String title, String description) {
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

    public int getIdDiscussion() {
        return idDiscussion;
    }

    public void setIdDiscussion(int idDiscussion) {
        this.idDiscussion = idDiscussion;
    }

    public UserDiscussionGroupVirtualProxy getMembers() {
        return members;
    }

    public void setMembers(UserDiscussionGroupVirtualProxy members) {
        this.members = members;
    }

}
