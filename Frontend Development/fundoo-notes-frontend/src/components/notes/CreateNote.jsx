import { useState } from 'react';
import { FiCheckSquare, FiEdit3 } from 'react-icons/fi';

export default function CreateNote({ onCreate }) {
  const [expanded, setExpanded] = useState(false);
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');

  const reset = () => {
    setTitle('');
    setDescription('');
    setExpanded(false);
  };

  const submit = async (e) => {
    e.preventDefault();
    if (!title.trim() && !description.trim()) return;
    await onCreate({ title: title.trim(), description: description.trim() });
    reset();
  };

  return (
    <form className={`create-note ${expanded ? 'expanded' : ''}`} onSubmit={submit}>
      {expanded && (
        <input
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          placeholder="Title"
          className="create-title"
        />
      )}
      <textarea
        value={description}
        onChange={(e) => setDescription(e.target.value)}
        placeholder="Take a note..."
        onFocus={() => setExpanded(true)}
        rows={expanded ? 4 : 1}
      />
      {expanded && (
        <div className="create-actions">
          <div>
            <button type="button" className="mini-action" title="Checklist"><FiCheckSquare /></button>
            <button type="button" className="mini-action" title="Drawing"><FiEdit3 /></button>
          </div>
          <div>
            <button type="button" className="btn btn-sm btn-light me-2" onClick={reset}>Close</button>
            <button type="submit" className="btn btn-sm btn-dark">Save</button>
          </div>
        </div>
      )}
    </form>
  );
}