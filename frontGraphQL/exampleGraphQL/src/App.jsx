//Router:	Envuelve la aplicación y gestiona la navegación.
//Routes:	Agrupa múltiples rutas y muestra solo la primera coincidencia.
//Route:	Define una URL específica y qué componente renderizar.

//Instala lo siguiente
//npm install react-router-dom
import Home from "./pages/mainpages/Home";

function App() {
  return (
    <div className="app">
      <Home />
    </div>
  );
}

export default App;