package com.fundoonotesapp.fundoo.service.impl;

import com.fundoonotesapp.fundoo.dto.request.LabelRequest;
import com.fundoonotesapp.fundoo.dto.response.LabelResponse;
import com.fundoonotesapp.fundoo.entity.Label;
import com.fundoonotesapp.fundoo.entity.Note;
import com.fundoonotesapp.fundoo.entity.User;
import com.fundoonotesapp.fundoo.exception.LabelNotFoundException;
import com.fundoonotesapp.fundoo.repository.LabelRepository;
import com.fundoonotesapp.fundoo.repository.NoteRepository;
import com.fundoonotesapp.fundoo.repository.UserRepository;
import com.fundoonotesapp.fundoo.service.LabelService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    private User getLoggedInUser() {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return userRepository.findByEmail(email)
                .orElseThrow();
    }

    @Override
    public LabelResponse createLabel(
            LabelRequest request) {

        User user = getLoggedInUser();

        Label label = Label.builder()
                .name(request.getName())
                .user(user)
                .build();

        labelRepository.save(label);

        return map(label);
    }

    @Override
    public List<LabelResponse> getLabels() {

        User user = getLoggedInUser();

        return labelRepository
                .findByUser(user)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public LabelResponse updateLabel(
            Long labelId,
            LabelRequest request) {

        User user = getLoggedInUser();

        Label label =
                labelRepository
                        .findByIdAndUser(
                                labelId,
                                user)
                        .orElseThrow(() ->
                                new LabelNotFoundException(
                                        "Label not found"));

        label.setName(request.getName());

        labelRepository.save(label);

        return map(label);
    }

    @Override
    public void deleteLabel(Long labelId) {

        User user = getLoggedInUser();

        Label label =
                labelRepository
                        .findByIdAndUser(
                                labelId,
                                user)
                        .orElseThrow(() ->
                                new LabelNotFoundException(
                                        "Label not found"));

        labelRepository.delete(label);
    }

    @Override
    public void addLabelToNote(
            Long noteId,
            Long labelId) {

        User user = getLoggedInUser();

        Note note = noteRepository.findByIdAndUser(noteId, user).orElseThrow();

        Label label = labelRepository.findByIdAndUser(labelId, user).orElseThrow();

        note.getLabels().add(label);

        noteRepository.save(note);
    }

    @Override
    public void removeLabelFromNote(
            Long noteId,
            Long labelId) {

        User user = getLoggedInUser();

        Note note =
                noteRepository
                        .findByIdAndUser(
                                noteId,
                                user)
                        .orElseThrow();

        Label label =
                labelRepository
                        .findByIdAndUser(
                                labelId,
                                user)
                        .orElseThrow();

        note.getLabels().remove(label);

        noteRepository.save(note);
    }

    private LabelResponse map(Label label) {

        return LabelResponse.builder()
                .id(label.getId())
                .name(label.getName())
                .build();
    }
}