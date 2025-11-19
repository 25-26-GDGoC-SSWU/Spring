package jpabook.jpashop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@RunWith(SpringRunner.class) 구 버전이라 대신 ExtendWith 사용
@ExtendWith(SpringExtension.class)
@SpringBootTest
class JpashopApplicationTests {

	@Test
	void contextLoads() {
	}

}
