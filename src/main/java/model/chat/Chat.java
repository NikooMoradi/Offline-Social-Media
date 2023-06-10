package model.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.User;
import util.ChatType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ChatType type;

    private String chatName;

    private Date createDate;

    @JoinColumn(nullable = false)
    @OneToOne
    private User owner;

    @Override
    public String toString() {
        return chatName;
    }
}
