import { useEffect, useState } from 'react';
import { FiCheck, FiX } from 'react-icons/fi';

export default function NoteEditor({ note, onSave, onClose }) {
  const [title, setTitle] = useState(note?.title || '');
  const [description, setDescription] = useState(note?.description || '');

  useEffect(() => {
    setTitle(note?.title || '');
    setDescription(note?.description || '');
  }, [note]);

  const submit = (e) => {
    e.preventDefault();
    onSave({ title, description });
  };

  return (
    <div className="editor-overlay" onMouseDown={onClose}>
      <form className="note-editor" onSubmit={submit} onMouseDown={(e) => e.stopPropagation()}>
        <input
          className="editor-title"
          placeholder="Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          autoFocus
        />
        <textarea
          className="editor-body"
          placeholder="Take a note..."
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          rows="7"
        />
        <div className="editor-footer">
          <small>Changes are saved to Fundoo Notes</small>
          <div>
            <button type="button" className="btn btn-light me-2" onClick={onClose}>
              <FiX /> Close
            </button>
            <button type="submit" className="btn btn-dark">
              <FiCheck /> Save
            </button>
          </div>
        </div>
      </form>
    </div>
  );
}