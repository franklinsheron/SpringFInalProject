package com.springboot.model;


import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.*;
@Entity
@Table(name="bookspring")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book
{
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @Size(max=20)
	 @Column(name="Title")
	 private String title;
	 
	 @Size(max=20)
	 @Column(name="Author_Name")
	 private String aname;

	 @ManyToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name="borrowing_user_id")
     private User user;
	 	 

}