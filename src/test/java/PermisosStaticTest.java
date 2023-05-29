import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class PermisosStaticTest {
    @BeforeAll
    public static void beforeClass(){
        MockedStatic<PermisosServiceStatic> overridePermisosServiceStatic = Mockito.mockStatic(PermisosServiceStatic.class);

        overridePermisosServiceStatic.when(()->PermisosServiceStatic.isUser("JUAN", "1234")).thenReturn(true);
        overridePermisosServiceStatic.when(()->PermisosServiceStatic.isUser("GABRIEL", "231")).thenReturn(true);
        overridePermisosServiceStatic.when(()->PermisosServiceStatic.isUser("PABLO", "321")).thenReturn(true);
        overridePermisosServiceStatic.when(()->PermisosServiceStatic.isUser("MARIA", "123")).thenReturn(false);

        overridePermisosServiceStatic.when(()->PermisosServiceStatic.getPermisos("GABRIEL")).thenReturn("CRUD");
        overridePermisosServiceStatic.when(()->PermisosServiceStatic.getPermisos("PABLO")).thenReturn("R");
        overridePermisosServiceStatic.when(()->PermisosServiceStatic.getPermisos("JUAN")).thenReturn("CR");
    }

    @ParameterizedTest
    @CsvSource({
            "GABRIEL,231,PERMISSION: CRUD",
            "PABLO,321,PERMISSION: R",
            "JUAN,1234,PERMISSION: CR"
    }
    )
    public void verifyRolesStatic(String usr, String psw, String expectedResult){
        PermisosStatic permisosStatic = new PermisosStatic();
        String actualResult = permisosStatic.getRoles(usr, psw);
        Assertions.assertTrue(actualResult.contains(expectedResult), "ERROR");
    }
}
