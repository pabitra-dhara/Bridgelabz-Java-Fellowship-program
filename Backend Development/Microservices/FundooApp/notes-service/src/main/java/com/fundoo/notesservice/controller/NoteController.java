package com.fundoo.notesservice.controller;

import com.fundoo.notesservice.dto.request.NoteRequest;
import com.fundoo.notesservice.dto.response.ApiResponse;
import com.fundoo.notesservice.dto.response.NoteResponse;
import com.fundoo.notesservice.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public ApiResponse<NoteResponse> createNote(
            @Valid @RequestBody NoteRequest request) {

        return new ApiResponse<>(
                "Note Created",
                noteService.createNote(request));
    }

    @GetMapping
    public ApiResponse<List<NoteResponse>> getNotes() {

        return new ApiResponse<>(
                "Success",
                noteService.getAllNotes());
    }

    @PutMapping("/{id}")
    public ApiResponse<NoteResponse> updateNote(
            @PathVariable Long id,
            @RequestBody NoteRequest request) {

        return new ApiResponse<>(
                "Updated",
                noteService.updateNote(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<NoteResponse> deleteNote(
            @PathVariable Long id) {

        return new ApiResponse<>(
                "Delete Successfully",
                noteService.deleteNote(id));
    }

    @PutMapping("/{id}/pin")
    public ApiResponse<NoteResponse> pinNote(
            @PathVariable Long id) {

        return new ApiResponse<>(
                "Pin Updated",
                noteService.pinNote(id));
    }
    @PutMapping("/{id}/archive")
    public ApiResponse<NoteResponse> archive(
            @PathVariable Long id) {

        return new ApiResponse<>(
                "Archive Updated",
                noteService.archiveNote(id));
    }
    @PutMapping("/{id}/restore")
    public ApiResponse<NoteResponse> restore(
            @PathVariable Long id) {

        return new ApiResponse<>(
                "Restored",
                noteService.restoreNote(id));
    }
    @GetMapping("/pinned")
    public ApiResponse<List<NoteResponse>>
    getPinnedNotes() {

        return new ApiResponse<>(
                "Success",
                noteService.getPinnedNotes());
    }
    @GetMapping("/archived")
    public ApiResponse<List<NoteResponse>>
    getArchivedNotes() {

        return new ApiResponse<>(
                "Success",
                noteService.getArchivedNotes());
    }
    @GetMapping("/trash")
    public ApiResponse<List<NoteResponse>>
    getTrashNotes() {

        return new ApiResponse<>(
                "Success",
                noteService.getTrashNotes());
    }
    @GetMapping("/search")
    public ApiResponse<List<NoteResponse>>
    searchNotes(
            @RequestParam String keyword) {

        return new ApiResponse<>(
                "Success",
                noteService.searchNotes(keyword));
    }
    @PostMapping("/{noteId}/labels/{labelId}")
    public ApiResponse<NoteResponse> addLabelToNote(
            @PathVariable Long noteId,
            @PathVariable Long labelId) {

        return new ApiResponse<>(
                "Label Added Successfully",
                noteService.addLabelToNote(noteId, labelId)
        );
    }
    @DeleteMapping("/{noteId}/labels/{labelId}")
    public ApiResponse<NoteResponse> removeLabelFromNote(
            @PathVariable Long noteId,
            @PathVariable Long labelId) {

        return new ApiResponse<>(
                "Label Removed Successfully",
                noteService.removeLabelFromNote(noteId, labelId)
        );
    }

    @DeleteMapping("/{id}/permanent")
    public ApiResponse<String> permanentDelete(
            @PathVariable Long id) {

        noteService.permanentDelete(id);

        return new ApiResponse<>(
                "Note Deleted Permanently",
                null);
    }

    @GetMapping("/{id}")
    public ApiResponse<NoteResponse> getNote(
            @PathVariable Long id) {

        return new ApiResponse<>(
                "Success",
                noteService.getNote(id));
    }
}
