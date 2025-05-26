
package com.example.aiwriting.repository;

import com.example.aiwriting.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
