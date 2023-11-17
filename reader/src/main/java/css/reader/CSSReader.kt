package css.reader

/** Read and Organize CSS information.
 */
class CSSReader(
    initCSS: String,
) {
    /** The CSS Blocks that were found.
     */
    internal val blocks = ArrayDeque<CSSBlock>()

    init {
        // Key characters denoting the start of a block of CSS
        val keyChar = listOf(".", "#")
        // Parse the Input
        var parser = 0
        while (parser < initCSS.length) {
            val i = initCSS.findAnyOf(keyChar, parser)
            parser = if (i != null)
                parseBlock(
                    initCSS,
                    i.first + 1,
                    StyleType.getType(i.second.toCharArray()),
                )
            else
                initCSS.length
        }
    }

    /** Read a block of CSS.
     * @param cssInput The String containing CSS to be parsed.
     * @param varStart The start of the CSS Name.
     * @param type The Type of CSS block (identifier, or classifier).
     * @return The location of the end of the block.
     */
    internal fun parseBlock(
        cssInput: String,
        varStart: Int,
        type: StyleType?,
    ) : Int {
        // The characters at the end of a name, or block of CSS
        val endChar = listOf(" ", ":", "{", "}")
        // Find the end of the block name
        val endIdx = cssInput.findAnyOf(endChar, varStart)
        // Obtain the variable name
        val varName = if (endIdx != null && endIdx.first >= 0)
            cssInput.substring(varStart, endIdx.first).trim()
        else
            null
        if (type == null) {
            println("Unknown CSS Type: $varName")
            return cssInput.length
        }
        // Continue if the name is not null or blank
        return if (!varName.isNullOrBlank()) {
            val bracketIdx = cssInput.indexOf("{", varStart)
            val closeIdx = cssInput.indexOf("}", varStart)
            val innerBlock = if (bracketIdx >= 0 && closeIdx >= 0)
                cssInput.substring(bracketIdx + 1, closeIdx)
            else
                null
            if (innerBlock != null)
                blocks.add(
                    CSSBlock(varName, type, innerBlock)
                )
            if (closeIdx > 0)
                closeIdx + 1
            else
                cssInput.length
        } else
            cssInput.length
    }

    companion object {
        /** Extract CSS from HTML, one style tag at a time.
         * @param input The HTML String input.
         * @param parserIndex The index to start parsing.
         * @return A CSSReader object, or null if a CSS block tag was missing.
         */
        fun extractCSS(
            input: String,
            parserIndex: Int = 0,
        ) : CSSReader? {
            val cssStart = input.indexOf("<style", parserIndex)
            if (cssStart < 0) return null
            val cssEnd = input.indexOf("</style>", cssStart)
            if (cssEnd < 0) return null
            return CSSReader(
                input.substring(cssStart, cssEnd + 8)
            )
        }
    }

}