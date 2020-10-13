package co.edu.unal.software_engineering.labs;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LabsApplicationTests{

	@Test
	void contextLoads( ){
		assertDoesNotThrow( ( ) -> LabsApplication.main( new String[]{ } ) );
	}

}