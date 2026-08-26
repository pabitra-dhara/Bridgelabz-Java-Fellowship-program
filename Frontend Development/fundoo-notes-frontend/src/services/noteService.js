import api from '../api/axios';

export const noteService = {
  getAll: () => api.get('/notes'),
  getById: (id) => api.get(`/notes/${id}`),
  create: (payload) => api.post('/notes', payload),
  update: (id, payload) => api.put(`/notes/${id}`, payload),
  remove: (id) => api.delete(`/notes/${id}`),
  permanentDelete: (id) => api.delete(`/notes/${id}/permanent`),
  pin: (id) => api.put(`/notes/${id}/pin`),
  archive: (id) => api.put(`/notes/${id}/archive`),
  restore: (id) => api.put(`/notes/${id}/restore`),
  getPinned: () => api.get('/notes/pinned'),
  getArchived: () => api.get('/notes/archived'),
  getTrash: () => api.get('/notes/trash'),
  search: (keyword) => api.get('/notes/search', { params: { keyword } }),
  addLabel: (noteId, labelId) => api.post(`/notes/${noteId}/labels/${labelId}`),
  removeLabel: (noteId, labelId) => api.delete(`/notes/${noteId}/labels/${labelId}`)
};