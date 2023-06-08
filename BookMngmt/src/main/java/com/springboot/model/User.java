package com.springboot.model;



import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.*;

@Entity
@Table(name="Userspring")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Size(max=20)
	@Column(name="User_Name")
    private String name;
	

        
}
