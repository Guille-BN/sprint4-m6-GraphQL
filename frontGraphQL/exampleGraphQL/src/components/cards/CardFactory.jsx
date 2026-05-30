import React from 'react';
import AnimalCard from './AnimalCard';
import GenericCard from './GenericCard';

export default function CardFactory({ item }) {
  // Factory Method: decide which card to render based on item content
  if (!item) return null;
  if (item.commonName || item.scientificName || item.type) {
    return <AnimalCard item={item} />;
  }
  return <GenericCard item={item} />;
}
