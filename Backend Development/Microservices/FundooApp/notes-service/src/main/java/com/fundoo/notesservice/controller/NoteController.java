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
    public ApiResponse<String> deleteNote(
            @PathVariable Long id) {

        noteService.deleteNote(id);

        return new ApiResponse<>(
                "Deleted Successfully",
                null);
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
}
