# Pop Shop

Bienvenue sur le projet **Pop Shop** ! Cette application web vous permet de gérer une collection de figurines Pop préférées. Le projet utilise Spring Boot, Thymeleaf et H2 Database, et inclut des tests automatisés ainsi qu'un pipeline CI/CD pour déploiement continu sur Heroku.

## Table des Matières

- [Prérequis](#prérequis)
- [Installation](#installation)
- [Utilisation](#utilisation)
- [Tests](#tests)
- [Intégration Continue (CI)](#intégration-continue-ci)
- [Distribution Automatique (CD)](#distribution-automatique-cd)
- [Contributeurs](#contributeurs)

## Prérequis

Avant de commencer, assurez-vous d'avoir installé les éléments suivants sur votre machine :

- [JDK 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven](https://maven.apache.org/install.html)
- [Git](https://git-scm.com/downloads)
- [Geckodriver](https://github.com/mozilla/geckodriver/releases) pour Selenium

## Installation

1. Clonez le dépôt :

   ```bash
   git clone https://github.com/AlyssaBoyer/PopShop-Test.git
   cd PopShop-Test
   ```

2. Installez les dépendances Maven :

   ```bash
   mvn clean install -DskipTests
   ```

## Utilisation

1. Déclarez la variable d'environnement `GECKODRIVER_PATH` :

- **Windows** :
  ```powershell
  $env:GECKODRIVER_PATH=".\drivers\geckodriver.exe"
  ```

- **Linux** :
  ```bash
  export GECKODRIVER_PATH="./drivers/geckodriver"
  ```

2. Démarrez l'application Spring Boot :

   ```bash
   mvn spring-boot:run
   ```

3. Ouvrez votre navigateur et accédez à [http://localhost:8080](http://localhost:8080) pour voir l'application en action.

## Tests

### Tests Unitaires

Les tests unitaires sont utilisés pour vérifier les fonctionnalités isolées de l'application. Pour exécuter les tests unitaires, utilisez la commande suivante :

```bash
mvn test -Dtest=*UnitTestSuite
```

### Tests d'Intégration

Les tests d'intégration vérifient que les différentes parties de l'application fonctionnent bien ensemble. Pour les exécuter, utilisez :

```bash
mvn test -Dtest=*IntegrationTestSuite
```

### Tests Graphiques

Les tests graphiques automatisés utilisent Selenium pour vérifier l'interface utilisateur. Pour exécuter ces tests, utilisez :
- [S'assurer de déclarer la variable d'environnement](#utilisation)

```bash
mvn test -Dtest=*UITestSuite
```

### Lancement de tous les tests :

```bash
./run-tests.sh
```
ou

- [S'assurer de déclarer la variable d'environnement](#utilisation)
```bash
mvn test
```

## Intégration Continue (CI)

Nous avons configuré GitHub Actions pour exécuter des tests automatisés à chaque commit et pull request sur les branches `main`, `develop`, et `feat/*`. Le pipeline CI est défini dans le fichier `.github/workflows/ci.yml`.

### Pipeline CI

- **Déclenchement** : À chaque commit sur `main`, `develop`, et `feat/*`, ainsi qu'à chaque pull request vers `main` et `develop`.
- **Étapes** :
    - Checkout du code
    - Configuration de JDK 17
    - Installation des dépendances
    - Configuration et installation de Firefox et Geckodriver
    - Compilation du projet sans tests
    - Démarrage de l'application Spring Boot
    - Exécution des tests
    - Capture des logs en cas d'échec

## Distribution Automatique (CD)

Nous avons également configuré GitHub Actions pour déployer automatiquement l'application sur Heroku à chaque commit sur la branche `release`. Le pipeline CD est défini dans le fichier `.github/workflows/cd.yml`.

### Pipeline CD

- **Déclenchement** : À chaque commit sur la branche `release` et via `workflow_dispatch`.
- **Étapes** :
    - Checkout du code
    - Configuration de JDK 17
    - Installation des dépendances
    - Configuration et installation de Firefox et Geckodriver
    - Compilation du projet sans tests
    - Démarrage de l'application Spring Boot
    - Exécution des tests
    - Déploiement sur Heroku

## Contributeurs

- Fatima-Zahra BOUHASSOUN, Alyssa BOYYER, Marion RANDRIANASOLO


---

Merci de votre intérêt pour notre projet Pop Shop ! 🎉
