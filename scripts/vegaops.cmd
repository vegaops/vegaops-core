@echo off

SET VERSION=0.1
SET VEGAOPS=C:\vegaops
SET PROVIDERs=http://192.168.31.252/vegas/provider

SET P1=%1
SET P2=%2
SET P3=%3


::===
:: Print string
::
:println
    set str=%1
    set level=%2
    if %level% == "debug" (
        echo "DEBUG: %rest%"
    )
    if  "%level%" == "info" (
        call :ColorText 0a " %str%"
        echo.
    )
    if  "%level%" == "warn" (
        call :ColorText 19 " %str%"
        echo.
    )
    if  "$level" = "error" (
        call :ColorText 0b " %str%"
        echo.
    )
    if  "$level" = "success" (
        call :ColorText 0C " %str%"
        echo.
    )
    if  "$level" = "" (
        call :ColorText 0a " %str%"
        echo.
    )
GOTO:EOF


:print_help
    call :println "Welcom to VegaOps(version: %VERSION%)"
    call :println "Useage: "
    call :println "[1] Execute VegaOps task: "
    call :println "    ./vegaops <input> [<output>] [<format>]"
    call :println "             input:  File path which used to"
    call :println "                     define vegaops task."
    call :println "             output: File dir which used to"
    call :println "                     store task result(nodes info)."
    call :println "             format: Task file format, support yaml"
    call :println "                     and json format, default is yaml."
    call :println "[2] Show help usage of VegaOps cli: "
    call :println "    ./vegaops help|h"
    call :println "[3] Show version of VegaOps: "
    call :println "    ./vegaops version|v"
GOTO:EOF


if "%P1%" == "help" (
    call :print_help
    PAUSE
    EXIT /b 0
)

if "%P1%" == "h" (
    call :print_help
    PAUSE
    EXIT /b 0
fi

if "%P1%" == "version" (
    call :println "VegaOps version: %VERSION%"
    call :println "Copyright © 2019-2021 onepro All rights reserved."
    PAUSE
    EXIT /b 0
)

if "%P1%" == "v" (
    call :println "VegaOps version: %VERSION%"
    call :println "Copyright © 2019-2021 onepro All rights reserved."
    PAUSE
    EXIT /b 0
)

if "%P1%" == "" (
    call :print_help
    PAUSE
    EXIT /b 0
)


SET INPUT_FILE="%P1%"

if  not exist "%INPUT_FILE%" (
    call :println "Bad input file path: $INPUT_FILE" "error"
    PAUSE
    EXIT /b 1
)

SET FORMAT="%P3%"
SET OUTPUT_FILE=""

if not exist "%P2%" (
    if "%P2%" == "json" (
        SET FORMAT="%P2%"
        SET OUTPUT_FILE="%cd%"
    ) else if "%P2%" == "yaml" (
        SET FORMAT="%P2%"
        SET OUTPUT_FILE="%cd%"
    ) else
        call :println "Bad output file dir: $OUTPUT_FILE" "warn"
        SET OUTPUT_FILE="%cd%"
        call :println "Use $OUTPUT_FILE instead" "warn"
    )
)

if "%FORMAT%" == "" (
    SET FORMAT=yaml
)

SET OUTPUT_FILE="%OUTPUT_FILE%\out.%FORMAT%"

SET JAVA_HOME="%VEGAOPS%\jre1.8"
SET CLASSPATH="%JAVA_HOME%\lib\"

call :println "Checking input file ..."
for /F %%i in ('%JAVA_HOME%\bin\java -cp "%VEGAOPS%\lib\*" Tools parse %INPUT_FILE% vendor') do ( set vendor=%%i)
for /F %%i in ('%JAVA_HOME%\bin\java -cp "%VEGAOPS%\lib\*" Tools parse %INPUT_FILE% version') do ( set venver=%%i)

if "%vendor%" == "" (
    call :println "Vender should be specified!" "error"
    PAUSE
    EXIT /B 1
) else if "%vendor%" == "null" (
    call :println "Vender should be specified!" "error"
    PAUSE
    EXIT /B 1
)

if "%venver%" == "" (
) else if "%venver%" == "null" (
    call :println "Version of $venver should be specified!" "error"
    PAUSE
    EXIT /B 1
)

set provider_dir="%VEGAOPS%\provider\%vendor%\%venver%"

call :println "Current vendor: %vendor%, version: %venver%"
if not exist "%provider_dir%" (
    set target="vegasops-%vendor%-provider.%venver%.tar.gz"
    set url="%PROVIDERs%\%vendor%\%target%"
    call :println "Try to download %target% ..."
    if not exist "%VEGAOPS%\provider\%vendor%" (
        md "%VEGAOPS%\provider\%vendor%"
    )
    for /F %%i in ('%JAVA_HOME%\bin\java -cp "%VEGAOPS%\lib\*" Tools download %url% "%VEGAOPS%\provider\%vendor%"') do ( set stat=%%i)
    if "%stat%" == "404" (
        call :println "Could not find vendor %vendor% with version %venver%"
        PAUSE
        EXIT /B 1
    )
)

call :println "Begin to run VegaOps task: "
call :println "Input file path: %INPUT_FILE%"
call :println "Output file path: %OUTPUT_FILE%"

%JAVA_HOME%\bin\java -jar "%VEGAOPS%/vegaops-starter.jar" -p "%VEGAOPS%\provider" -l "%VEGAOPS%\lib" -c "%VEGAOPS%\config" -i "%INPUT_FILE%" -o "%OUTPUT_FILE%" -f "%FORMAT%"

call :println "Task executed successful!" "success"

if exist "%OUTPUT_FILE%" (
    call println "End of VegaOps task, output: "
    type "%OUTPUT_FILE%"
)
