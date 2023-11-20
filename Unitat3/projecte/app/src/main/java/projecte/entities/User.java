package projecte.entities;

/**
 *
 * @author pc-raul
 */
import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(length = 255)
    private String email;

    @ElementCollection
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Collection<String> roles;

    @OneToMany(mappedBy = "idUser", targetEntity = Posts.class, cascade = CascadeType.ALL)
    private Collection<Posts> posts;

    @ManyToMany(mappedBy = "idUser")
    private Collection<Event> events;

    @OneToMany(mappedBy = "idUser", targetEntity = Message.class, cascade = CascadeType.ALL)
    private Collection<Message> messages;

    @ManyToMany(mappedBy = "idUser")
    private Collection<Follow> follow;

    @OneToMany(mappedBy = "iduser", targetEntity = Participant.class, cascade = CascadeType.ALL)
    private Collection<Participant> participants;

    @Column(nullable = false)
    private String password;

    @Column
    private Integer phone;

    public User() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
        public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }

    public Collection<Posts> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Posts> posts) {
        this.posts = posts;
    }

    public Collection<Event> getEvents() {
        return events;
    }

    public void setEvents(Collection<Event> events) {
        this.events = events;
    }

    public Collection<Message> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }

    public Collection<Follow> getFollow() {
        return follow;
    }

    public void setFollow(Collection<Follow> follow) {
        this.follow = follow;
    }

    public Collection<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(Collection<Participant> participants) {
        this.participants = participants;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }


}

