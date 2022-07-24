:start
@echo off
title MC-ASTG.NET(BC)
"C:\Program Files (x86)\Java\Zulu\bin\java.exe" -Dfile.encoding=UTF-8 -Xmx2G -jar paper.jar nogui
pause
goto restart
:restart
@echo off
title MC-ASTG.NET(BC)
"C:\Program Files (x86)\Java\Zulu\bin\java.exe" -Dfile.encoding=UTF-8 -Xmx2G -jar paper.jar nogui
pause
goto restart