package me.ataur.bdlaws.util.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Amran
 */
@Entity
@Table(name = "message")
@Data
public class MessageResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "locale")
    private String locale;

    @Column(name = "value")
    private String value;

}
