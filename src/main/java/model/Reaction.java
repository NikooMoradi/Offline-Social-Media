package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import util.ReactionType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Post reactionTo;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Comment liked;

    private Date createDate;

    @JoinColumn(nullable = false)
    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private ReactionType type;

}
