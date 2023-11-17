package css.properties

class ColorProperties {


	/**
	 */
	fun getColor(
		value: String = "black",
	) : String {
		return "color:$value"
	}

	/**
	 */
	fun getBackgroundColor(
		value: String = "white",
	) : String {
		return "background-color:$value"
	}

	/**
	 */
	fun getBackground(
		value: String = "white",
	) : String {
		return "background:$value"
	}

}