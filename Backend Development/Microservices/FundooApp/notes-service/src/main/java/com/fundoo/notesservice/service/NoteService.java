package com.fundoo.notesservice.service;



import com.fundoo.notesservice.dto.request.NoteRequest;
import com.fundoo.notesservice.dto.response.NoteResponse;

import java.util.List;

public interface NoteService {

    NoteResponse createNote(NoteRequest request);

    List<NoteResponse> getAllNotes();

    NoteResponse updateNote(Long noteId,
                            NoteRequest request);

    NoteResponse deleteNote(Long noteId);

    NoteResponse pinNote(Long noteId);

    NoteResponse archiveNote(Long noteId);

    NoteResponse restoreNote(Long noteId);

    List<NoteResponse> getPinnedNotes();

    List<NoteResponse> getArchivedNotes();

    List<NoteResponse> getTrashNotes();

    List<NoteResponse> searchNotes(String keyword);

    NoteResponse addLabelToNote(Long noteId, Long labelId);

    NoteResponse removeLabelFromNote(Long noteId, Long labelId);

    void permanentDelete(Long noteId);

    NoteResponse getNote(Long noteId);
}