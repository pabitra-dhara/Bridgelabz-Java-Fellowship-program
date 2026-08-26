import { useEffect, useState } from 'react';
import AppShell from '../components/layout/AppShell';
import { reminderService } from '../services/reminderService';

export default function Reminders() {
  const [items, setItems] = useState([]);
  const [error, setError] = useState('');

  const load = async () => {
    try {
      const response = await reminderService.getMine();
      setItems(response.data?.data ?? []);
    } catch (err) {
      setError(err.response?.data?.message || 'Could not load reminders');
    }
  };

  useEffect(() => { load(); }, []);

  const remove = async (id) => {
    await reminderService.remove(id);
    load();
  };

  return (
    <AppShell>
      <section className="simple-page">
        <h2>Reminders</h2>
        <p className="text-muted">Scheduled reminders from your notes.</p>
        {error && <div className="alert alert-danger">{error}</div>}
        {items.length === 0 ? (
          <div className="empty-state compact"><div className="empty-icon">🔔</div><p>No reminders</p></div>
        ) : (
          <div className="reminder-list">
            {items.map((item) => (
              <div className="reminder-item" key={item.id}>
                <div>
                  <strong>Note #{item.noteId}</strong>
                  <div>{new Date(item.reminderTime).toLocaleString()}</div>
                </div>
                <span className={`badge ${item.notified ? 'text-bg-success' : 'text-bg-warning'}`}>
                  {item.notified ? 'Sent' : 'Pending'}
                </span>
                <button className="btn btn-sm btn-outline-danger" onClick={() => remove(item.id)}>Delete</button>
              </div>
            ))}
          </div>
        )}
      </section>
    </AppShell>
  );
}