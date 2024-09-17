# Base Application

## Description

Base est une application Spring Boot qui sert de point de départ pour développer des applications plus complexes. Ce projet utilise diverses dépendances pour gérer les opérations CRUD, la sécurité, la persistance des données et la migration des bases de données.

## Table des Matières

- [Prérequis](#prérequis)
- [Installation](#installation)
- [Configuration](#configuration)
- [Exécution](#exécution)
- [Tests](#tests)
- [Dépendances](#dépendances)
- [Licence](#licence)

## Prérequis

Avant de commencer, assurez-vous que vous avez les éléments suivants installés sur votre machine :

- [Java 21](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html) ou version supérieure
- [Maven](https://maven.apache.org/download.cgi) (pour la gestion des dépendances et le build)
- [MySQL](https://dev.mysql.com/downloads/installer/) ou [MariaDB](https://mariadb.org/download/) (si vous utilisez une base de données différente d'H2)

## Installation

Clonez le dépôt et accédez au répertoire du projet :

```bash
git clone <[URL-DU-REPOSITORY](https://github.com/NARIHY/Base-spring-boot)>
cd base
```

Installez les dépendances du projet en utilisant Maven :

```bash
mvn clean install
```

## Configuration

### Fichier de Configuration

Configurez votre fichier `application.properties` ou `application.yml` situé dans le répertoire `src/main/resources`. Vous pouvez spécifier les paramètres de connexion à la base de données, le port du serveur, etc. Voici un exemple de configuration pour `application.properties` :

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/base
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.flyway.enabled=true
```

## Exécution

Pour démarrer l'application, utilisez Maven :

```bash
mvn spring-boot:run
```

L'application sera accessible à l'adresse `http://localhost:8080`.

## Tests

Pour exécuter les tests, utilisez la commande Maven suivante :

```bash
mvn test
```

Les tests incluent des tests unitaires et d'intégration pour vérifier que les fonctionnalités de l'application fonctionnent comme prévu.

## Dépendances

Voici un résumé des dépendances utilisées dans ce projet :

- **Spring Boot Starter Data JPA** : Fournit le support pour JPA et Hibernate.
- **Spring Boot Starter Security** : Intègre la sécurité avec Spring Security.
- **Spring Boot Starter Web** : Permet le développement d'applications web et RESTful.
- **Flyway Core et Flyway MySQL** : Pour la gestion des migrations de base de données.
- **H2 Database** : Base de données en mémoire utilisée pour les tests et le développement.
- **MariaDB Java Client** : Pilote JDBC pour MariaDB.
- **Lombok** : Réduit le code boilerplate en générant automatiquement des getters, setters, et autres méthodes.
- **Spring Boot Starter Test** : Inclut des outils pour les tests unitaires et d'intégration.
- **Spring Security Test** : Fournit des outils pour tester des fonctionnalités de sécurité.
