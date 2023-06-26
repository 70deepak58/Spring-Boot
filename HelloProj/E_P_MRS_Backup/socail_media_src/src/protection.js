import React, { useState, useEffect } from 'react';

function Protected() {
  const [data, setData] = useState('');

  useEffect(() => {
    fetch('/api/protected', {
      headers: {
        'Authorization': `Bearer ${localStorage.getItem('jwt')}`
      }
    })
      .then(response => response.json())
      .then(data => setData(data.message));
  }, []);

  return (
    <div>
      <p>{data}</p>
    </div>
  );
}
