package com.yash.yotaapi.domain;
 
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yash.yotaapi.constraints.CompareDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
 
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
 
/**
* Represents a Training entity.
* This class defines the structure and attributes of a Training.
* @author raghav.muchhal
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@SQLDelete(sql = "UPDATE Training SET Status = true WHERE id=?")
@Table(name = "Training_Managment")
@Entity
@CompareDate(message = "End date must be greater than Start date")
public class Training {
 
    /** Unique identifier for the training. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
 
    /** Name used to identify the training. */
    @Size(min = 3, max = 25, message = "Training identifier name should be between 3 to 25 characters only")
    private String trainingIdentifierName;
 
    /** Name of the training. */
    @NotEmpty(message = "Training name is mandatory")
    @Column(unique = false, nullable = false)
    private String trainingName;
 
    /** Description of the training. */
    @NotBlank(message = "Training description is mandatory")
    private String trainingDescription;
 
    /** Start date of the training. */
    @NotNull(message = "Start date cannot be Null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date startDate;
 
    /** End date of the training. */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "End date cannot be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date endDate;
 
    /** Actual start date of the training. */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date actualStartDate;
 
    /** Actual end date of the training. */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date actualEndDate;
 
    /** Date when the training was created. */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @CreatedDate
    private Date createdAt;
 
    /** Date when the training was last updated. */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @LastModifiedDate
    private Date updatedAt;
 
    /** Status of the training. */
    private boolean status = Boolean.FALSE;
 
    /** Transient field for storing user name. */
    @Transient
    private String userName;
 
    /** Pre-persist action to set the created date. */
    @PrePersist
    public void onCreate() {
        this.createdAt = new Date();
    }
 
    /** Pre-update action to set the updated date. */
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = new Date();
    }
 
    /** Set of nominations associated with the training. */
    @ManyToMany(mappedBy = "trainings")
    private Set<Nomination> nominations = new HashSet<>();
}