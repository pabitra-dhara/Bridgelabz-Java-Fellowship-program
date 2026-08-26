import { useEffect, useState } from 'react';
import { FiCheck, FiPlus, FiX } from 'react-icons/fi';
import { labelService } from '../../services/labelService';
import { noteService } from '../../services/noteService';

export default function LabelPicker({ note, onClose, onSaved }) {
  const [labels, setLabels] = useState([]);
  const [selected, setSelected] = useState(new Set((note.labels || []).map((l) => l.id)));
  const [newLabel, setNewLabel] = useState('');
  const [loading, setLoading] = useState(true);
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState('');

  const loadLabels = async () => {
    try {
      const response = await labelService.getAll();
      setLabels(response.data?.data ?? []);
    } catch (err) {
      setError(err.response?.data?.message || 'Could not load labels');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { loadLabels(); }, []);

  const toggle = (id) => {
    setSelected((current) => {
      const next = new Set(current);
      next.has(id) ? next.delete(id) : next.add(id);
      return next;
    });
  };

  const createLabel = async () => {
    const value = newLabel.trim();
    if (!value) return;
    try {
      const response = await labelService.create({ name: value });
      const created = response.data?.data;
      await loadLabels();
      if (created?.id != null) {
        setSelected((current) => new Set([...current, created.id]));
      }
      setNewLabel('');
    } catch (err) {
      setError(err.response?.data?.message || 'Could not create label');
    }
  };

  const save = async () => {
    setSaving(true);
    setError('');
    try {
      const original = new Set((note.labels || []).map((l) => l.id));
      const current = selected;

      for (const id of current) {
        if (!original.has(id)) await noteService.addLabel(note.id, id);
      }
      for (const id of original) {
        if (!current.has(id)) await noteService.removeLabel(note.id, id);
      }

      onSaved?.();
      onClose();
    } catch (err) {
      setError(err.response?.data?.message || 'Could not update note labels');
    } finally {
      setSaving(false);
    }
  };

  return (
    <div className="editor-overlay" onMouseDown={onClose}>
      <div className="label-picker" onMouseDown={(e) => e.stopPropagation()}>
        <div className="label-picker-head">
          <h3>Edit labels</h3>
          <button className="icon-btn" onClick={onClose}><FiX /></button>
        </div>

        {error && <div className="alert alert-danger py-2">{error}</div>}

        <div className="label-create-row">
          <input
            className="form-control"
            placeholder="Create new label"
            value={newLabel}
            onChange={(e) => setNewLabel(e.target.value)}
            onKeyDown={(e) => e.key === 'Enter' && (e.preventDefault(), createLabel())}
          />
          <button className="btn btn-outline-dark" onClick={createLabel} type="button">
            <FiPlus />
          </button>
        </div>

        <div className="label-picker-list">
          {loading ? <div className="text-muted p-2">Loading labels...</div> : labels.length === 0 ? (
            <div className="text-muted p-2">No labels yet. Create one above.</div>
          ) : labels.map((label) => (
            <label className="label-picker-item" key={label.id}>
              <input
                type="checkbox"
                checked={selected.has(label.id)}
                onChange={() => toggle(label.id)}
              />
              <span>{label.name}</span>
              {selected.has(label.id) && <FiCheck className="ms-auto" />}
            </label>
          ))}
        </div>

        <div className="label-picker-footer">
          <button className="btn btn-light" onClick={onClose}>Cancel</button>
          <button className="btn btn-dark" onClick={save} disabled={saving}>
            {saving ? 'Saving...' : 'Save'}
          </button>
        </div>
      </div>
    </div>
  );
}
