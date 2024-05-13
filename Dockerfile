# Utilisation de l'image openjdk:21 comme base
FROM openjdk:21
# Définition du répertoire de travail dans le conteneur
WORKDIR /app
# Copie du fichier JAR depuis le système hôte vers le répertoire /app dans le conteneur
#COPY ecfpoo.jar /app/ecfpoo.jar
COPY ecfpoo.jar /ecfpoo.jar


# Commande à exécuter lorsque le conteneur démarre
CMD ["java", "-jar", "ecfpoo.jar"]

