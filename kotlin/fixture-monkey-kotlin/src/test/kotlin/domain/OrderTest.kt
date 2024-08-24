package domain

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.minSizeExp
import com.navercorp.fixturemonkey.kotlin.setExp
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.example.domain.Order
import org.junit.jupiter.api.Test


class OrderTest {

    @Test
    fun sampleOrder() {
        // given
        val sut = FixtureMonkey.builder()
            .plugin(KotlinPlugin())
            .build()

        // when
        val actual = sut.giveMeBuilder<Order>()
            .setExp(Order::orderNo, "1")
            .setExp(Order::productName, "Line Sally")
            .minSizeExp(Order::items, 1)
            .sample()

        // then
        assertThat(actual.orderNo).isEqualTo("1")
        assertThat(actual.productName).isEqualTo("Line Sally")
        assertThat(actual.items).hasSizeGreaterThanOrEqualTo(1)
    }

}
