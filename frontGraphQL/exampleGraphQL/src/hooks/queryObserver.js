import { useEffect, useState } from 'react';
import { executeQuery } from '../services/graphqlClient';

class QueryObserver {
  constructor() {
    this.subscribers = new Set();
  }

  subscribe(cb) {
    this.subscribers.add(cb);
    return () => this.subscribers.delete(cb);
  }

  async runQuery(query, variables = {}) {
    this.subscribers.forEach((cb) => cb({ loading: true, data: null, error: null }));
    try {
      const data = await executeQuery(query, variables);
      this.subscribers.forEach((cb) => cb({ loading: false, data }));
    } catch (error) {
      this.subscribers.forEach((cb) => cb({ loading: false, error: error.message }));
    }
  }
}

const queryObserver = new QueryObserver();

export default queryObserver;

export function useQueryObserver() {
  const [result, setResult] = useState({ data: null, error: null, loading: false });

  useEffect(() => {
    const unsubscribe = queryObserver.subscribe((payload) => {
      setResult(payload);
    });
    return () => unsubscribe();
  }, []);

  return { result, runQuery: queryObserver.runQuery.bind(queryObserver) };
}
