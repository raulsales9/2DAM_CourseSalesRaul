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

@Entity
@Table(name = "Participant")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "iduser")  // Este nombre debe coincidir con el nombre de la columna en la tabla Participant
    private User user; 

    @ManyToOne(targetEntity = Conversation.class)
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    public Integer getId() {
        return this.id;
    }
}

