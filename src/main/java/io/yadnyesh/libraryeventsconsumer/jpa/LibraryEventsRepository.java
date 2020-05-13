package io.yadnyesh.libraryeventsconsumer.jpa;

import io.yadnyesh.libraryeventsconsumer.entity.LibraryEvent;
import org.springframework.data.repository.CrudRepository;

public interface LibraryEventsRepository extends CrudRepository<LibraryEvent, Integer> {}
