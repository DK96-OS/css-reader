package css.properties

/**
 */
class FontProperties {

	/**
	 */
	fun getSizeProperty(
		value: String = "large",
	) : String {
		return "font-size:$value"
	}

	/**
	 */
	fun getFontWeight(
		value: String = "bold",
	) : String {
		return "font-weight:$value"
	}

	/**
	 */
	fun getFontFamily(
		value: String = "Verdana, sans-serif",
	) : String {
		return "font-family:$value"
	}

	/**
	 */
	fun getTextAlign(
		value: String = "center",
	) : String {
		return "text-align:$value"
	}


}