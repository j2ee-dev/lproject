package me.ataur.bdlaws.website.model;


        import lombok.Data;
        import me.ataur.bdlaws.admin.model.*;

        import javax.persistence.*;

/**
 * @author Amran
 */
@Entity
@Table(name = "vw_search")
@Data
public class AdvancedSearch {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String title;
    private String description;
    private String link;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "actId")
    private Act actId;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "partId")
    private ActPart partId;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "chapterId")
    private ActChapter chapterId;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "sectionId")
    private ActSection sectionId;
    public AdvancedSearch(){

    }
}
