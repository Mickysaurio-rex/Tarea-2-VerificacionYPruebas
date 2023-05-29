import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PermisosStatic {

    PermisosServiceStatic permisosServiceStatic;

    public PermisosStatic(){
        permisosServiceStatic = new PermisosServiceStatic();
    }
    public PermisosStatic(PermisosServiceStatic permisosServiceStatic){
        this.permisosServiceStatic = permisosServiceStatic;
    }
    public String getRoles(String usr, String psw){
        boolean isUser = permisosServiceStatic.isUser(usr, psw);
        if(isUser){
            Date fecha = Calendar.getInstance().getTime();
            DateFormat formatofecha = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
            String fechaStr = formatofecha.format(fecha);
            return "PERMISSION: " + permisosServiceStatic.getPermisos(usr) + " - " + fechaStr;
        }else{
            return "Incorrect " + usr + " and " + psw;
        }
    }
}
