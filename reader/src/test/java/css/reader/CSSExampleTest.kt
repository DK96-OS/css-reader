package css.reader

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/** Testing CSS Examples
 */
class CSSExampleTest {

	val input1 = """
		.divContainer {
			height: 100%;
			width: 50%;
			float: left;
		}
	""".trimIndent()

	val input2 = """
		#title3 {
			font-size: x-large;
		}
	""".trimIndent()

	@Test
	fun testCSSBlock_input1_() {
		val reader = CSSReader(input1)
		assertTrue(reader.blocks.isNotEmpty())
		reader.blocks.first().also { block ->
			assertEquals(
				"divContainer", block.name
			)
			assertEquals(
				StyleType.CLASS, block.type
			)
			val string = block.toString()
			assertTrue(
				string.startsWith("." + block.name)
			)
			assertEquals(
				1, string.count { it == '{' }
			)
			assertEquals(
				1, string.count { it == '}' }
			)
		}
	}

	@Test
	fun testCSSBlock_input2_() {
		val reader = CSSReader(input2)
		assertEquals(
			1, reader.blocks.size
		)
		reader.blocks.first().also { block ->
			assertEquals(
				"title3", block.name
			)
			assertEquals(
				1, block.getProperties().count()
			)
		}
	}

}