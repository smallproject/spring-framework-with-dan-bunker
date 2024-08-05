package nl.smallproject.www.conferencedemo.repositories;

import nl.smallproject.www.conferencedemo.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
}
