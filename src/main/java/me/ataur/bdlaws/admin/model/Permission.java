package me.ataur.bdlaws.admin.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Permission {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @JsonView(DataTablesOutput.View.class)
    private int id;

    @Getter
    @Setter
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    @JsonView(DataTablesOutput.View.class)
    private String views;

    @Getter
    @Setter
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    @JsonView(DataTablesOutput.View.class)
    private String createViews;

    @Getter
    @Setter
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    @JsonView(DataTablesOutput.View.class)
    private String editChecks;

    @Getter
    @Setter
    @JsonView(DataTablesOutput.View.class)
    private String username;

    @Getter
    @Setter
    @Column(name = "created")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonView(DataTablesOutput.View.class)
    private Date created;

    @Getter
    @Setter
    @JsonView(DataTablesOutput.View.class)
    private String title;

}
