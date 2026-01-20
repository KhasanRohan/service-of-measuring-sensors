//package autotests;
//
//import io.restassured.common.mapper.TypeRef;
//import io.restassured.http.ContentType;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.nurudinov.service_of_measuring_sensors.dto.SensorDTO;
//import org.nurudinov.service_of_measuring_sensors.service.SensorService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.util.List;
//
//import static io.restassured.RestAssured.given;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class SensorTest {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @BeforeEach
//    public void clearDatabase() {
//        // Очистка таблиц через JDBC
//        jdbcTemplate.execute("DELETE FROM sensors");
//    }
//
//    @Test
//    void positiveGetSensorTest() {
//        List<SensorDTO> sensorDTO = given()
//                .get("http://localhost:8080/api/sensors")
//                .then().log().all()
//                .statusCode(200)
//                .extract().as(new TypeRef<>() {});
//        Assertions.assertEquals("sensor1", sensorDTO.getFirst().getName());
//    }
//
//    @Test
//    void positiveRegisterSensorTest() {
//
//        SensorDTO sensor = new SensorDTO("TestSensor1");
//        String response = given()
//                .contentType(ContentType.JSON)
//                .body(sensor)
//                .post("http://localhost:8080/api/sensors/registration")
//                .then().log().all()
//                .statusCode(201)
//                .extract().jsonPath().getString("message");
//        Assertions.assertEquals("Сенсор зарегистрирован", response);
//    }
//}
