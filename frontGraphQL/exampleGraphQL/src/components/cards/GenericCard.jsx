import React from 'react';

export default function GenericCard({ item }) {
  return (
    <div style={{ border: '1px solid #eee', padding: 10, borderRadius: 6, width: 300, margin: 8 }}>
      {Object.keys(item).map((k) => (
        <div key={k} style={{ fontSize: 13 }}>
          <strong>{k}:</strong> {String(item[k])}
        </div>
      ))}
    </div>
  );
}
