package css.reader

/** A block of CSS, with a name, a type, and set of properties.
 * @param name The name of the CSS Block.
 * @param type The type of CSS Block.
 * @param innerBlock
 */
class CSSBlock(
    val name: String,
    val type: StyleType,
    innerBlock: String,
) {
    /**
     */
    private val properties
        : MutableMap<String, String> = toCSSProperties(innerBlock)

    /**
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
        val map = java.util.TreeMap<String, String>()
        var parser = 0
        while (parser < css.length) {
            val colonIdx = css.indexOf(":", parser)
            val scIdx = css.indexOf(";", parser)
            if (scIdx < 0) {
                println("Missing Semicolon")
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