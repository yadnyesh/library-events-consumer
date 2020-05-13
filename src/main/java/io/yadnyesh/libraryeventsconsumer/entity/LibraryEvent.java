package io.yadnyesh.libraryeventsconsumer.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class LibraryEvent {
	@Id
	@GeneratedValue
	private Integer libraryEventId;

	@OneToOne (mappedBy = "libraryEvent", cascade = {CascadeType.ALL})
	@ToString.Exclude
	private Book book;
	
	@Enumerated(EnumType.STRING)
	private LibraryEventType libraryEventType;
	
}
