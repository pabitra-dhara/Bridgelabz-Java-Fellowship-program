package com.fundoo.notesservice.service.impl;

import com.fundoo.notesservice.dto.request.NoteRequest;
import com.fundoo.notesservice.dto.response.NoteResponse;
import com.fundoo.notesservice.entity.Note;
import com.fundoo.notesservice.exception.NoteNotFoundException;
import com.fundoo.notesservice.repository.NoteRepository;
import com.fundoo.notesservice.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Override
    public NoteResponse createNote(NoteRequest request) {

        Note note = Note.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .userId(1L)
                .pinned(false)
                .archived(false)
                .trashed(false)
                .build();

        noteRepository.save(note);

        return mapToResponse(note);
    }

    @Override
    public List<NoteResponse> getAllNotes() {

        return noteRepository
                .findByUserIdAndTrashedFalse(1L)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public NoteResponse updateNote(
            Long noteId,
            NoteRequest request) {

        Note note = noteRepository
                .findById(noteId)
                .orElseThrow(() ->
                        new NoteNotFoundException(
                                "Note not found"));

        note.setTitle(request.getTitle());
        note.setDescription(request.getDescription());

        noteRepository.save(note);

        return mapToResponse(note);
    }

    @Override
    public void deleteNote(Long noteId) {

        Note note = noteRepository
                .findById(noteId)
                .orElseThrow(() ->
                        new NoteNotFoundException(
                                "Note not found"));

        note.setTrashed(true);

        noteRepository.save(note);
    }

    @Override
    public NoteResponse pinNote(Long noteId) {

        Note note = noteRepository
                .findById(noteId)
                .orElseThrow(() ->
                        new NoteNotFoundException(
                                "Note not found"));

        note.setPinned(!note.isPinned());

        noteRepository.save(note);

        return mapToResponse(note);
    }

    @Override
    public NoteResponse archiveNote(Long noteId) {

        Note note = noteRepository
                .findById(noteId)
                .orElseThrow(() ->
                        new NoteNotFoundException(
                                "Note not found"));

        note.setArchived(!note.isArchived());

        noteRepository.save(note);

        return mapToResponse(note);
    }

    @Override
    public NoteResponse restoreNote(Long noteId) {

        Note note = noteRepository
                .findById(noteId)
                .orElseThrow(() ->
                        new NoteNotFoundException(
                                "Note not found"));

        note.setTrashed(false);

        noteRepository.save(note);

        return mapToResponse(note);
    }

    @Override
    public List<NoteResponse> getPinnedNotes() {

        return noteRepository
                .findByUserIdAndPinnedTrueAndTrashedFalse(1L)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<NoteResponse> getArchivedNotes() {

        return noteRepository
                .findByUserIdAndArchivedTrueAndTrashedFalse(1L)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<NoteResponse> getTrashNotes() {

        return noteRepository
                .findByUserIdAndTrashedTrue(1L)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<NoteResponse> searchNotes(String keyword) {

        List<Note> titleResults =
                noteRepository
                        .findByUserIdAndTitleContainingIgnoreCase(
                                1L,
                                keyword);

        List<Note> descriptionResults =
                noteRepository
                        .findByUserIdAndDescriptionContainingIgnoreCase(
                                1L,
                                keyword);

        return java.util.stream.Stream
                .concat(
                        titleResults.stream(),
                        descriptionResults.stream())
                .distinct()
                .map(this::mapToResponse)
                .toList();
    }

    private NoteResponse mapToResponse(Note note) {

        return NoteResponse.builder()
                .id(note.getId())
                .title(note.getTitle())
                .description(note.getDescription())
                .pinned(note.isPinned())
                .archived(note.isArchived())
                .trashed(note.isTrashed())
                .build();
    }
}