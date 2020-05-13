package io.yadnyesh.libraryeventsconsumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.yadnyesh.libraryeventsconsumer.entity.LibraryEvent;
import io.yadnyesh.libraryeventsconsumer.jpa.LibraryEventsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LibraryEventsService {
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	private LibraryEventsRepository libraryEventsRepository;
	
	public void processLibraryEvent(ConsumerRecord<Integer, String> consumerRecord) throws JsonProcessingException {
		LibraryEvent libraryEvent = objectMapper.readValue(consumerRecord.value(), LibraryEvent.class);
		log.info("libraryEvent : {} ", libraryEvent);
		
		switch(libraryEvent.getLibraryEventType()){
			case NEW:
				save(libraryEvent);
				break;
			case UPDATE:
				break;
			default:
				log.info("Invalid Library Event Type");
		}
	}
	
	private void save(LibraryEvent libraryEvent) {
		libraryEvent.getBook().setLibraryEvent(libraryEvent);
		libraryEventsRepository.save(libraryEvent);
		log.info("Saved - Created: {}", libraryEvent);
	}
}
