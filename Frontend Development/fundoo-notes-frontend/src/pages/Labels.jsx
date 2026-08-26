import { useEffect, useState } from 'react';
import AppShell from '../components/layout/AppShell';
import { FiEdit2, FiTrash2, FiCheck, FiX } from 'react-icons/fi';
import { labelService } from '../services/labelService';

export default function Labels() {
  const [labels, setLabels] = useState([]);
  const [name, setName] = useState('');
  const [editingId, setEditingId] = useState(null);
  const [editingName, setEditingName] = useState('');
  const [error, setError] = useState('');

  const load = async () => {
    try { const response = await labelService.getAll(); setLabels(response.data?.data ?? []); }
    catch (err) { setError(err.response?.data?.message || 'Could not load labels'); }
  };
  useEffect(() => { load(); }, []);

  const create = async (e) => {
    e.preventDefault(); if (!name.trim()) return;
    try { await labelService.create({ name: name.trim() }); setName(''); await load(); }
    catch (err) { setError(err.response?.data?.message || 'Could not create label'); }
  };

  const update = async (id) => {
    if (!editingName.trim()) return;
    try { await labelService.update(id, { name: editingName.trim() }); setEditingId(null); await load(); }
    catch (err) { setError(err.response?.data?.message || 'Could not update label'); }
  };

  const remove = async (id) => {
    if (!window.confirm('Delete this label?')) return;
    try { await labelService.remove(id); await load(); }
    catch (err) { setError(err.response?.data?.message || 'Could not delete label'); }
  };

  return <AppShell><section className="simple-page narrow">
    <h2>Edit labels</h2><p className="text-muted">Create, rename or delete labels.</p>
    <form className="d-flex gap-2 mb-4" onSubmit={create}>
      <input className="form-control" value={name} onChange={(e) => setName(e.target.value)} placeholder="New label" />
      <button className="btn btn-dark">Create</button>
    </form>
    {error && <div className="alert alert-warning">{error}</div>}
    <div className="label-management">
      {labels.map((label) => <div className="label-management-item" key={label.id}>
        {editingId === label.id ? <>
          <input className="form-control" value={editingName} onChange={(e) => setEditingName(e.target.value)} autoFocus />
          <div className="label-edit-actions"><button onClick={() => update(label.id)}><FiCheck /></button><button onClick={() => setEditingId(null)}><FiX /></button></div>
        </> : <>
          <span className="label-name">{label.name}</span>
          <div className="label-edit-actions"><button title="Edit" onClick={() => { setEditingId(label.id); setEditingName(label.name); }}><FiEdit2 /></button><button title="Delete" onClick={() => remove(label.id)}><FiTrash2 /></button></div>
        </>}
      </div>)}
    </div>
  </section></AppShell>;
}
