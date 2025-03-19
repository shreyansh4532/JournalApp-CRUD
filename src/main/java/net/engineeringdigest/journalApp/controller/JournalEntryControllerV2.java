package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll() {
        return journalEntryService.getAllEntries();
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry) {
        myEntry.setLocalDateTime(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry myEntry) {
        JournalEntry oldEntry =  journalEntryService.getEntryById(id).orElse(null);
        if(oldEntry != null) {
            oldEntry.setTitle(myEntry.getTitle() != null && !myEntry.getTitle().isEmpty() ? myEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(myEntry.getContent() != null && !myEntry.getContent().isEmpty() ? myEntry.getContent() : oldEntry.getContent());
        }
        journalEntryService.saveEntry(oldEntry);
        return oldEntry;
    }

    @GetMapping("/id/{id}")
    public JournalEntry getEntry(@PathVariable ObjectId id) {
        return journalEntryService.getEntryById(id).orElse(null);
    }

    @DeleteMapping("/id/{id}")
    public boolean deleteEntry(@PathVariable ObjectId id) {
        journalEntryService.deleteEntry(id);
        return true;
    }
}
