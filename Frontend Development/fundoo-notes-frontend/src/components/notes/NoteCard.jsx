import { useState } from 'react';
import {
  FiArchive, FiBell, FiEdit2, FiMoreVertical, FiTag, FiTrash2,
  FiRotateCcw, FiTrash, FiMapPin, FiCheckCircle
} from 'react-icons/fi';

export default function NoteCard({
  note, onEdit, onPin, onArchive, onTrash, onRestore,
  onPermanentDelete, onLabels, mode = 'active'
}) {
  const [menuOpen, setMenuOpen] = useState(false);

  return (
    <article className={`note-card ${note.pinned ? 'pinned' : ''}`}>
      <div className="note-card-head">
        <h3>{note.title || 'Untitled'}</h3>
        {note.pinned && <span className="pin-dot" title="Pinned">📌</span>}
      </div>

      <p>{note.description || 'No description'}</p>

      {note.labels?.length > 0 && (
        <div className="label-row">
          {note.labels.map((label) => (
            <span className="label-chip" key={label.id ?? label.name}>{label.name}</span>
          ))}
        </div>
      )}

      <div className="note-actions">
        {mode === 'active' && (
          <>
            <button title="Edit" onClick={() => onEdit?.(note)}><FiEdit2 /></button>
            <button title="Reminder" onClick={() => onEdit?.(note, 'reminder')}><FiBell /></button>
            <button title="Labels" onClick={() => onLabels?.(note)}><FiTag /></button>
            <button title={note.pinned ? 'Unpin' : 'Pin'} onClick={() => onPin?.(note.id)}><FiMapPin /></button>
            <button title="Archive" onClick={() => onArchive?.(note.id)}><FiArchive /></button>
            <button title="Trash" onClick={() => onTrash?.(note.id)}><FiTrash2 /></button>
          </>
        )}

        {mode === 'archived' && (
          <>
            <button title="Restore" onClick={() => onRestore?.(note.id)}><FiRotateCcw /></button>
            <button title="Trash" onClick={() => onTrash?.(note.id)}><FiTrash2 /></button>
          </>
        )}

        {mode === 'trash' && (
          <>
            <button title="Restore" onClick={() => onRestore?.(note.id)}><FiRotateCcw /></button>
            <button title="Delete permanently" onClick={() => onPermanentDelete?.(note.id)}><FiTrash /></button>
          </>
        )}

        <div className="note-more-wrap">
          <button title="More" onClick={() => setMenuOpen((v) => !v)}><FiMoreVertical /></button>
          {menuOpen && (
            <>
              <div className="menu-backdrop" onClick={() => setMenuOpen(false)} />
              <div className="note-menu">
                {mode === 'active' && <>
                  <button onClick={() => { setMenuOpen(false); onLabels?.(note); }}><FiTag /> Add/Edit labels</button>
                  <button onClick={() => { setMenuOpen(false); onArchive?.(note.id); }}><FiArchive /> Archive</button>
                  <button onClick={() => { setMenuOpen(false); onTrash?.(note.id); }}><FiTrash2 /> Move to trash</button>
                </>}
                {mode === 'archived' && <button onClick={() => { setMenuOpen(false); onRestore?.(note.id); }}><FiRotateCcw /> Unarchive</button>}
                {mode === 'trash' && <>
                  <button onClick={() => { setMenuOpen(false); onRestore?.(note.id); }}><FiRotateCcw /> Restore</button>
                  <button className="danger" onClick={() => { setMenuOpen(false); onPermanentDelete?.(note.id); }}><FiTrash /> Delete permanently</button>
                </>}
              </div>
            </>
          )}
        </div>
      </div>
    </article>
  );
}
