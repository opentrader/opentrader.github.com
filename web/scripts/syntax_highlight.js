$(function() {
    $("pre[class=]").addClass("brush: php")

    SyntaxHighlighter.config.clipboardSwf = base + '/scripts/syntax_highlight/clipboard.swf'
    SyntaxHighlighter.all();
})
