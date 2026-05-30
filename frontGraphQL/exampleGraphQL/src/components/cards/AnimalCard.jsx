import React from 'react';

export default function AnimalCard({ item }) {
  return (
    <div style={{ border: '1px solid #ddd', padding: 12, borderRadius: 6, width: 320, margin: 8 }}>
      <h3 style={{ margin: '0 0 8px 0' }}>{item.commonName || '—'}</h3>
      <div style={{ fontSize: 13, color: '#555' }}>{item.scientificName}</div>
      <div style={{ marginTop: 8 }}>
        <strong>Type:</strong> {item.type} • <strong>Genus:</strong> {item.genus}
      </div>
      <div style={{ marginTop: 8 }}>
        <strong>Diet:</strong> {item.diet} • <strong>Gender:</strong> {item.gender}
      </div>
      <div style={{ marginTop: 8 }}>
        <strong>Extant:</strong> {String(item.extant)}
      </div>
      <div style={{ marginTop: 8, fontSize: 12, color: '#666' }}>{item.createdAt}</div>
    </div>
  );
}
