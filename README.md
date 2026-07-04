# Wochenberichte 📝

Eine benutzerfreundliche JavaFX-Anwendung zur einfachen Erstellung, Verwaltung und zum Drucken von IHK-Wochenberichten (Ausbildungsnachweisen). Die Anwendung bietet zudem eine intelligente Unterstützung durch künstliche Intelligenz (KI), um Wochenberichte basierend auf einfachen Textbeschreibungen automatisch zu generieren.

---

## 🌟 Features

- **Offizielles IHK-Layout:** Die Benutzeroberfläche und der Ausdruck sind exakt an das offizielle A4-Format der IHK-Wochenberichte angepasst.
- **Automatisches Speichern & Laden:** Berichte können im XML-basierten `.ihk` Format gespeichert und jederzeit wieder importiert werden (realisiert über JAXB).
- **KI-Unterstützung (AI Integration):** Sende eine kurze Beschreibung deiner Tätigkeiten an die integrierte KI, um den Bericht (Einträge und Stunden) automatisch ausfüllen zu lassen.
- **Intelligente Datumsberechnung:** Nach Angabe der Kalenderwoche/des Montags berechnet die App automatisch die korrekten Daten für die gesamte Woche (Montag bis Freitag).
- **Druckfunktion & PDF-Export:** Perfekt skalierter Ausdruck auf A4-Papier mit automatischer Ausblendung von Steuerelementen während des Druckvorgangs.
- **Tastatur-Shortcuts:**
  - `Strg + S` : Bericht speichern
  - `Strg + O` : Bericht öffnen/laden
  - `Strg + P` : Bericht drucken
  - `Strg + Shift + S` : Speichern und direkt drucken

---

## 🛠️ Technologien & Bibliotheken

- **Java 11**
- **JavaFX 13 & FXML** (UI-Framework)
- **Maven** (Dependency- & Build-Management)
- **JAXB (Java Architecture for XML Binding)** (Speichern/Laden von Berichtsdaten als XML)
- **Cloudflare Workers API** (Anbindung des KI-Assistenten zur automatisierten Generierung)

---

## 🚀 Installation & Starten

### Voraussetzungen
Stelle sicher, dass du folgende Software auf deinem System installiert hast:
* **Java Development Kit (JDK) 11** oder höher
* **Apache Maven 3.x**

### Projekt klonen
```bash
git clone https://github.com/DejanKrstovski/wochenBericht.git
cd wochenBericht
```

### Anwendung starten (Entwicklungsmodus)
Verwende das installierte Maven-Plugin, um die JavaFX-App direkt auszuführen:
```bash
mvn clean javafx:run
```

### Debugging & Profiling
Die Konfiguration im `pom.xml` unterstützt auch das direkte Debuggen über die IDE oder den manuellen Anschluss eines Debuggers:
```bash
# Debugger starten auf Port 8000
mvn clean javafx:run@debug
```

### Ausführbare JAR erstellen (Packaging)
Um eine eigenständige, ausführbare JAR-Datei (Fat-JAR) inklusive aller Abhängigkeiten zu erstellen, führe folgenden Befehl aus:
```bash
mvn clean package
```
Die fertige Datei findest du anschließend im Ordner `target/Wochenberichte-1.0-SNAPSHOT.jar`. Sie kann wie folgt gestartet werden:
```bash
java -jar target/Wochenberichte-1.0-SNAPSHOT.jar
```

---

## 🤖 Funktionsweise der KI-Unterstützung

Über den Chatbot-Button in der Menüleiste öffnet sich ein Eingabefenster. 
1. Gib stichpunktartig oder im Fließtext ein, was du in der Woche gelernt oder getan hast.
2. Die Anwendung sendet den Prompt an das Worker Backend.
   - *Tipp:* Du must eigene Worker URL über `AI_WORKER_URL` eintragen.
3. Der Service verarbeitet den Text und liefert ein valides XML-Dokument im IHK-Wochenbericht-Format zurück.
4. Die App lädt die Antwort automatisch in die entsprechenden Textfelder deines aktuellen Berichts.
