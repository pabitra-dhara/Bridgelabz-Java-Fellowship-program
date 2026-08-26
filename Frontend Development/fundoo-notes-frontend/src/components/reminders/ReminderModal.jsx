import { useState } from 'react';
import { reminderService } from '../../services/reminderService';

export default function ReminderModal({ note, onClose, onCreated }) {
  const [time, setTime] = useState('');
  const [saving, setSaving] = useState(false);

  const save = async (e) => {
    e.preventDefault();
    if (!time) return;

    setSaving(true);
    try {
      // datetime-local gives "YYYY-MM-DDTHH:mm", which matches
      // Spring LocalDateTime JSON format.
      await reminderService.create(note.id, {
        reminderTime: time + ':00'
      });
      onCreated?.();
      onClose();
    } finally {
      setSaving(false);
    }
  };

  return (
    <div className="editor-overlay" onMouseDown={onClose}>
      <form className="reminder-modal" onSubmit={save} onMouseDown={(e) => e.stopPropagation()}>
        <h3>Set reminder</h3>
        <p className="text-muted">{note.title || 'Untitled'}</p>
        <label className="form-label">Reminder date and time</label>
        <input
          type="datetime-local"
          className="form-control"
          value={time}
          onChange={(e) => setTime(e.target.value)}
          required
        />
        <div className="mt-3 d-flex justify-content-end">
          <button type="button" className="btn btn-light me-2" onClick={onClose}>Cancel</button>
          <button className="btn btn-dark" disabled={saving}>
            {saving ? 'Saving...' : 'Set reminder'}
          </button>
        </div>
      </form>
    </div>
  );
}