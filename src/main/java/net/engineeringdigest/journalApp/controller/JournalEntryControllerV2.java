package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<JournalEntry> entries = journalEntryService.getAllEntries();
        if(entries != null && !entries.isEmpty()) {
            return new ResponseEntity<>(entries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
        try {
            myEntry.setLocalDateTime(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry myEntry) {
        JournalEntry oldEntry =  journalEntryService.getEntryById(id).orElse(null);
        if(oldEntry != null) {
            oldEntry.setTitle(myEntry.getTitle() != null && !myEntry.getTitle().isEmpty() ? myEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(myEntry.getContent() != null && !myEntry.getContent().isEmpty() ? myEntry.getContent() : oldEntry.getContent());
            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getEntry(@PathVariable ObjectId id) {
        Optional<JournalEntry> journalEntry = journalEntryService.getEntryById(id);
        if(journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId id) {
        journalEntryService.deleteEntry(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
