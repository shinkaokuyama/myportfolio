package models;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "processes")
@NamedQueries({
    @NamedQuery(
            name = "getAllProcesses",
            query = "SELECT r FROM Processes AS r ORDER BY .id DESC" 
            ),
    @NamedQuery(
            name = "getProcessesCount",
            query = "SELECT COUNT(r) FROM Processes AS r"
            )
    
})

@Entity
public class Processes {
    @Id
    @Column(name="id")
    @

}
