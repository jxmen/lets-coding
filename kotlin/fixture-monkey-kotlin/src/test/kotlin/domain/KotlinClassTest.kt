package domain

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.into
import com.navercorp.fixturemonkey.kotlin.setExp
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.example.domain.KotlinClass
import org.junit.jupiter.api.Test

class KotlinClassTest {

    @Test
    fun name() {
        val fixtureMonkey = FixtureMonkey.builder()
            .apply { plugin(KotlinPlugin()) }
            .build()

        val kotlinClass = fixtureMonkey.giveMeBuilder<KotlinClass>()
            .setExp(KotlinClass::nestedObject into KotlinClass.Nested::nestedField, "nestedField")
            .setExp(KotlinClass::nestedObjectList, emptyList<KotlinClass.Nested>())
            .sample()

        assertThat(kotlinClass.nestedObject.nestedField).isEqualTo("nestedField")
        assertThat(kotlinClass.nestedObjectList).isEmpty()
    }
}
