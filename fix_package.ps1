$files = Get-ChildItem -Path . -Recurse -Include *.java,*.yml,*.xml | Where-Object { $_.FullName -notmatch '\\target\\' -and $_.FullName -notmatch '\\.git\\' -and $_.FullName -notmatch '\\.gemini\\' }
foreach ($file in $files) {
    $content = [System.IO.File]::ReadAllText($file.FullName)
    $newContent = $content -cReplace 'com\.peyaj\.peyajarenareset', 'com.peyaj.arenareset'
    if ($content -cne $newContent) {
        [System.IO.File]::WriteAllText($file.FullName, $newContent)
    }
}
