<div style="display: flex; justify-content: center; align-items: center; border-bottom:2px solid gray">
    <a href="https://www.youtube.com/watch?v=xvFZjo5PgG0">
        <img id="logo" src="./Documentazione/static/text_logo.svg" alt="" style="padding: 20px 0; width: 45%; height: 45%">
    </a>
</div>

# Turing Careers

### Chi Siamo
- <a href="https://github.com/JacopoPassariello">@JacopoPassariello</a>
- <a href="https://github.com/antoninoLorenzo">@antoninoLorenzo</a>
- <a href="https://github.com/ClaudioGaudino">@ClaudioGaudino</a>
- <a href="https://github.com/apagnotta">@apagnotta</a>

## Installazione
Essendo sistema Turing Careers è composto da una componente Core realizzato in Java e da una API 
che fornisce le funzionalità di ricerca e raccomandazione, per questo motivo di seguito viene 
mostrato il procedimento per installare ed effettuare il deploy di entrambi i sistemi.


### TC Core
**Dipendenze TC Core**:
- Java 17
- MySQL 8.0
- Apache Tomcat 10.1
- Maven

**Installazione**
1. Clona la repository tramite il seguente comando:
``` 
git clone https://github.com/JacopoPassariello/TuringCareers.git 
```
2. Crea il database:
```
mysql -u[username] -p[password] < ./database/tabelleTuringCareers.sql && mysql -u[username -p[password] turing_careers < ./database/db_populator.db
```

3. Spostati nel percorso /src/main/resources/META-INF e modifica il file persistence.xml 
```xml
<!-- jdbc driver -->
<property name="jakarta.persistence.jdbc.user" value="YOUR_USERNAME"/>
<property name="jakarta.persistence.jdbc.password" value="YOUR_PASSWORD"/>
```

4. Crea il package WAR tramite Maven
```
mvn package
```

5. Copia il file WAR nella directory dedicata al deploy nel percorso di installazione di Tomcat, poi apri un terminale nel percorso di installazione e esegui:
```
create-domain --adminport [PORT] --user admin [domain-name] 
```
```
asadmin start-domain [domain-name]
```


### TC Recommender
**Dipendenze TC Recommender**:
- Python >= 3.12
- MySQL 8.0
- Terminale Bash


**Installazione**
1. Clona la repository:
```
git clone https://github.com/antoninoLorenzo/RecommenderSystem_TC.git
```
2. Installa i package necessari tramite il seguente comando:
```
pip install -r requirements.txt
```
3. Esegui il seguente comando tramite un terminale bash specificando l'indirizzo su cui è stato 
effettuato il deploy del del database come [ip:port/connection] (es. localhost:3306/turing_careers), poi il nume utente e la password:
```
nohup python launcher.py —db-address [ip:port/connection] —db-user [Nome Utente] —db-psw [Password] > log.txt & echo $! > pid.txt
```


