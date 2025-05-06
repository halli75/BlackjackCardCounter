@echo off
set JAVA_FX_LIB=C:\Users\arnav\OneDrive\Desktop\openjfx-24.0.1_windows-x64_bin-sdk\javafx-sdk-24.0.1\lib
echo Using JavaFX library path: %JAVA_FX_LIB%

REM === Compile .java files ===
echo Compiling Java files...
javac --module-path "%JAVA_FX_LIB%" --add-modules javafx.controls -d out src\blackjack\*.java

IF %ERRORLEVEL% NEQ 0 (
    echo Compilation failed.
    pause
    exit /b
)

REM === Create manifest ===
echo Main-Class: blackjack.ConfigScreen > MANIFEST.MF

REM === Create JAR ===
echo Creating JAR file...
jar cfm BlackjackTrainer.jar MANIFEST.MF -C out .

REM === Run JAR ===
echo Running the JAR...
java --module-path "%JAVA_FX_LIB%" --add-modules javafx.controls -jar BlackjackTrainer.jar
pause
