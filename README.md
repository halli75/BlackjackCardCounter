# BlackjackCardCounter
# 🃏 Blackjack Card Counting Trainer (JavaFX)

This is a JavaFX-based tool designed to help players practice **Hi-Lo card counting** in blackjack. Players are dealt hands automatically using basic strategy, then must guess the running count. The game tracks performance and provides feedback to improve card counting skills.

---

## Features

- Customizable number of decks (4–8)
- Adjustable number of players (1–8)
- Difficulty settings (Easy, Medium, Hard) that control card dealing speed
- Real blackjack dealing order (dealer gets one card face down)
- Basic strategy-based play logic
- Hi-Lo card counting system
- Running count maintained across hands (no reshuffle)
- Accuracy-based scoring at end of session

---

## 🛠 Requirements

- Java JDK 11 or higher
- JavaFX SDK 11 or higher

---

## 📦 How to Run

### 1. Install Java & JavaFX

- Download and install the [JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- Download [JavaFX SDK](https://gluonhq.com/products/javafx/)

### 2. Set Up Environment

Add your JDK `bin` directory to the system PATH (so `javac`, `java`, and `jar` work in terminal or cmd).
In the build_and_run.bat change the path to the path of the lib folder in your JDK folder
Create a enviornment variable for you Java FX folder

### 3. Compile & Run with Script

In the project root folder, run:
.\build_and_run.bat

Scoring
The game ends when 85% of the shoe is dealt. Your final score is calculated as:
(# of correct count guesses) / (total hands played) × 100%

Future Features (Ideas)
- Visual card images instead of text
- Blackjack Table background
- Sounds for dealing cards
- Bankroll tracking
- Support for multiple counting systems (e.g., KO, Omega II)
- Teaching betsizes dependent on true count
- Count history and performance tracking
- Realistic reshuffling with penetration setting


👨‍💻 Author
Created by Arnav Gowda as a JavaFX project to combine game logic, UI design, and algorithmic decision-making.
