package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "processes")
@NamedQueries({
    @NamedQuery(
            name = "getAllProcesses",
            query = "SELECT r FROM Process AS r ORDER BY r.id DESC"
            ),
    @NamedQuery(
            name = "getProcessesCount",
            query = "SELECT COUNT(r) FROM Process AS r"
            ),

})

@Entity
public class Process {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "completed_date", nullable = false)
    private Date completed_date;

    @Column(name = "process_name", length = 50, nullable = false)
    private String process_name;

    @Lob
    @Column(name = "message", length = 300, nullable = false)
    private String message;




    public Integer getId(){
        return id;
    }
    public void setId (Integer id){
        this.id = id;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public Date getCompleted_date(){
        return completed_date;
    }
    public void setCompleted_date(Date completed_date){
        this.completed_date = completed_date;
    }

    public String getProcess_name(){
        return process_name;
    }

    public void setProcess_name(String process_name){
        this.process_name = process_name;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

}
