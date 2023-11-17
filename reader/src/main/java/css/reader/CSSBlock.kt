package css.reader

/** A block of CSS, with a name, a type, and set of properties.
 * @param name The name of the CSS Block.
 * @param type The type of CSS Block.
 * @param innerBlock
 */
class CSSBlock(
    /** The name of the block.
     */
    val name: String,
    /** The type of style the block applies to.
     */
    val type: StyleType,
    innerBlock: String,
) {
    /** The properties, in a Tree Map.
     *  The Tree ensures that the properties are ordered alphabetically
     */
    private val properties
        : MutableMap<String, String> = toCSSProperties(innerBlock)

    /** Obtain all of the properties as an iterable.
     */
    fun getProperties()
        : Iterable<Map.Entry<String, String>> = properties.asIterable()

    override fun toString(): String {
        return buildString {
            append(type.getCSSTag())
            append(name)
            append('{')
            properties.asIterable()
                .forEach { (k, v) -> append("$k:$v;") }
            append('}')
            appendLine()
        }
    }

    /** Clear all properties from the Block.
     */
    internal fun clearProperties() {
        properties.clear()
    }

    /** Convert a CSS String to a Map of CSS Properties.
     * @param css The CSS String input.
     * @return A Map of CSS Properties.
     */
    internal fun toCSSProperties(
        css: String,
    ) : MutableMap<String, String> {
        val map: MutableMap<String, String> = java.util.TreeMap()
        var parser = 0
        while (parser < css.length) {
            val colonIdx = css.indexOf(":", parser)
            var scIdx = css.indexOf(";", parser)
            if (scIdx < 0) {
                // Missing semicolon
                scIdx = css.indexOf('\n', colonIdx)
                if (scIdx < 0) {
                    // Skip to the end
                    if (css.indexOf(":", colonIdx + 1) != -1) {
                        // There are more properties after, must cancel operation
                        throw IllegalStateException("Missing Semicolon in CSS")
                    }
                    scIdx = css.length
                }
            }
            parser = if (colonIdx in 1 until scIdx) {
                val k = css.substring(parser, colonIdx).trim()
                val v = css.substring(colonIdx + 1, scIdx).trim()
                if (k.isNotEmpty() && v.isNotEmpty())
                    map[k] = v
                scIdx + 1
            } else
                css.length
        }
        return map
    }

}