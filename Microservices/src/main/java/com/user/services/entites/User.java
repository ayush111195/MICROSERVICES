package com.user.services.entites;

import java.util.ArrayList;
import java.util.List;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="micro_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	@Id
private String userId;	

private String name;	

private String about;	

private String email;

@Transient  // agr hum kise chej ko data base main nhi save karna chahte h uske leye use karte h 
private List<Rating> ratings=new ArrayList<>();

}
