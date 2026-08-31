import React, { useEffect, useState } from "react";
import { getCurrentUser, getUserDetails, updateUserDetails } from "../services/authService";
import { useAuth } from "../context/AuthContext";

export default function Profile() {
  const { user, setUserFromServer } = useAuth();
  const [details, setDetails] = useState({ email: "", addressType: "Work", fullAddress: "", city: "", state: "" });
  const [editing, setEditing] = useState(false);
  useEffect(() => { (async () => { try { const [u,d] = await Promise.all([getCurrentUser(), getUserDetails()]); setUserFromServer(u); setDetails(d || {}); } catch(e) { console.error(e); } })(); }, [setUserFromServer]);
  const save = async () => { const d = await updateUserDetails(details); setDetails(d); setEditing(false); };
  return <section className="container profile-page">
    <div className="breadcrumb">Home / <span>Profile</span></div>
    <div className="profile-section"><h2>Personal Details</h2>
      <label>Full Name</label><input value={user?.fullName || ""} readOnly />
      <label>Email Id</label><input value={user?.email || ""} readOnly />
      <label>Mobile Number</label><input value={user?.phone || ""} readOnly />
    </div>
    <div className="profile-section"><h2>Address Details <button className="edit-link" onClick={() => editing ? save() : setEditing(true)}>{editing ? "Save" : "Edit"}</button></h2>
      <label>Type</label><input value={details.addressType || ""} onChange={e=>setDetails({...details,addressType:e.target.value})} readOnly={!editing} />
      <label>Address</label><textarea value={details.fullAddress || ""} onChange={e=>setDetails({...details,fullAddress:e.target.value})} readOnly={!editing} />
      <div className="profile-two"><input value={details.city || ""} onChange={e=>setDetails({...details,city:e.target.value})} readOnly={!editing} /><input value={details.state || ""} onChange={e=>setDetails({...details,state:e.target.value})} readOnly={!editing} /></div>
    </div></section>;
}
