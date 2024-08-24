package persistence

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.example.persistence.JpaProduct
import org.junit.jupiter.api.RepeatedTest


/**
 * 사용자 정의 객체 - https://naver.github.io/fixture-monkey/v1-0-0-kor/docs/get-started/customizing-objects/
 * 객체 커스터마이징 - https://naver.github.io/fixture-monkey/v1-0-0-kor/docs/customizing-objects/apis/
 */
class JpaProductTest {

    @RepeatedTest(30)
    fun sampleProductTest() {
        // given
        val fixtureMonkey = FixtureMonkey.builder()
            .plugin(KotlinPlugin())
            .build()

        // when
        val jpaProduct = fixtureMonkey.giveMeBuilder<JpaProduct>()
            .setPostCondition { it.name.length <= 15 }
            .setPostCondition { it.price >= 0 }
            .sample()

        // then
        assertThat(jpaProduct.name).hasSizeLessThan(16)
        assertThat(jpaProduct.price).isGreaterThanOrEqualTo(0)
        assertThat(jpaProduct.id).isNotNull
        assertThat(jpaProduct.createdAt).isNotNull
        assertThat(jpaProduct.updatedAt).isNotNull
        println(jpaProduct)
    }
}

