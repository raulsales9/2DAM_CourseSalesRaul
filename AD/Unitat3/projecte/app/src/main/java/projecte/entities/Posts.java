/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecte.entities;

/**
 *
 * @author pc-raul
 */
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Post")
public class Posts {

    @Id
    @GeneratedValue
    @Column(name = "idPost")
    private Integer idPost;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column
    private Integer likes;

    @Column(length = 255, nullable = true)
    private String text;

    @Column
    private Boolean isSubmitted;

    @Column(length = 255)
    private String image;

    @Column(length = 50, nullable = true)
    private String title;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User idUser;

    // Constructores, getters y setters

    public Posts() {}
    
    public Posts(String title) {
        this.title = title;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getIsSubmitted() {
        return isSubmitted;
    }

    public void setIsSubmitted(Boolean isSubmitted) {
        this.isSubmitted = isSubmitted;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }
}
