package com.fundoonotesapp.fundoo.service.impl;

import com.fundoonotesapp.fundoo.dto.request.NoteRequest;
import com.fundoonotesapp.fundoo.dto.response.NoteResponse;
import com.fundoonotesapp.fundoo.entity.Note;
import com.fundoonotesapp.fundoo.entity.User;
import com.fundoonotesapp.fundoo.exception.NoteNotFoundException;
import com.fundoonotesapp.fundoo.repository.NoteRepository;
import com.fundoonotesapp.fundoo.repository.UserRepository;
import com.fundoonotesapp.fundoo.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    private User getLoggedInUser() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email).orElseThrow();
    }

    @Override
    public NoteResponse createNote(
            NoteRequest request) {

        User user = getLoggedInUser();

        Note note = Note.builder().title(request.getTitle()).description(request.getDescription()).user(user).build();

        noteRepository.save(note);

        return mapToResponse(note);
    }

    @Override
    public List<NoteResponse> getAllNotes() {

        User user = getLoggedInUser();

        return noteRepository.findByUserAndTrashedFalse(user).stream().map(this::mapToResponse).toList();
    }

    @Override
    public NoteResponse updateNote(
            Long noteId,
            NoteRequest request) {

        Note note = noteRepository.findById(noteId).orElseThrow(() -> new NoteNotFoundException("Note not found"));

        note.setTitle(request.getTitle());
        note.setDescription(
                request.getDescription());

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

    private NoteResponse mapToResponse(
            Note note) {

        return NoteResponse.builder()
                .id(note.getId())
                .title(note.getTitle())
                .description(note.getDescription())
                .pinned(note.isPinned())
                .archived(note.isArchived())
                .trashed(note.isTrashed())
                .build();
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

        User user = getLoggedInUser();

        return noteRepository
                .findByUserAndPinnedTrueAndTrashedFalse(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    @Override
    public List<NoteResponse> getArchivedNotes() {

        User user = getLoggedInUser();

        return noteRepository
                .findByUserAndArchivedTrueAndTrashedFalse(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    @Override
    public List<NoteResponse> getTrashNotes() {

        User user = getLoggedInUser();

        return noteRepository
                .findByUserAndTrashedTrue(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    @Override
    public List<NoteResponse> searchNotes(String keyword) {

        User user = getLoggedInUser();

        List<Note> titleResults =
                noteRepository
                        .findByUserAndTitleContainingIgnoreCase(
                                user,
                                keyword);

        List<Note> descriptionResults =
                noteRepository
                        .findByUserAndDescriptionContainingIgnoreCase(
                                user,
                                keyword);

        return java.util.stream.Stream
                .concat(
                        titleResults.stream(),
                        descriptionResults.stream())
                .distinct()
                .map(this::mapToResponse)
                .toList();
    }
}