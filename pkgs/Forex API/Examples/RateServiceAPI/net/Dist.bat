ECHO Deleting previous distribution
del /Q /S /F Dist 

ECHO Creating Rate Service API Documentation
cd RateServiceAPI
..\tools\NDocConsole -project=RateServiceAPI.ndoc
cd ..

ECHO Copying RateServiceAPI Files
copy RateServiceAPI\bin\RateServiceAPI.dll dist
copy RateServiceAPI\bin\RateServiceAPI.xml dist

ECHO Copying RateService API Documentation
xcopy /I /Q RateServiceAPI\doc dist\doc

ECHO Copying RateService Example Projects
xcopy /I /Q RateServiceExampleVB dist\RateServiceExampleVB 
xcopy /I /Q RateServiceExampleCSharp dist\RateServiceExampleCSharp 

ECHO Copy Example Exe to dist directory
copy RateServiceExampleVB\bin\RateServiceExampleVB.exe dist
copy RateServiceExampleCSharp\bin\Release\RateServiceExampleCSharp.exe dist



