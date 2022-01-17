package me.ataur.bdlaws.website.model;


import lombok.Data;
import me.ataur.bdlaws.admin.model.Act;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Amran
 */
@Entity
@Table(name="vw_search")
@Data
public class Search implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String title;
    private String description;
    private String link;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "actId")
    private Act actId;
}
