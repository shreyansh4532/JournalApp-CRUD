package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    JournalEntryRepo journalEntryRepo;

//  CREATE
    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepo.save(journalEntry);
    }

//  GET ALL
    public List<JournalEntry> getAllEntries() {
        return journalEntryRepo.findAll();
    }

//  GET ONE
    public Optional<JournalEntry> getEntryById(ObjectId id) {
        return journalEntryRepo.findById(id);
    }

//  UPDATE
//    public JournalEntry updateEntry() {
//
//    }

//  DELETE
    public void deleteEntry(ObjectId id) {
        journalEntryRepo.deleteById(id);
    }
}
