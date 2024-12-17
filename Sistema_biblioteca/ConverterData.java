import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverterData {
    public static Date converterParaData(String dataStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.parse(dataStr);
        } catch (Exception e) {
            System.out.println("Erro ao converter a data.");
            return null;
        }
    }
}
