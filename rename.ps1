cd 'c:\Users\princ\Desktop\AreaResetterPro-AreaResetterPro-1.3.4-PAPER'

New-Item -ItemType Directory -Force -Path src\main\java\com\peyaj | Out-Null
Move-Item -Path src\main\java\com\lgndluke\arearesetterpro -Destination src\main\java\com\peyaj\arenareset
Remove-Item -Path src\main\java\com\lgndluke -Recurse -Force
Rename-Item -Path src\main\java\com\peyaj\arenareset\AreaResetterPro.java -NewName peyajArenaReset.java
Rename-Item -Path src\main\java\com\peyaj\arenareset\placeholders\AreaResetterProExpansion.java -NewName peyajArenaResetExpansion.java

$files = Get-ChildItem -Path . -Recurse -Include *.java,*.xml,*.yml,*.md | Where-Object { $_.FullName -notmatch '\\target\\' -and $_.FullName -notmatch '\\.git\\' -and $_.FullName -notmatch '\\.gemini\\' }
foreach ($file in $files) {
    $content = [System.IO.File]::ReadAllText($file.FullName)
    
    $newContent = $content -creplace 'AreaResetterPro', 'peyajArenaReset'
    $newContent = $newContent -creplace 'arearesetterpro', 'peyajarenareset'
    $newContent = $newContent -creplace 'com\.lgndluke', 'com.peyaj'
    $newContent = $newContent -creplace 'lgndluke', 'peyaj'
    $newContent = $newContent -creplace 'arp_', 'par_'
    
    if ($content -cne $newContent) {
        [System.IO.File]::WriteAllText($file.FullName, $newContent)
    }
}
