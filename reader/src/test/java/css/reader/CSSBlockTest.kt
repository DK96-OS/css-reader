package css.reader

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/** Testing CSSBlock class.
 */
class CSSBlockTest {

	lateinit var mInstance: CSSBlock

	/** The name of the block used during tests.
	 */
	val blockName = "headerStyle"

	@Before
	fun testSetup() {
		mInstance = CSSBlock(
			blockName,
			StyleType.CLASS,
			"color: red; background-color: blue;"
		)
	}

	@Test
	fun testGetProperties_HasTwoProperties() {
		assertEquals(
			2, mInstance.getProperties().count()
		)
	}

	@Test
	fun testClearProperties_RemovesAllProperties() {
		mInstance.clearProperties()
		//
		assertEquals(
			0, mInstance.getProperties().count()
		)
	}

	@Test
	fun testToString_ContainsBlockName() {
		val result = mInstance.toString()
		assertTrue(
			result.contains(blockName)
		)
		// assert that the style type is in the correct location
		assertTrue(
			result.indexOf("#") < result.indexOf(blockName)
		)
	}

	@Test
	fun testToString_ContainsProperties() {
		val result = mInstance.toString()
		// Also ensure that the properties do not contain spaces
		assertTrue(
			result.contains("color:red;")
		)
		assertTrue(
			result.contains("background-color:blue;")
		)
	}

	@Test
	fun testToString_ContainsBrackets() {
		val result = mInstance.toString()
		assertTrue(
			result.contains("{")
		)
		assertTrue(
			result.contains("}")
		)
	}

	@Test
	fun testToString_AfterClearProperties_ReturnsBlockName() {
		mInstance.clearProperties()
		val result = mInstance.toString()
		assertFalse(
			result.isEmpty()
		)
		assertTrue(
			result.contains(mInstance.name)
		)
		assertEquals(
			15,
			result.length
		)
	}

	@Test
	fun testGetProperties_InputNoFinalSemicolon_HasTwoProperties() {
		mInstance = CSSBlock(
			blockName,
			StyleType.CLASS,
			"color: red; background-color: blue"
		)
		val result = mInstance.getProperties()
		assertEquals(
			2, result.count()
		)
		// properties are ordered alphabetically
		assertEquals(
			"background-color", result.elementAt(0).key
		)
		assertEquals(
			"blue", result.elementAt(0).value
		)
		assertEquals(
			"color", result.elementAt(1).key
		)
		assertEquals(
			"red", result.elementAt(1).value
		)
	}

	@Test
	fun testGetProperties_InputContainsNewLine_HasTwoProperties() {
		mInstance = CSSBlock(
			blockName,
			StyleType.CLASS,
			"""
			color: red; 
			background-color: blue;
			"""
		)
		val result = mInstance.getProperties()
		assertEquals(
			2, result.count()
		)
		// properties are ordered alphabetically
		assertEquals(
			"background-color", result.elementAt(0).key
		)
		assertEquals(
			"blue", result.elementAt(0).value
		)
		assertEquals(
			"color", result.elementAt(1).key
		)
		assertEquals(
			"red", result.elementAt(1).value
		)
	}

}