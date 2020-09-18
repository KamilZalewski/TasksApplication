call gradlew build
if "%ERRORLEVEL%" == "0" goto openchrome
echo.
echo GRADLEW BUILD has errors - breaking work
goto fail

:openchrome
start C:\ProgramFiles(x86)\Google\Chrome\Application\chrome.exe

goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.