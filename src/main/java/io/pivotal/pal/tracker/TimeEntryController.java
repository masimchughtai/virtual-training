package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/time-entries")
public class TimeEntryController {
    @Autowired
    TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity<>(timeEntry, HttpStatus.CREATED);
    }

    @GetMapping(value="/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable("timeEntryId") long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
        if(null==timeEntry){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(timeEntry, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntryList = timeEntryRepository.list();
        return new ResponseEntity<>(timeEntryList, HttpStatus.OK);
    }

    @PutMapping(value="/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable("timeEntryId") long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {
        TimeEntry updatedTimeEntry = timeEntryRepository.update(timeEntryId, timeEntryToUpdate);
        if(null==updatedTimeEntry){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
    }

    @DeleteMapping(value="{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable("timeEntryId") long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
