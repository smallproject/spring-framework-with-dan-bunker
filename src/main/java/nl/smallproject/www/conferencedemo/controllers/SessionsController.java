package nl.smallproject.www.conferencedemo.controllers;

import nl.smallproject.www.conferencedemo.models.Session;
import nl.smallproject.www.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//proper http return status
//import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {
    @Autowired
    private SessionRepository sessionsRepository;

    @GetMapping
    public List<Session> list() {
        return sessionsRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id) {
//        return sessionsRepository.getOne(id);
        //getOne functionality is already depreciated
        //getReferenceById seems similar but needs to be tested
        //seems to work pretty similar
        return sessionsRepository.getReferenceById(id);
    }

//    returns standard spring status mapping 200
    @PostMapping
//    proper http return status
//    @ResponseStatus(HttpStatus.CREATED)
    public Session create(@RequestBody final Session session) {
        return sessionsRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //Also need to check for the children records before deleting.
        sessionsRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session) {
//        because this is a PUT, we expect all attributes to be passed in. A PATCH would only need what
        //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
        Session existingSession = sessionsRepository.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionsRepository.saveAndFlush(existingSession);
    }
}
