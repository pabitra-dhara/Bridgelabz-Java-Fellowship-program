import React from "react";
import { createPortal } from "react-dom";

export default function Modal({ open, title, children, onClose }) {
  if (!open) return null;
  return createPortal(
    <div className="modal-backdrop-custom" role="dialog" aria-modal="true">
      <div className="custom-modal">
        <div className="modal-head"><h3>{title}</h3><button onClick={onClose}>×</button></div>
        <div>{children}</div>
      </div>
    </div>,
    document.getElementById("modal-root")
  );
}
