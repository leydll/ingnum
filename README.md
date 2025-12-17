## 1. Installation

### Installer sur macOS

j'ai utilisé Homebrew pour installer la version OpenJDK 21 :
```bash
brew install openjdk@21
```
## 2. Lancement sans Docker
1. compiler le projet et créer le fichier jar :
```bash
./gradlew build
```
2. lancer l'app depuis le terminal :
```bash
java -jar build/libs/RentalService-0.0.1-SNAPSHOT.jar
```
3. créer le fichier dockerfile et mettre dedans :
```bash
FROM eclipse-temurin:21-jdk
COPY build/libs/RentalService-0.0.1-SNAPSHOT.jar .
CMD java -Xmx300m -Xms300m -XX:TieredStopAtLevel=1 -noverify -jar RentalService-0.0.1-SNAPSHOT.jar
EXPOSE 8081
``` 

## 4. Lancement avec Docker

1. Construction de l'image :
docker build -t rentalservice .

2. Lancement du conteneur :
docker run -p 8081:8080 rentalservice