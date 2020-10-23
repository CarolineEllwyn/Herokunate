package caroline.hibernationapp.hibernation.Utils;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Command {

    public HttpStatus result;
    public Date date = new Date();

    public String requestIP = "UNKNOWN";
    public String requestPath = "UNKNOWN";
    public String requestMethod = "UNKNOWN";

    public Command(HttpServletRequest request){

        this.requestIP = request.getRemoteAddr();
        this.requestPath = request.getRequestURI();
        this.requestMethod = request.getMethod();

    }

    public void setResult(HttpStatus result){
        this.result = result;
    }

    public String toString() {
        StringBuilder strBuilder = new StringBuilder();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        strBuilder.append(String.format("%1$-25s", dateFormat.format(date)));

        strBuilder.append(String.format("%1$-20s", requestIP));

        strBuilder.append(String.format("%1$-10s", requestMethod));

        strBuilder.append(String.format("%1$-30s", requestPath));

        strBuilder.append(String.format("%1$-20s", result));

        return strBuilder.toString();

    }

}
