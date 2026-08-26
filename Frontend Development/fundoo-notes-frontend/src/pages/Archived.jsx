import { useEffect, useState } from 'react';
import AppShell from '../components/layout/AppShell';
import NoteCard from '../components/notes/NoteCard';
import { noteService } from '../services/noteService';

export default function Archived() {
  const [notes, setNotes] = useState([]);
  const [error, setError] = useState('');

  const load = async () => {
    try { const response = await noteService.getArchived(); setNotes(response.data?.data ?? []); }
    catch (err) { setError(err.response?.data?.message || 'Could not load archived notes'); }
  };
  useEffect(() => { load(); }, []);

  const restore = async (id) => { await noteService.restore(id); load(); };
  const trash = async (id) => { await noteService.remove(id); load(); };

  return <AppShell><section className="simple-page">
    <h2>Archived</h2><p className="text-muted">Notes you have archived.</p>
    {error && <div className="alert alert-danger">{error}</div>}
    {notes.length === 0 ? <div className="empty-state compact"><div className="empty-icon">📦</div><p>No archived notes</p></div> :
      <div className="notes-grid">{notes.map((note) => <NoteCard key={note.id} note={note} mode="archived" onRestore={restore} onTrash={trash} />)}</div>}
  </section></AppShell>;
}
