@echo off

call wipe.bat

if not "%JAVA7_64_HOME%"=="" (
    if exist "%JAVA7_64_HOME%\bin\javac.exe" (
        "%JAVA7_64_HOME%\bin\javac" -encoding UTF-8 *.java
        exit 0
    )
)

if not "%JAVA7_32_HOME%"=="" (
    if exist "%JAVA7_32_HOME%\bin\javac.exe" (
        "%JAVA7_32_HOME%\bin\javac" -encoding UTF-8 *.java
        exit 0
    )
)

if not "%JAVA7_HOME%"=="" (
    if exist "%JAVA7_HOME%\bin\javac.exe" (
        "%JAVA7_HOME%\bin\javac" -encoding UTF-8 *.java
        exit 0
    )
)

if not "%JAVA_HOME%"=="" (
    if exist "%JAVA_HOME%\bin\javac.exe" (
        "%JAVA_HOME%\bin\javac" -encoding UTF-8 *.java
        exit 0
    )
)

javac -encoding UTF-8 *.java
