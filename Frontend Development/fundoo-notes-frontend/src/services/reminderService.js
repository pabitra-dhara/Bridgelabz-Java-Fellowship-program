import api from '../api/axios';

export const reminderService = {
  getMine: () => api.get('/reminders'),
  create: (noteId, payload) => api.post(`/reminders/${noteId}`, payload),
  update: (id, payload) => api.put(`/reminders/${id}`, payload),
  remove: (id) => api.delete(`/reminders/${id}`)
};