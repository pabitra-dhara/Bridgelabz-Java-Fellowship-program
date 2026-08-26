import { useEffect, useState } from 'react';
import AppShell from '../components/layout/AppShell';
import NoteCard from '../components/notes/NoteCard';
import { noteService } from '../services/noteService';

export default function Trash() {
  const [notes, setNotes] = useState([]);
  const [error, setError] = useState('');
  const load = async () => { try { const response = await noteService.getTrash(); setNotes(response.data?.data ?? []); } catch (err) { setError(err.response?.data?.message || 'Could not load trash'); } };
  useEffect(() => { load(); }, []);
  const restore = async (id) => { await noteService.restore(id); load(); };
  const permanentDelete = async (id) => { if (!window.confirm('Delete this note permanently?')) return; await noteService.permanentDelete(id); load(); };
  return <AppShell><section className="simple-page">
    <h2>Trash</h2><p className="text-muted">Recover notes or delete them permanently.</p>
    {error && <div className="alert alert-danger">{error}</div>}
    {notes.length === 0 ? <div className="empty-state compact"><div className="empty-icon">🗑️</div><p>No notes in trash</p></div> :
      <div className="notes-grid">{notes.map((note) => <NoteCard key={note.id} note={note} mode="trash" onRestore={restore} onPermanentDelete={permanentDelete} />)}</div>}
  </section></AppShell>;
}
