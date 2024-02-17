package projecte.entities;

/**
 *
 * @author pc-raul
 */

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(length = 255)
    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

    @OneToMany(mappedBy = "idUser", targetEntity = Posts.class, cascade = CascadeType.ALL)
    private Collection<Posts> posts;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "User_Event", 
        joinColumns = { @JoinColumn(name = "user_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "event_id") }
    )
    private Set<Event> events;

    @OneToMany(mappedBy = "iduser", targetEntity = Message.class, cascade = CascadeType.ALL)
    private Collection<Message> messages;

    @ManyToMany(mappedBy = "idUser")
    private Collection<Follow> follow;

    @OneToMany(mappedBy = "user", targetEntity = Participant.class, cascade = CascadeType.ALL)
    private Collection<Participant> participants;

    @Column(nullable = false)
    private String password;

    @Column
    private Integer phone;

    

    public User() {}
    
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }


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
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Collection<Posts> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Posts> posts) {
        this.posts = posts;
    }

     public Set<Event> getEvents() {
        return events;
    }

    // Cambia el tipo del parámetro
    public void setEvents(Set<Event> events) {
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

