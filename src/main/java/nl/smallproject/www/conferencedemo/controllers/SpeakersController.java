package nl.smallproject.www.conferencedemo.controllers;

import nl.smallproject.www.conferencedemo.models.Speaker;
import nl.smallproject.www.conferencedemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//proper http return status
//import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("api/v1/speakers")
public class SpeakerController {
    @Autowired
    private SpeakerRepository speakersRepository;

    @GetMapping
    public List<Speaker> list() {
        return speakersRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id) {
        return speakersRepository.getOne(id);
    }

//    returns standard spring status mapping 200
    @PostMapping
//    proper http return status
//    @ResponseStatus(HttpStatus.CREATED)
    public Speaker create(@RequestBody final Speaker speaker) {
        return speakersRepository.saveAndFlush(speaker);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //Also need to check for the children records before deleting.
        speakersRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
//        because this is a PUT, we expect all attributes to be passed in. A PATCH would only need what
        //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
        Speaker existingSpeaker = speakersRepository.getOne(id);
        BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
        return speakersRepository.saveAndFlush(existingSpeaker);
    }
}
