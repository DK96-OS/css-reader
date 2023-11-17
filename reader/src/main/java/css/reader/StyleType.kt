package css.reader

/** A Type of CSS Block.
 */
enum class StyleType {

    /** A Style applied to an element with a specific Id.
     */
    ID,

    /** A Style applied to a class of elements.
     */
    CLASS,

    /** A KeyFrame Style block.
     */
    KEYFRAMES;

    /** Obtain the character, or sequence denoting a scope of CSS.
     * @return A Character Array, either 1 length or more.
     */
    fun getCSSTag()
        : CharArray = when (this) {
        ID -> charArrayOf('#')
        CLASS -> charArrayOf('.')
        KEYFRAMES -> "@keyframes ".toCharArray()
    }

    companion object {
        /** Determine the Type, based on the given chars.
         * @param chars The Characters to use to determine the type.
         * @return The Style Type if valid, otherwise returns null.
         */
        fun getType(
            chars: CharArray
        ) : StyleType? {
            return when (chars[0]) {
                '.' -> CLASS
                '#' -> ID
                '@' -> {
                    if (Character.toLowerCase(chars[1]) == 'k'
                        && chars.size > 3
                        ) KEYFRAMES
                    else
                        null
                }
                else -> null
            }
        }
    }

}