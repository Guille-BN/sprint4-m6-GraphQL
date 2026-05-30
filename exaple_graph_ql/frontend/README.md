# Explicación del patrón Observer en React
El patrón Observer se usó para que varios componentes se actualicen automáticamente cuando se cambia una consulta GraphQL o llegan nuevos datos. Se hizo un archivo llamado [queryObserver.js](/frontGraphQL/exampleGraphQL/src/hooks/queryObserver.js) que se encarga de ejecutar las queries y avisarle a los componentes cuando hay cambios.

Después se creó un hook llamado useQueryObserver() para que los componentes puedan obtener los resultados y ejecutar queries fácilmente. [SearchBar.jsx](/frontGraphQL/exampleGraphQL/src/components/SearchBar.jsx) arma la query y la ejecuta, y [Home.jsx](/frontGraphQL/exampleGraphQL/src/pages/mainpages/Home.jsx) recibe los resultados y actualiza la interfaz automáticamente.

Con esto, los componentes no dependen directamente unos de otros y es más fácil mantener el código organizado.

# Explicación del patrón Factory Method en React
El patrón Factory Method se usó para decidir qué tipo de animal mostrar dependiendo de la información recibida. Para esto, se creó un componente llamado [CardFactory.jsx](/frontGraphQL/exampleGraphQL/src/components/cards/CardFactory.jsx) que revisa los datos y decide qué tarjeta renderizar. Si el objeto no tiene información de animales usa GenericCard.

En [Home.jsx](/frontGraphQL/exampleGraphQL/src/pages/mainpages/Home.jsx) se recorren los resultados y cada elemento pasa por CardFactory.

Con esto, toda la lógica para elegir las tarjetas está en un solo lugar y agregar nuevas cards después es más fácil.

## Para ejecutar:
### Frontend:
```zsh
cd frontGraphQL/exampleGraphQL
npm install
npm run dev
```

### Backend:
Ejecutar spring-graphql en el Dashboard de Spring Boot.