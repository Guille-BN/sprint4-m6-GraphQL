import React, { useState } from 'react';
import { useQueryObserver } from '../hooks/queryObserver';

const FIELDS = `id commonName scientificName gender diet extant genus type createdAt updatedAt deletedAt`;

export default function SearchBar() {
  const { runQuery } = useQueryObserver();
  const [queryType, setQueryType] = useState('getAllPosts');
  const [term, setTerm] = useState('');
  const [count, setCount] = useState(10);
  const [typeFilter, setTypeFilter] = useState('All');
  const [genderFilter, setGenderFilter] = useState('All');
  const [dietFilter, setDietFilter] = useState('All');
  const [extantFilter, setExtantFilter] = useState('All');

  function buildQuery() {
    switch (queryType) {
      case 'getAllPosts':
        return `query { getAllPosts { ${FIELDS} } }`;
      case 'getRecentPosts':
        return `query { getRecentPosts(count: ${count}, offset: 0) { ${FIELDS} } }`;
      case 'getAnimalsByType':
        return `query { getAnimalsByType(type: \"${term}\") { ${FIELDS} } }`;
      case 'getPostById':
        return `query { getPostById(id: ${term}) { ${FIELDS} } }`;
      default:
        return `query { getAllPosts { ${FIELDS} } }`;
    }
  }

  function handleSubmit(e) {
    e.preventDefault();
    const q = buildQuery();
    runQuery(q);
  }

  // handlers for filter dropdowns - set state only; use Apply Filters to run combined query
  function handleTypeChange(value) {
    setTypeFilter(value);
  }

  function handleGenderChange(value) {
    setGenderFilter(value);
  }

  function handleDietChange(value) {
    setDietFilter(value);
  }

  function handleExtantChange(value) {
    setExtantFilter(value);
  }

  function applyFilters() {
    const parts = [];
    if (typeFilter !== 'All') parts.push(`type: \"${typeFilter}\"`);
    if (genderFilter !== 'All') parts.push(`gender: \"${genderFilter}\"`);
    if (dietFilter !== 'All') parts.push(`diet: \"${dietFilter}\"`);
    if (extantFilter === 'true') parts.push(`extant: true`);
    if (extantFilter === 'false') parts.push(`extant: false`);

    if (parts.length === 0) {
      runQuery(`query { getAllPosts { ${FIELDS} } }`);
      return;
    }

    const q = `query { posts(filter: { ${parts.join(', ')} }) { ${FIELDS} } }`;
    runQuery(q);
  }

  return (
    <div style={{ display: 'flex', gap: 12, alignItems: 'center', marginBottom: 12, flexWrap: 'wrap' }}>
      <div>
        <label>Type: </label>
        <select value={typeFilter} onChange={(e) => handleTypeChange(e.target.value)}>
          <option>All</option>
          <option>Mammal</option>
          <option>Fish</option>
          <option>Reptile</option>
        </select>
      </div>

      <div>
        <label>Gender: </label>
        <select value={genderFilter} onChange={(e) => handleGenderChange(e.target.value)}>
          <option>All</option>
          <option>Male</option>
          <option>Female</option>
        </select>
      </div>

      <div>
        <label>Diet: </label>
        <select value={dietFilter} onChange={(e) => handleDietChange(e.target.value)}>
          <option>All</option>
          <option>Herbivore</option>
          <option>Gelatinivore</option>
          <option>Carnivore</option>
          <option>Omnivore</option>
        </select>
      </div>

      <div>
        <label>Extant: </label>
        <select value={extantFilter} onChange={(e) => handleExtantChange(e.target.value)}>
          <option>All</option>
          <option value="true">true</option>
          <option value="false">false</option>
        </select>
      </div>

      <div>
        <button onClick={applyFilters}>Apply filters</button>
      </div>

      <form onSubmit={handleSubmit} style={{ display: 'flex', gap: 8, alignItems: 'center' }}>
        <select value={queryType} onChange={(e) => setQueryType(e.target.value)}>
          <option value="getAllPosts">All animals</option>
          <option value="getRecentPosts">Recent</option>
          <option value="getAnimalsByType">By type (manual)</option>
          <option value="getPostById">By id</option>
        </select>

        {(queryType === 'getAnimalsByType' || queryType === 'getPostById') && (
          <input placeholder="term" value={term} onChange={(e) => setTerm(e.target.value)} />
        )}

        {queryType === 'getRecentPosts' && (
          <input type="number" value={count} onChange={(e) => setCount(Number(e.target.value))} style={{ width: 80 }} />
        )}
      </form>
    </div>
  );
}
