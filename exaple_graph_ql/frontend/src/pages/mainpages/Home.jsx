import React, { useEffect } from 'react';
import SearchBar from '../../components/SearchBar';
import CardFactory from '../../components/cards/CardFactory';
import { useQueryObserver } from '../../hooks/queryObserver';

function Home() {
    const { result, runQuery } = useQueryObserver();

    useEffect(() => {
        // load initial data
        const q = `query { getAllPosts { id commonName scientificName gender diet extant genus type createdAt } }`;
        runQuery(q);
    }, [runQuery]);

    const items = (() => {
        if (!result || !result.data) return [];
        // GraphQL root field could be getAllPosts or getAnimalsByType etc.
        const keys = Object.keys(result.data);
        if (keys.length === 0) return [];
        const first = result.data[keys[0]];
        return Array.isArray(first) ? first : [first];
    })();

    return (
        <div style={{ padding: 16 }}>
            <h1>Tipos de animales</h1>
            <h2>Sprint 4, Module 6 Advanced Web -  Team 23</h2>
            <SearchBar />

                    {result && result.error && (
                        <div style={{ color: 'red' }}>Error: {result.error}</div>
                    )}

                    {result && result.loading && (
                        <div style={{ marginTop: 12, fontStyle: 'italic' }}>Loading...</div>
                    )}

            <div style={{ display: 'flex', flexWrap: 'wrap', marginTop: 12 }}>
                {items.length === 0 && <div>No resultados</div>}
                {items.map((it) => (
                    <CardFactory key={it.id || JSON.stringify(it)} item={it} />
                ))}
            </div>
        </div>
    );
}

export default Home;