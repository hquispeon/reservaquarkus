# Proyecto reservaquarkus
Proyecto de gestión de reservas desarrollado con Quarkus, utilizando OpenJDK 21 y Maven como herramientas de compilación. Incluye soporte para generación de ejecutables nativos mediante GraalVM, optimizando el rendimiento y la portabilidad de la aplicación.

# Página de creación del proyecto base
https://code.quarkus.io/

# Compilación
mvn clean install -U
mvn compile quarkus:dev

# Crear imagen nativa
mvn package -Pnative

# CURLS
curl --request GET \
  --url http://localhost:8082/hello

curl --request POST \
  --url http://localhost:8082/reservas/grabar \
  --header 'content-type: application/json' \
  --data '{
  "nombreCliente": "Nombre Cliente",
  "fechaReserva": "2025-08-01T16:00:00",
  "descripcion": "Tour Valle Sagrado",
  "restricciones": "Comida vegetariana"
}'

curl --request GET \
  --url http://localhost:8082/reservas/consultar

curl --request GET \
  --url http://localhost:8082/reservas/consultar/1

curl --request DELETE \
  --url http://localhost:8082/reservas/borrar/1 \
  --header 'accept: application/json'