import { useCallback, useEffect, useState } from 'react';
import AppShell from '../components/layout/AppShell';
import CreateNote from '../components/notes/CreateNote';
import NoteCard from '../components/notes/NoteCard';
import NoteEditor from '../components/notes/NoteEditor';
import LabelPicker from '../components/notes/LabelPicker';
import ReminderModal from '../components/reminders/ReminderModal';
import { noteService } from '../services/noteService';

export default function Dashboard() {
  const [notes, setNotes] = useState([]);
  const [editing, setEditing] = useState(null);
  const [labelNote, setLabelNote] = useState(null);
  const [reminderNote, setReminderNote] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  const loadNotes = useCallback(async () => {
    setLoading(true);
    try {
      const response = await noteService.getAll();
      setNotes(response.data?.data ?? []);
      setError('');
    } catch (err) {
      setError(err.response?.data?.message || 'Could not load notes');
    } finally { setLoading(false); }
  }, []);

  useEffect(() => { loadNotes(); }, [loadNotes]);

  const create = async (payload) => { await noteService.create(payload); await loadNotes(); };
  const save = async (payload) => {
    if (editing?.id) await noteService.update(editing.id, payload);
    else await noteService.create(payload);
    setEditing(null); await loadNotes();
  };
  const pin = async (id) => { await noteService.pin(id); await loadNotes(); };
  const archive = async (id) => { await noteService.archive(id); await loadNotes(); };
  const trash = async (id) => { await noteService.remove(id); await loadNotes(); };

  const search = async (keyword) => {
    if (!keyword) return loadNotes();
    try {
      const response = await noteService.search(keyword);
      setNotes(response.data?.data ?? []);
    } catch (err) { setError(err.response?.data?.message || 'Search failed'); }
  };

  return (
    <AppShell onSearch={search}>
      <section className="dashboard">
        <CreateNote onCreate={create} />
        {error && <div className="alert alert-danger mt-3">{error}</div>}
        <div className="section-label">Notes</div>
        {loading ? <div className="loading-state">Loading notes...</div> : notes.length === 0 ? (
          <div className="empty-state"><div className="empty-icon">💡</div><p>Notes you add appear here</p></div>
        ) : (
          <div className="notes-grid">
            {notes.filter((n) => !n.archived && !n.trashed).map((note) => (
              <NoteCard key={note.id} note={note}
                onEdit={(n, action) => action === 'reminder' ? setReminderNote(n) : setEditing(n)}
                onPin={pin} onArchive={archive} onTrash={trash} onLabels={setLabelNote} />
            ))}
          </div>
        )}
        {editing && <NoteEditor note={editing} onSave={save} onClose={() => setEditing(null)} />}
        {labelNote && <LabelPicker note={labelNote} onClose={() => setLabelNote(null)} onSaved={loadNotes} />}
        {reminderNote && <ReminderModal note={reminderNote} onClose={() => setReminderNote(null)} onCreated={() => {}} />}
      </section>
    </AppShell>
  );
}
