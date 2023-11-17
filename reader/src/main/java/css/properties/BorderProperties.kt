package css.properties

/**
 */
class BorderProperties {

	/**
	 */
	fun getBorderRadius(
		value: String = "6px",
	) : String {
		return "border-radius:$value"
	}

	/**
	 */
	fun getBorderWidth(
		value: String = "3px",
	) : String {
		return "border-width:$value"
	}

	/**
	 */
	fun getBorderColor(
		value: String = "black",
	) : String {
		return "border-color:$value"
	}

	/**
	 */
	fun getBorderStyle(
		value: String = "solid",
	) : String {
		return "border-style:$value"
	}

}