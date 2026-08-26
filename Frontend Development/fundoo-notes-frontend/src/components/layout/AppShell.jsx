import { useState } from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import {
  FiArchive, FiBell, FiEdit3, FiFileText, FiMenu, FiSearch,
  FiTag, FiTrash2, FiLogOut, FiX
} from 'react-icons/fi';
import { useAuth } from '../../context/AuthContext';

export default function AppShell({ children, onSearch }) {
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const [search, setSearch] = useState('');
  const { email, logout } = useAuth();
  const navigate = useNavigate();

  const submitSearch = (event) => {
    event.preventDefault();
    onSearch?.(search.trim());
  };

  return (
    <div className="keep-app">
      <header className="topbar">
        <button className="icon-btn menu-btn" onClick={() => setSidebarOpen(true)}>
          <FiMenu />
        </button>
        <div className="brand">
          <span className="brand-lightbulb">💡</span>
          <span>Fundoo</span>
        </div>

        <form className="search-box" onSubmit={submitSearch}>
          <FiSearch />
          <input
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            placeholder="Search"
          />
          {search && (
            <button type="button" className="search-clear" onClick={() => {
              setSearch('');
              onSearch?.('');
            }}>
              <FiX />
            </button>
          )}
        </form>

        <div className="top-actions">
          <button className="icon-btn" title="Refresh" onClick={() => window.location.reload()}>↻</button>
          <div className="avatar" title={email}>{email?.charAt(0).toUpperCase() || 'U'}</div>
        </div>
      </header>

      <aside className={`sidebar ${sidebarOpen ? 'open' : ''}`}>
        <div className="sidebar-close">
          <button className="icon-btn" onClick={() => setSidebarOpen(false)}><FiX /></button>
        </div>

        <NavLink to="/" end className="side-link" onClick={() => setSidebarOpen(false)}>
          <FiFileText /> <span>Notes</span>
        </NavLink>
        <NavLink to="/reminders" className="side-link" onClick={() => setSidebarOpen(false)}>
          <FiBell /> <span>Reminders</span>
        </NavLink>
        <NavLink to="/labels" className="side-link" onClick={() => setSidebarOpen(false)}>
          <FiTag /> <span>Edit labels</span>
        </NavLink>
        <NavLink to="/archived" className="side-link" onClick={() => setSidebarOpen(false)}>
          <FiArchive /> <span>Archive</span>
        </NavLink>
        <NavLink to="/trash" className="side-link" onClick={() => setSidebarOpen(false)}>
          <FiTrash2 /> <span>Trash</span>
        </NavLink>
        <div className="sidebar-spacer" />
        <button className="side-link logout-link" onClick={() => { logout(); navigate('/login'); }}>
          <FiLogOut /> <span>Sign out</span>
        </button>
      </aside>

      {sidebarOpen && <div className="sidebar-backdrop" onClick={() => setSidebarOpen(false)} />}
      <main className="page-content">{children}</main>
    </div>
  );
}