package com.fundoonotesapp.fundoo.service;

import com.fundoonotesapp.fundoo.dto.request.NoteRequest;
import com.fundoonotesapp.fundoo.dto.response.NoteResponse;

import java.util.List;

public interface NoteService {

    NoteResponse createNote(NoteRequest request);

    List<NoteResponse> getAllNotes();

    NoteResponse updateNote(Long noteId,
                            NoteRequest request);

    void deleteNote(Long noteId);

    NoteResponse pinNote(Long noteId);

    NoteResponse archiveNote(Long noteId);

    NoteResponse restoreNote(Long noteId);

    List<NoteResponse> getPinnedNotes();

    List<NoteResponse> getArchivedNotes();

    List<NoteResponse> getTrashNotes();

    List<NoteResponse> searchNotes(String keyword);
}