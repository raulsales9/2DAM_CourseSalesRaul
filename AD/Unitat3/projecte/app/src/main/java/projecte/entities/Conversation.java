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
import java.util.*;

@Entity
@Table(name = "Conversation")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToMany(mappedBy = "conversation", targetEntity = Participant.class)
    private Set<Participant> participants;

    @OneToOne(targetEntity = Message.class)
    @JoinColumn(name = "last_message_id", referencedColumnName = "id")
    private Message lastMessage;

    @ManyToMany(targetEntity = User.class)
    @JoinTable(name = "conversation_user",
        joinColumns = @JoinColumn(name = "conversation_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    @OneToMany(mappedBy = "conversation", targetEntity = Message.class)
    private Set<Message> messages;

    public Conversation() {
        this.users = new HashSet<>();
        this.messages = new HashSet<>();
    }

    public Integer getId() {
        return this.id;
    }

    // ... other getters and setters ...
}

