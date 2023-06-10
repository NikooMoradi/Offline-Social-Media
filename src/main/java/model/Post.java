package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import util.PostType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private Date createDate;

    @Column(name = "text" ,columnDefinition = "varchar(255) not null")
    private String text;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] postImage;

    @JoinColumn(nullable = false)
    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostType type;

}
