package com.bank.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "branch")
@NoArgsConstructor
@AllArgsConstructor

public class BranchEntity implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Long branchID;

    @Column(name = "branch_name", length = 100, nullable = false, unique = true)
    private String branchName;

    @Column(name = "branch_postcode", length = 30, nullable = false)
    private String branchPostCode;
    
    @Column(name = "creation_date", nullable = false)
	private LocalDateTime creationDate;
    
    // Set creationDate to current time if it's null before persisting
 	@PrePersist
 	protected void onCreate() {
 		if (this.creationDate == null) {
 			this.creationDate = LocalDateTime.now();
 		}
 	}

}
