#!/bin/bash
# Script pour exécuter les tests et déployer en cas de succès

export GECKODRIVER_PATH="./drivers/geckodriver.exe"

# Démarrer Spring Boot en arrière-plan
mvn spring-boot:run &> spring-boot-output.log &
SPRING_BOOT_PID=$!

# Attendre que Spring Boot soit prêt
echo "Waiting for Spring Boot to start..."
sleep 5

until curl --output /dev/null --silent --head --fail http://localhost:8080; do
    echo "Waiting for Spring Boot to start..."
    sleep 5
done

# Exécuter les tests Maven
mvn clean test
TEST_RESULT=$?

# Arrêter Spring Boot
kill $SPRING_BOOT_PID

# Vérifier si les tests ont réussi
if [ $TEST_RESULT -eq 0 ]; then
  echo "Tous les tests ont réussi."
else
  echo "Des tests ont échoué."
  exit 1
fi
