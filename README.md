# Reskilling Hackathon: The White-Label Store Challenge

This repo contains the source code for the backend, to be deployed in Google Cloud Run Service.

It uses Java 17 + Spring Boot + Spring JDBC.

## Set up

Make sure to replace <YOUR_SQUAD> in `application.properties` file.

Make sure to add the db password there too, it will be provieded to you.

## Helpful Commands

```
docker build -t europe-docker.pkg.dev/<YOUR_SQUAD>/reskilling-hackathon-backend/backend:latest .

docker push europe-docker.pkg.dev/<YOUR_SQUAD>/reskilling-hackathon-backend/backend:latest
```

----

# 🚀 Hackaton: The White-Label Store Challenge

### Instrucciones Generales
* Duración: 3 Horas
* Stack: Java 17 + Spring Boot + Cloud Run + Cloud SQL

### Objetivo General
El objetivo principal es crear el backend para una app de Gestión de Dispositivos. El backend expondrá un API REST para que el front pueda consumir. La especificación del API deberá ser diseñada por el squad, considerando el modelo de datos que existe en la base de datos.

### Funcionalidad sugerida
* Poder visualizar el listado de dispositivos del catálogo, a alto nivel.
* Poder visualizar la imágen de cada dispositivo.
* Poder visualizar el detalle de un dispositivo.
* Poder paginar los resultados en el listado.
* Poder ordenar los dispositivos en el listado.
* Poder filtrar dispositivos por marca y/o precio.
* Poder buscar dispositivos por palabras claves (marca o modelo).
* Poder operar sobre el catálogo:
  * Borrar un dispositivo.
  * Editar el precio de un dispositivo.
  * Añadir un nuevo dispositivo.

### Recursos existentes
* Proyecto de GCP
  * https://console.cloud.google.com/welcome?project=squad-a1
  * https://console.cloud.google.com/welcome?project=squad-b1
* Postgres Cloud SQL
  * Con una tabla “products” rellenada con 30 dispositivos
* Bucket en Cloud Storage
  * Con 30 imágenes (.webp) de distintos dispositivos
* Cloud Run Service
  * Servicio en Java + Spring Boot
  * Incluye un API HTTP
  * Incluye conexión a la base de datos
  * Incluye un Dockerfile y pom.xml
  * https://github.com/nicanor-romero/reskilling-hackathon

### Objetivos Puntuales a Cumplimentar
1. Analizar y probar el servicio existente (cloud run service).
2. Revisar los recursos existentes (cloud storage bucket, cloud sql, artifact registry, service accounts).
3. Revisar el modelo de datos de la tabla “products”.
4. Diseñar el API para exponer el catálogo a el frontend teniendo en cuenta la funcionalidad sugerida. OPCIONAL: Escribir la especificación OpenAPI.
5. Implementar de a etapas la funcionalidad del API. Es preferible entregar un producto básico que funcione correctamente, a entregar un producto completo pero que no funciona bien o está mal implementado.

### Notas
* Simulemos una situación real. Este ejercicio no es didáctico. Hay información que no ha sido especificada en las instrucciones y deberán buscar entre los recursos para obtenerla, como lo harían en una situación real.
* Se puede utilizar IA. Sin embargo, es necesario entender lo que ha sido generado por ella.
* No es necesario tener conocimiento sobre Spring Boot. Usando IA debería ser sencillo implementar la funcionalidad y poder entender qué hace cada línea de código.
* No es necesario conocer Spring Boot JDBC (Java Database Connectivity). Usando la documentación y la IA debería ser sencillo lograr el objetivo.
* Buenas prácticas. Se tendrán en consideración las buenas prácticas de desarrollo de software, teniendo en cuenta el contexto del proyecto y el tiempo limitado.
* Trabajo en equipo. Parte del objetivo es trabajar en equipo, tener discusiones, analizar entre todos, planificar, repartir tareas, colaborar, etc.

## 🏆 Criterios de Evaluación del Hackathon
La evaluación se centrará en la capacidad del equipo para entregar una API REST funcional, bien estructurada y lista para ser consumida por el frontend, respetando las buenas prácticas, a pesar de las limitaciones de tiempo.

### 1. Producto Mínimo Viable (MVP) y Funcionalidad (50%)

Se evaluará la cantidad y calidad de las funcionalidades sugeridas que hayan sido implementadas y que sean accesibles y funcionales a través de los endpoints REST.

| Criterio | Descripción | Ponderación |
| :--- | :--- | :--- |
| Implementación Core | CRUD Básico sobre el catálogo: Listado (`GET /products`), Detalle (`GET /products/{id}`), y al menos una operación de modificación (EDITAR PRECIO o BORRAR). | 20% |
| Calidad de Búsqueda/Filtro | Implementación funcional de al menos una de las funcionalidades de búsqueda/filtro más complejas: Búsqueda por palabras clave (`q` o `search`) o Filtro por marca y/o precio. | 15% |
| Usabilidad del Listado | Implementación funcional de Paginación y/o Ordenación de resultados en la API de listado. | 10% |
| Salud del Servicio | El servicio debe estar desplegado en Cloud Run y ser accesible. El endpoint raíz (`/` o un `/health` check) debe devolver un estado HTTP 200 OK. | 5% |

### 2. Planeación, Documentación y Adaptación (25%)

Se evalúa la gestión del tiempo, el trabajo en equipo y la capacidad de diseño bajo las condiciones limitadas del hackathon.

| Criterio | Descripción | Ponderación |
| :--- | :--- | :--- |
| Diseño de API REST | La API debe seguir los principios RESTful (uso correcto de verbos HTTP, status codes y estructura de recursos) y ser intuitiva para el frontend. | 10% |
| Análisis de Recursos | Evidencia de que el equipo revisó los recursos existentes (BD, GCS, Service Account) para encontrar la información faltante (p. ej., credenciales, connection strings). | 5% |
| Trabajo en Equipo y Uso de IA | Evidencia de colaboración y reparto de tareas. La IA debe usarse para acelerar el desarrollo, no para reemplazar la comprensión. | 10% |

### 3. Calidad Técnica y Buenas Prácticas (25%)

Se evalúa la limpieza, eficiencia y estructura del código, reflejando la capacidad del equipo para trabajar con un stack real.

| Criterio | Descripción | Ponderación |
| :--- | :--- | :--- |
| Diseño de API REST | La API debe seguir los principios RESTful (uso correcto de verbos HTTP, status codes y estructura de recursos) y ser intuitiva para el frontend. | 10% |
| Análisis de Recursos | Evidencia de que el equipo revisó los recursos existentes (BD, GCS, Service Account) para encontrar la información faltante (p. ej., credenciales, connection strings). | 5% |
| Trabajo en Equipo y Uso de IA | Evidencia de colaboración y reparto de tareas. La IA debe usarse para acelerar el desarrollo, no para reemplazar la comprensión. | 10% |
