# Utilisation de l'image openjdk:21 comme base
FROM openjdk:21
# Définition du répertoire de travail dans le conteneur
WORKDIR /app
# Copie du fichier JAR depuis le système hôte vers le répertoire /app dans le conteneur
COPY ecfpoo.jar /app/ecfpoo.jar
# Commande à exécuter lorsque le conteneur démarre
CMD ["java", "-jar", "ecfpoo.jar"]

FROM php:apache

# Installer phpMyAdmin
RUN apt-get update && apt-get install -y phpmyadmin

# Lier phpMyAdmin à Apache
RUN ln -s /usr/share/phpmyadmin /var/www/html/phpmyadmin

# Configurer phpMyAdmin pour se connecter à MySQL
# (vous devrez peut-être fournir des informations de connexion spécifiques ici)
# Exemple: COPY config.inc.php /etc/phpmyadmin/config.inc.php

# Exposer le port Apache
EXPOSE 80

# Commande par défaut pour démarrer Apache
CMD ["apache2-foreground"]
