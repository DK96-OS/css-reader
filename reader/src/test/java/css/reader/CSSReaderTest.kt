package css.reader

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/** Testing CSSReader.
 */
class CSSReaderTest {

	private lateinit var mInstance: CSSReader

	private val mInput = """
		.pText {
		  margin: 0px 1px;
		  color: #2e3586;
		  text-align: center;
		  padding: 0px;
		}
		#horizontalBar {
		  height: 9px;
		  width: 100%;
		  background-color: #2e3586;
		}
	""".trimIndent()

	@Before
	fun testSetup() {
		mInstance = CSSReader(mInput)
	}

	@Test
	fun testInitialCondition() {
		assertEquals(
			2, mInstance.blocks.size
		)
	}

	@Test
	fun testInitialCondition_Block1() {
		val block1 = mInstance.blocks[0]
		assertEquals(
			"pText", block1.name
		)
		assertEquals(
			StyleType.CLASS, block1.type
		)
	}

	@Test
	fun testInitialCondition_Block2() {
		val block2 = mInstance.blocks[1]
		assertEquals(
			"horizontalBar", block2.name
		)
		assertEquals(
			StyleType.ID, block2.type
		)
	}

}