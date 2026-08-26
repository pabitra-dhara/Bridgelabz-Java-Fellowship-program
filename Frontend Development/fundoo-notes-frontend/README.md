# Fundoo Notes React Frontend

React + Vite frontend for the Fundoo Notes microservices application.

## Backend gateway

The project is configured for the API Gateway used in the current setup:

```env
VITE_API_BASE_URL=http://localhost:8081/api-gateway
```

If your gateway uses another port/path, change `.env` and restart Vite.

## Features implemented

- Create, edit and delete labels
- Add/remove labels from notes
- Display labels on note cards
- Pin/unpin notes
- Archive notes
- Archived Notes page and unarchive
- Move notes to trash
- Trash page and restore
- Permanent delete from trash
- Google Keep-style action menu
- Reminder UI
- Responsive Bootstrap/SCSS layout
- Axios JWT interceptor
- Protected routes

## Run

```bash
npm install
npm run dev
```

Do not commit `node_modules`.
