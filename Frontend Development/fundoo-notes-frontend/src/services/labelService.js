import api from '../api/axios';

// Keep these methods in one place. Change only the URL paths if your
// label-service endpoints use different routes.
export const labelService = {
  getAll: () => api.get('/labels'),
  create: (payload) => api.post('/labels', payload),
  update: (id, payload) => api.put(`/labels/${id}`, payload),
  remove: (id) => api.delete(`/labels/${id}`)
};