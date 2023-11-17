package projecte.entities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

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



}

