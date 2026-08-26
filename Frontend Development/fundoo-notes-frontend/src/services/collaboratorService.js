import api from '../api/axios';

// Placeholder for your collaborator-service API.
// Replace paths with the exact endpoints from your backend.
export const collaboratorService = {
  add: (noteId, payload) => api.post(`/notes/${noteId}/collaborators`, payload),
  remove: (noteId, email) =>
    api.delete(`/notes/${noteId}/collaborators`, { params: { email } })
};