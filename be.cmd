:: Navigate to the specified directory
cd "C:\father project\two repos\Build-Ledger-Backend\BuildLedger-Backend"

:: Check if the directory exists
if not "%CD%"=="C:\father project\two repos\Build-Ledger-Backend\BuildLedger-Backend" (
    echo Directory not found or inaccessible.

)

:: Run git status
git status

:: Keep the CMD window open
cmd
