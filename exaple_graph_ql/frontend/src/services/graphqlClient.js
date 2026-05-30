export async function executeQuery(query, variables = {}) {
  const res = await fetch('http://localhost:8080/graphql', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ query, variables }),
  });

  const json = await res.json();
  if (json.errors) {
    const msg = json.errors.map((e) => e.message).join(', ');
    throw new Error(msg);
  }
  return json.data;
}

export default { executeQuery };
