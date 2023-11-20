/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecte.entities;

/**
 *
 * @author pc-raul
 */
import java.util.ArrayList;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "follow")
public class Follow {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column
    private Integer followers;

    @Column
    private Integer following;

    @ManyToMany
    @JoinTable(
        name = "User_Follow", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "follow_id")
    )
    private Collection<User> idUser;

    public Follow() {
        this.idUser = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Integer getFollowing() {
        return following;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }

    public Collection<User> getIdUser() {
        return idUser;
    }

    public void setIdUser(Collection<User> idUser) {
        this.idUser = idUser;
    }

    public void addFollower(User follower) {
        if (!this.idUser.contains(follower)) {
            this.idUser.add(follower);
            this.followers++;
        }
    }

    public void removeFollower(User follower) {
        if (this.idUser.contains(follower)) {
            this.idUser.remove(follower);
            this.followers--;
        }
    }

    public void addFollowing(User following) {
        if (!this.idUser.contains(following)) {
            this.idUser.add(following);
            this.following++;
        }
    }

    public void removeFollowing(User following) {
        if (this.idUser.contains(following)) {
            this.idUser.remove(following);
            this.following--;
        }
    }

    public void addIdUser(User idUser) {
        if (!this.idUser.contains(idUser)) {
            this.idUser.add(idUser);
        }
    }

    public void removeIdUser(User idUser) {
        this.idUser.remove(idUser);
    }
}
