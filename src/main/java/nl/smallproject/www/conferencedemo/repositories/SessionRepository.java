package nl.smallproject.www.conferencedemo.repositories;

import nl.smallproject.www.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
